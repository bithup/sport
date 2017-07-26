<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<%--场馆排序方式--%>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta charset="UTF-8">
    <title>排序方式</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/index_fp.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">

</head>
<body>
<div class="content">
    <!--头部开始-->
    <div class="head">
        <div class="left"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
        排序方式
    </div>
    <!--范围-->
    <div class="range">
        <input type="hidden" id="id" value="${sportId}">
        <div class="range_a">排序：</div>
        <ul>
            <li><a href="">大小</a></li>
            <li><a href="">类别</a></li>
            <li><a href="">距离</a></li>
        </ul>

    </div>
</div>
</body>
</html>
