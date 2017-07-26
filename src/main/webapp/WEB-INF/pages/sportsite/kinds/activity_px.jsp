<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">

<head>
    <meta charset="UTF-8">
    <title>收费标准</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/activity_px.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".range>ul>li").on("click",function () {

                location.href="/kinds/kindIndex.htm?id=${sportId}&name=${sportName}&goodsType=3&condition=3&searchCondition="+$(this).attr("id");
            });
        })
    </script>

</head>
<body>
<div class="content">
    <div class="head">
        <div class="left"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
        收费标准
    </div>
    <!--范围-->
    <div class="range">
        <div class="range_a">标准：</div>
        <ul>
            <li id="1"><a>免费</a></li>
            <li id="0" class="jl"><a>收费</a></li>
            <li id="2"><a>AA制</a></li>
        </ul>

    </div>




</div>
</body>
</html>