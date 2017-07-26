<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta charset="UTF-8">
    <title>我的活动</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/my_activity.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="content">
    <div class="head">
        <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt=""></a>
        我的活动
    </div>
    <div class="activity">
        <c:forEach items="${list}" var="item">
        <h3><img src="${item.picRealPath}" alt=""></h3>
        <div class="box">
            <p class="font">秋季篮球竞赛</p>
            <p>圈住：豆豆它不逗</p>
        </div>
        <span><img src="<%=uiPath%>site/images/collect1.png" alt="">1人</span>
        </c:forEach>
    </div>
    <!--
    <div class="date"><p>已无更多数据</p></div>
    -->
</div>
<script>
    $(function(){
        $(".activity").click(function(){
            window.location.href="./my_act.html";
        })
    })
</script>
</body>
</html>