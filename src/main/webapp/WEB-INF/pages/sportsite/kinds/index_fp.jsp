<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!DOCTYPE html>
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
        <div class="range_a">排序：</div>
        <ul>
            <li><a href="/kinds/kindIndex.htm?id=${sportId}&name=${sportName}&goodsType=1&condition=3">大小</a></li>
            <li class="jl"><a href="/kinds/kindIndex.htm?id=${sportId}&name=${sportName}&goodsType=1&condition=4">价格</a></li>

        </ul>

    </div>
</div>
</body>
</html>