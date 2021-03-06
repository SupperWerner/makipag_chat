package ots.layim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import ots.layim.bean.InputInfoBean.InputModel;
import ots.layim.bean.OutputInfoBean.ResChatInfo;
import ots.layim.bean.OutputInfoBean.ResultChatModel;
import ots.layim.myenum.ChatEmit;
import ots.layim.util.JacksonUtil;
import ots.layim.util.WebsocketUtil;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

@Controller
@ServerEndpoint(value = "/websocket/{id}")
public class WebSocketController {

    Logger log = LoggerFactory.getLogger(WebSocketController.class);

    // webSocket中的会话
    private  Session session;

    // Http请求中的会话
    private HttpSession httpSession;

    // 保存实例待以后发送广播用到webSocket会话
    private static Map<String,WebSocketController> onlineUsers = new HashMap<>();

    // 在线人数统计
    private static int onlineCount = 0;


    @OnOpen
    public void onOpen(Session session , EndpointConfig config, @PathParam("id") String id ){
        // 1. 记录 webSocket：session
        this.session = session;


        // 2.获取当前登录HttpSession信息
        HttpSession httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;

        String ip = getIp(id, (x) -> x + WebsocketUtil.getRemoteAddress(session).toString());

        // 3. 记录当前登录用户信息，以及对应的Endpoin实例
        if (!StringUtils.isEmpty(id)){
            onlineUsers.put(ip,this);
        }
        log.info("当前登录人id+ip为：{}",ip);



        // 6. 通过广播的方式推送消息
        //session.getBasicRemote().sendText("");

        broadcastAllUsers("");
        addCount();
        log.info("登录成功！当前登录人数：{}",getOnlineCount());


    }

    @OnMessage
    public void onMessage(String msg,Session session){
        // 1. 获取消息端的内容并解析
        log.info("msg:{}",msg);

        // 接收的消息
       // InputModel inputModel = JacksonUtil.json2Object(msg, InputModel.class);
        JacksonUtil jacksonUtil = JacksonUtil.create();
        InputModel inputModel = jacksonUtil.jsonToObject(msg, InputModel.class);
        log.info(inputModel.toString());

        // 返回信息的模型
        ResultChatModel<ResChatInfo> resModel = new ResultChatModel<>();

        /*
         判断接收的类型
         if type = chatMessage    接受的是聊天信息
         type == chatModel  则代表接收的是系统消息
          */
        if (Objects.equals(inputModel.getType(),"chatMessage")){
            resModel.setEmit(ChatEmit.CHAT_MESSAGE);
            ResChatInfo resChatInfo = new ResChatInfo();
            resChatInfo.setUsername(inputModel.getData().getMine().getUsername());
            resChatInfo.setAvatar(inputModel.getData().getMine().getAvatar());
            resChatInfo.setId(inputModel.getData().getMine().getId());  // //消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
            resChatInfo.setType(inputModel.getData().getTo().getType());  // 消息类型 从获得消息的to中拿取
            resChatInfo.setContent(inputModel.getData().getMine().getContent());   // 消息内容
            resChatInfo.setCid("1"); // 消息id （可以不写）
            resChatInfo.setMine(true);
            resChatInfo.setFromid(inputModel.getData().getMine().getId()); //消息发送者id
            resChatInfo.setTimestamp(System.currentTimeMillis());  // 时间戳

            // 将消息对象转为json
            String resJson = jacksonUtil.objectToJson(resModel);
            log.info("返回信息Json序列化后的对象：{}",resJson);

            // 获取id+ip的Map Key值
            String toIp = getIp(inputModel.getData().getTo().getId(), (x) -> x + WebsocketUtil.getRemoteAddress(session).toString());
            String mineIp = getIp(inputModel.getData().getMine().getId(), (x) -> x + WebsocketUtil.getRemoteAddress(session).toString());
            // 发送消息
            toFriendMessage(resJson,toIp,mineIp);

        }else{
            resModel.setEmit(ChatEmit.CHAT_MODEL);
            // 暂时没用
        }



        // 4. 进行推送

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        delCount();
        log.info("当前用户数：{}",getOnlineCount());
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        throwable.printStackTrace();
        log.info("服务异常");
    }

    private void toFriendMessage(String content,String toId,String fromId){
        boolean isOnline = false;

        // 判断接受人是否在线
        Boolean online = isOnline(toId, x -> {
            Set<String> strings = onlineUsers.keySet();
            for (String string : strings) {
                if (Objects.equals(string, x)) {
                    return true;
                }
            }
            return false;
        });
        if (online){
            Set<String> ips = onlineUsers.keySet();
            for (String ip : ips) {
                if (Objects.equals(ip,toId)){
                    WebSocketController webSocket = onlineUsers.get(ip);
                    try {
                        webSocket.session.getBasicRemote().sendText(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }else{
            // 保存到数据库
            log.info("用户没有在线请");
        }


        // 如果存在发送消息
       /* if (isOnline){
            for(HttpSession httpSession : onlineUsers.keySet()){
                if (httpSession.getAttribute("username").equals(fromName)||httpSession.getAttribute("username").equals(toName)){
                    try {
                        onlineUsers.get(httpSession).session.getBasicRemote().sendText(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }*/
        // 如果不存在

    }

    /**
     * 获取id+ip的值 作为Map中的Key
     * @param id
     * @param fun
     * @return
     */
    private String getIp(String id, Function<String,String> fun){
        return fun.apply(id);
    }

    /**
     * 查询用户是否在线
     * @param mapKey
     * @param pre
     * @return
     */
    private Boolean isOnline(String mapKey, Predicate<String> pre){
        return pre.test(mapKey);
    }

    private String getNames() {
        StringBuffer names = new StringBuffer();
        /*if (onlineUsers.size()>0){
            for(HttpSession hsession : onlineUsers.keySet()){
                String username = (String) hsession.getAttribute("username");
                names.append(username).append(",");
            }
        }*/

        return names.substring(0,names.length()-1);
    }

    /**
     * 发送广播消息
     * @param message  信息
     */
    private void broadcastAllUsers(String message){
        for(String hsession : onlineUsers.keySet()){
            try {
                onlineUsers.get(hsession).session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取总在线人数
     * @return
     */
    public int getOnlineCount(){
        return onlineCount;
    }

    /**
     * 用户上线
     */
    public synchronized void addCount(){
        onlineCount ++;
    }

    /**
     * 用户退出
     */
    public synchronized void delCount(){
        onlineCount --;
    }

}
