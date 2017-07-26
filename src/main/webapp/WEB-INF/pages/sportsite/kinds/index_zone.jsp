<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta charset="UTF-8">
    <title>区域范围</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/index_yuf.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <div class="head">
        <div class="left"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
        区域范围
    </div>
    <!--范围-->
    <div class="range">
        <ul>
            <c:forEach items="${zoneNameList}" var="item">
                <li id="${item.id}">${item.name}</li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $(".range>ul>li").on("click",function () {
            //alert(this.id);
            location.href="/kinds/kindIndex.htm?id=${sportId}&name=${sportName}&goodsType=${goodsType}&condition=2&searchCondition="+this.id
        });
    });
</script>
</html>