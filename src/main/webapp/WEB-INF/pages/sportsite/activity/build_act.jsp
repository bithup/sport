<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>我的活动</title>
    <base href="<%=basePath%>">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=uiPath%>site/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/build_act.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <!-- 标题 -->
    <div class="log_tit">
        <a href="javascript:history.go(-1)"></a>
        <strong>我的活动</strong>
    </div>
    <div class="no"></div>
    <!-- 内容开始 -->
    <div class="my_act">
        <ul class="act_basic">
            <li><img src="<%=uiPath%>site/images/activity.png" alt=""></li>
            <li>
                <div>活动名称<span><input type="text" value="" id="activityName"></span></div>
            </li>
            <li>
                <div>活动类型<span><input type="text" value="" id="kindsName"></span></div>
            </li>
            <li>
                <div>活动地点<span><input type="text" value="" id="activityAddress"></span></div>
            </li>
            <li>
                <div>联系电话<span><input type="text" value="" id="contact"></span></div>
            </li>
        </ul>
        <div class="no"></div>
        <ul class="act_other">
            <li>
                <div>所属区域<span><input type="text" value="" id=""></span></div>
            </li>
            <li>
                <div>活动开始<span><input type="text" value="" id="startDate"></span></div>
            </li>
            <li>
                <div>活动结束<span><input type="text" value="" id="endDate"></span></div>
            </li>
            <li>
                <div>报名截止时间<span><input type="text" value="" id=""></span></div>
            </li>
            <li>
                <div>人数<span class="peo_count"><input type="text" value="" id="">人</span></div>
            </li>
        </ul>
        <div class="no"></div>
        <div class="act_intro">
            <div class="act_wrap">
                活动简介
                <textarea name=""  >篮球运动是以投篮、上篮、扣篮为中心的对抗性室内体育运动之一。</textarea>
                <div class="build_btn">
                    <a href="./act_check.html">创建</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>