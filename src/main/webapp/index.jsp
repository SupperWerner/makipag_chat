<%--
  Created by IntelliJ IDEA.
  User: mybook
  Date: 20-2-13
  Time: 下午1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="lib/layim-v3.9.1/dist/css/layui.css" media="all">

    <input type="text" id="userId"  >
    <button id="loginBtn">登录</button>
</head>
<body style="height: 100%">

<h3>欢迎页面</h3>

<script src="lib/layim-v3.9.1/dist/layui.js" type="text/javascript"></script>
<script>
    layui.use('layim', function(layim){
        var ws ;
        var $ = layui.jquery;
        $('#loginBtn').on('click',function(){
            var url = 'ws://localhost:8080/websocket/'+$('#userId').val();
            ws = new WebSocket(url);
            init();
            layer.msg("重新登录");
        });
        // 建立webSocket

        /*if ('Websocket' in window){*/
           /*ws = new WebSocket('ws://localhost:8080/websocket/1');*/
       /* }else if ('MozWebSocket' in window){
            ws = new MozWebSocket('ws://localhost:8080/webSocket/onOpen');
        }else {
            layer.msg("浏览器不支持在线聊天系统，请更换为Google浏览器")
        }*/


       function init(){
           // 监听有消息传递，触发该方法
           ws.onmessage = function(res){
               var data = res.data;
               layer.msg("收到消息");
           };

           // 关闭链接触发
           ws.onclose = function (res){
               layer.msg("关闭链接！");
           };

           // 打开时触发
           ws.onopen = function (res){
               layer.msg("打开链接！");
           };


           layim.on('sendMessage', function(res) {
               var mine = res.mine; //包含我发送的消息及我的信息
               ws.send(JSON.stringify({
                   type: 'chatMessage' //随便定义，用于在服务端区分消息类型
                   ,data: res
               }));
           });


           //基础配置
           var chat = layim.config({

               init: {
                   url:'chat/init/'+$('#userId').val()
                   ,type:'get'
                   ,data:{}
               } //获取主面板列表信息，下文会做进一步介绍

               //获取群员接口（返回的数据格式见下文）
               ,members: {
                   url: 'chat/members' //接口地址（返回的数据格式见下文）
                   ,type: 'get' //默认get，一般可不填
                   ,data: {} //额外参数
               }

               //上传图片接口（返回的数据格式见下文），若不开启图片上传，剔除该项即可
               ,uploadImage: {
                   url: 'chat/uploadImage' //接口地址
                   ,type: 'post' //默认post
               }

               //上传文件接口（返回的数据格式见下文），若不开启文件上传，剔除该项即可
               ,uploadFile: {
                   url: '' //接口地址
                   ,type: 'post' //默认post
               }
               //扩展工具栏，下文会做进一步介绍（如果无需扩展，剔除该项即可）
               ,tool: [{
                   alias: 'code' //工具别名
                   ,title: '代码' //工具名称
                   ,icon: '&#xe64e;' //工具图标，参考图标文档
               }]

               /*,msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
               ,find: layui.cache.dir + 'css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
               ,chatLog: layui.cache.dir + 'css/modules/layim/html/chatlog.html' //聊天记录页面地址，若不开启，剔除该项即可*/
           });
       }

    });
</script>
</body>
</html>
