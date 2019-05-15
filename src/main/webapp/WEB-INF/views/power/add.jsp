<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/10
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${PATH}/css/main.css">
    <link rel="stylesheet" href="${PATH}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">许可维护--许可增加</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li style="padding-top:8px;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
                            <i class="glyphicon glyphicon-user"></i> ${user.username} <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                            <li class="divider"></li>
                            <li><a href="${PATH}/doOut"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                        </ul>
                    </div>
                </li>
                <li style="margin-left:10px;padding-top:8px;">
                    <button type="button" class="btn btn-default btn-danger">
                        <span class="glyphicon glyphicon-question-sign"></span> 帮助
                    </button>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <jsp:include page="/WEB-INF/views/user/common.jsp"/>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form">
                        <div class="form-group">
                            <label >新的权限名</label>
                            <input type="text" class="form-control" id="powername" placeholder="请输入新增的权限名"><span id="mySpan"></span>
                        </div>
                        <div class="form-group">
                            <label >权限的url</label>
                            <input type="text" class="form-control" id="powerurl" placeholder="输入新增权限的url"><span id="mySpan1"></span>
                        </div>

                        <button type="button" id="insert" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${PATH}/script/docs.min.js"></script>
<script src="${PATH}/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
</script>
<script>
    $("#powername").blur(function () {
        var powername=$("#powername").val();
        var pid=${param.id};
         $.ajax({
             type:"POST",
             url : "${PATH}/power/select",
             data: {"name":powername,
                       "id":pid},
             success:function (result) {
                 if(!result.result)
                     $("#mySpan").html("权限名已经存在");
                 else
                     $("#mySpan").html("");
             }
             }

         )
    })
    $("#powerurl").blur(function () {
        var powerurl=$("#powerurl").val();

        $.ajax({
                type:"POST",
                url : "${PATH}/power/selectUrl",
                data: {"url":powerurl},
                success:function (result) {
                    if(!result.result)
                        $("#mySpan1").html("url已经存在");
                    else
                        $("#mySpan1").html("");
                }
            }

        )

    })
    $("#insert").click(function () {
        var powername=$("#powername").val();
        var powerurl=$("#powerurl").val();
        var span=$("#mySpan").html();
        var span1=$("#mySpan1").html();


        if(span!=""||span1!="") {
            layer.msg("数据不合法请检查", {time:1000, icon:5, shift:6}, function () {

            });
            return;

        }
        var id=${param.id};

        $.ajax({
            url:"${PATH}/power/insert",
            type: "POST",
            data: {
                "name":powername,
                "url" :powerurl,
                "pid": id

            },
            success : function (result) {
                if(result.result){
                    layer.msg("添加成功", {time:1000, icon:5, shift:6}, function () {
                    });
                    window.location.href="${PATH}/power/main";
                }
            }


        })
    })
</script>
</body>
</html>

