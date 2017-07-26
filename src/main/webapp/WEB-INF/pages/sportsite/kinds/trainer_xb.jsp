<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta charset="UTF-8">
    <title>教练性别</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/trainer_xb.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script type="text/javascript">
        $(function () {
            $(".option input").on("click",function () {
                location.href="/kinds/kindIndex.htm?id=${sportId}&name=${sportName}&goodsType=2&condition=3&searchCondition="+this.value;
            })
        })
    </script>

</head>
<body>
<div class="content">
    <!--头部开始-->
    <div class="head">
        <div class="left"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
        教练性别
    </div>
    <div class="no"></div>
    <div class="option">
        <p>性别</p>
            <input type="radio" name="sex" value="1" />男

            <span><input type="radio" name="sex" value="2" /> 女</span>

    </div>

</div>
</body>
</html>