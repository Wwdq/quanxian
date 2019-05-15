<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/4/15
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">

    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">权限管理系统</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form id="doLogin"  action="doLogin"  method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="username"  name="username" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>



        <br>
        <a class="btn btn-lg btn-success btn-block" href="javascript:void(0)" onclick="dologin()" > 登录</a>
    </form>
</div>
<script src="${PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${PATH}/layer/layer.js"></script>


<script>
    function dologin() {
        // 非空校验
        var username = $("#username").val();
        // 表单元素的value取值不会为null, 取值是空字符串
        if ( username == "" ) {
            layer.msg("用户名不能为空", {time:1000, icon:5, shift:6}, function () {

            });
            return;
        }

        var password = $("#password").val();
        if ( password == "" ) {
            layer.msg("密码不能为空", {time:1000, icon:5, shift:6}, function () {

            });
            return;
        }

        // 提交表单
        //alert("提交表单");
        //$("#loginForm").submit();
        // 使用AJAX提交数据
        var loadingIndex=null;
        $.ajax({
            type : "POST",
            url  : "doLogin",
            data : {
                "username" : username,
                "password"  : password
            },
            beforeSend : function(){
                loadingIndex = layer.msg('处理中', {icon: 16});
            },
            success : function(result) {
                layer.close(loadingIndex);
                if (result.result) {
                    window.location.href = "main";
                } else {
                    layer.msg(result.msg, {time:1000, icon:5, shift:6}, function () {

                    });
                }
            }
        });
    }
</script>
</body>
