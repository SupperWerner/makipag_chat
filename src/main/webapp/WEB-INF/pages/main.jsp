<%--
  Created by IntelliJ IDEA.
  User: mybook
  Date: 20-2-14
  Time: 上午10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>LayIM测试</title>
    <link rel="stylesheet" href="../../static/lib/layim-v3.9.1/dist/css/layui.css" media="all">
</head>
<body>

<h3>欢迎页面</h3>

<script src="../../static/lib/layim-v3.9.1/dist/layui.js" type="text/javascript"></script>
<script>
    layui.use('layim', function(layim){
        //先来个客服模式压压精
        layim.config({
            brief: true //是否简约模式（如果true则不显示主面板）
        }).chat({
            name: '客服姐姐'
            ,type: 'friend'
            ,avatar: 'http://tp1.sinaimg.cn/5619439268/180/40030060651/1'
            ,id: -2
        });
    });
</script>
</body>
</html>
