package ots.layim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ots.layim.bean.InitBean.InitDataVO;
import ots.layim.bean.InitBean.InitFriend;
import ots.layim.bean.InitBean.InitGroup;
import ots.layim.bean.Mine;
import ots.layim.bean.ResultModel;
import ots.layim.util.MyStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("chat/")
public class InitMakipagChatController {

    Logger log = LoggerFactory.getLogger(InitMakipagChatController.class);

    private static List<Mine> listMine = new ArrayList<>();
    static{
        Mine mine = new Mine();
        mine.setUsername("小明");
        mine.setSign("明天会更好！");
        mine.setStatus(true);
        mine.setId("1");
        mine.setAvatar("http://b-ssl.duitang.com/uploads/item/201510/24/20151024160112_uYtPB.jpeg");
        listMine.add(mine);

        Mine mine1 = new Mine();
        mine1.setStatus(true);
        mine1.setSign("明天会更好！");
        mine1.setAvatar("http://img1.imgtn.bdimg.com/it/u=561326960,1564995287&fm=11&gp=0.jpg");
        mine1.setId("2");
        mine1.setUsername("小红");
        listMine.add(mine1);

        Mine mine2 = new Mine();
        mine2.setStatus(true);
        mine2.setSign("明天会更好！");
        mine2.setAvatar("http://b-ssl.duitang.com/uploads/item/201509/22/20150922134955_vfEWL.jpeg");
        mine2.setId("3");
        mine2.setUsername("小里");
        listMine.add(mine2);

        Mine mine3 = new Mine();
        mine3.setStatus(true);
        mine3.setSign("明天会更好！");
        mine3.setAvatar("http://pic2.zhimg.com/50/v2-aca193e0dd7812fa9d99fe8008fb62fa_hd.jpg");
        mine3.setId("4");
        mine3.setUsername("小白");
        listMine.add(mine3);


        Mine mine4 = new Mine();
        mine4.setStatus(true);
        mine4.setSign("明天会更好！");
        mine4.setAvatar("http://cdn.duitang.com/uploads/item/201610/26/20161026123307_Etf8L.jpeg");
        mine4.setId("5");
        mine4.setUsername("小黑");
        listMine.add(mine4);

        Mine mine5 = new Mine();
        mine5.setStatus(true);
        mine5.setSign("明天会更好！");
        mine5.setAvatar("http://diy.qqjay.com/u/files/2012/0924/34ed6999a4d79361fd7d751ad767fced.jpg");
        mine5.setId("6");
        mine5.setUsername("小蓝");
        listMine.add(mine5);
    }


    @GetMapping("main")
    public String main(){
        return "main";
    }

    /**
     * 初始化主页模板信息
     * @return
     */
    @GetMapping("init/{id}")
    @ResponseBody
    public ResultModel<InitDataVO> initMode(@PathVariable String id){
        ResultModel<InitDataVO> model = new ResultModel();

        InitDataVO initDataVO = new InitDataVO();
        /* 查询  登录人信息 */
        log.info("查询登录人信息,id={}",id);
        Mine loginMine = getLoginMine(id);
        initDataVO.setMine(loginMine);



        /* 查询 好友列表 */
        log.info("查询好友列表,id={}",id);
        List<Mine> mines = onLineUser(id);

        InitFriend friend = new InitFriend();
        friend.setGroupname("好友");
        friend.setId("0010022");
        friend.setList(mines);

        List<InitFriend> listFriend = new ArrayList<>();
        listFriend.add(friend);
        initDataVO.setFriend(listFriend);



        /* 查询 群组列表 */
        log.info("查询群组列表,id={}",id);

        InitGroup group = new InitGroup();
        group.setAvatar("http://pic1.zhimg.com/50/v2-af12f7b6f7eb2f2f5f7e2f3b7880cf01_hd.jpg");
        group.setGroupname("颜色小组");
        group.setId("001011");
        ArrayList<InitGroup> listGroups = new ArrayList<>();
        initDataVO.setGroup(listGroups);

        model.setData(initDataVO);
        model.setCode(0);
        model.setMsg("");

        return model;
    }

    /**
     * 获取 群组的人员信息
     * @return
     */
    @GetMapping("members")
    @ResponseBody
    public ResultModel<Mine> getMembers(){
        ResultModel<Mine> model = new ResultModel<>();

        /* 查询 群组人员列表 */
        log.info("查询群组人员列表");

        return model;

    }

    @PostMapping("uploadImage")
    public ResultModel<Map<String,String>> uploadImage(HttpServletRequest request, MultipartFile file){
        ResultModel<Map<String, String>> model = new ResultModel<>();

        /*获取文件名*/
        String originalFilename = file.getOriginalFilename();
        String fileExtension = MyStringUtils.getFileExtension(originalFilename);

        /* 上传 图片信息 保存*/
        if ("zip".equals(fileExtension)){
            log.info("上传压缩文件，并保存到服务器，返回压缩文件url");
        }else{
            log.info("上传图片信息，并保存到服务器，返回图片url");
        }

        return model;
    }

    private Mine getLoginMine(String id){
        if (id !=null){
            for (Mine mine : listMine) {
                if (Objects.equals(id,mine.getId())){
                    return mine;
                }
            }
        }

        return null;
    }

    private List<Mine> onLineUser(String id){
        List<Mine> mines = new ArrayList<>();

        if (id !=null){
            for (Mine mine : listMine) {
                if (!Objects.equals(id,mine.getId())){
                    mines.add(mine);
                }
            }
        }
        return mines;
    }
}
