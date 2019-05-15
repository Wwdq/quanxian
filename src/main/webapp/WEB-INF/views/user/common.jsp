<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/15
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul style="padding-left:0px;" class="list-group">
    <c:forEach items="${userPower.children}"  var="power">
        <c:if test="${empty power.children}">
        <li class="list-group-item tree-closed" >
            <a href="${PATH}/${power.url}"><i class="${power.icon}"></i>${power.name}</a>
        </li>
    </c:if>
        <c:if test="${not empty power.children}">
            <li class="list-group-item">
                <span><i class="${power.icon}"></i> ${power.name} <span class="badge" style="float:right">${power.children.size()}</span></span>
                <c:forEach items="${power.children}" var="child">
                <ul style="margin-top:10px;">
                    <li style="height:30px;">
                        <a href="${PATH}${child.url}" ><i class="${child.icon}"></i>${child.name}</a>
                    </li>
                </ul>
                </c:forEach>
            </li>
        </c:if>
    </c:forEach>
</ul>



