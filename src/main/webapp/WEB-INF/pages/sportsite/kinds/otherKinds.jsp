<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!DOCTYPE html>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>更多</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/site_qt.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <!--头部开始-->
    <div class="head">
        <div class="head_1">
            <h3><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></h3>
            更多
        </div>
    </div>
    <!--分类开始-->
    <c:forEach items="${list}" var="item">
        <div class="box">
            <div class="ball"><p>${item.parentKinds.name}</p></div>
            <ul>
                <c:forEach items="${item.childList}" var="var" varStatus="status">
                    <c:choose>
                        <c:when test="${empty flg}">
                            <c:choose>
                                <c:when test="${status.index%3==0}">
                                    <li class="cur"><a href="/kinds/kindIndex.htm?id=${var.id}&name=${var.name}&goodsType=${goodsType}&condition=1">${var.name}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/kinds/kindIndex.htm?id=${var.id}&name=${var.name}&goodsType=${goodsType}&condition=1">${var.name}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test="${flg=='act'}">
                            <c:choose>
                                <c:when test="${status.index%3==0}">
                                    <li class="cur"><a href="/activity/activityListInit.htm?id=${var.id}">${var.name}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/activity/activityListInit.htm?id=${var.id}">${var.name}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test="${flg=='coach'}">
                            <c:choose>
                                <c:when test="${status.index%3==0}">
                                    <li class="cur"><a href="/coach/coachListInit.htm?id=${var.id}">${var.name}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/coach/coachListInit.htm?id=${var.id}">${var.name}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test="${flg=='venue'}">
                            <c:choose>
                                <c:when test="${status.index%3==0}">
                                    <li class="cur"><a href="/venue/venueListInit.htm?id=${var.id}">${var.name}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/venue/venueListInit.htm?id=${var.id}">${var.name}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                </c:forEach>
            </ul>
        </div>
        <div class="no"></div>

    </c:forEach>

</div>
</body>
</html>