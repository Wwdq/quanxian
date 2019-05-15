<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/14
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/14
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/10
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/4/17
  Time: 14:02
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
    <link rel="stylesheet" href="${PATH}/ztree/zTreeStyle.css">

    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#"> 许可维护</a></div>
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 权限分配（角色为:${role.name}）</h3>
                </div>
                <div class="panel-body">

                        <div class="form-group has-feedback">

                                <button onclick="doAssign()"  class="btn btn-success">分配许可</button>

                                <br><br>
                                <ul id="powerTree" class="ztree"></ul>

                        </div>

                    </form>


                    <br>
                    <hr style="clear:both;">

                </div>
            </div>
        </div>
    </div>
</div>

<script src="${PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${PATH}/script/docs.min.js"></script>
<script src="${PATH}/layer/layer.js"></script>
<script src="${PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>

<script >

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
    var setting = {
        async: {
            enable: true,
            url:"${PATH}/power/loadAssignData?rid=${param.id}",
            autoParam:["id", "name=n", "level=lv"],
            otherParam:{"otherParam":"zTreeAsyncTest"},

        },
        view: {

            showIcon: true,

            selectedMulti: false,
            addDiyDom: function (treeId, treeNode) {
                var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                if (treeNode.icon) {
                    icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background", "");
                }

            }
        },
        check: {
            enable: true
        }


    };




    $(document).ready(function(){

        $.fn.zTree.init($("#powerTree"), setting);
    });
    function doAssign(){
        var treeObj = $.fn.zTree.getZTreeObj("powerTree");
        var nodes = treeObj.getCheckedNodes(true);

        if(nodes.length==0){
            layer.msg("请选择许可信息", {time:3000, icon:5, shift:6}, function () {

            });

        }else{
            var d="rid="+${param.id};
            $.each(nodes,function (i,node) {
                    d += "&pid=" + node.id;
                }

            );
            $.ajax({
                type:"GET",
                url: "${PATH}/role/doAssign",
                data:d,
                success:function (result) {
                    if(result.result){
                        layer.msg("分配成功", {time:1000, icon:6, shift:6}, function () {
                            window.location.href="${PATH}/role/assign?id=${role.id}"
                        });


                    }
                }
            })
        }
    }
</script>

</body>
</html>

