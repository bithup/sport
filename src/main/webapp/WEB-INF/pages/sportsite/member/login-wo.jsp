<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>我的</title>
    <base href="<%=basePath%>">
    <link href="<%=uiPath%>site/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/Personal-center.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<!--标题-->
<div class="yd-title">
    <ul>
        <li class="title-left"> <a href="javascript:history.go(-1)"></a></li>
        <li class="title-center">我的</li>
        <li class="title-right"></li>
    </ul>
</div>
<!--头像-->
<div class="yd-bg">
    <div class="circular">
        <c:choose>
            <c:when test="${empty memberUser.headRealPath}">
            <img src="<%=uiPath%>site/images/defaultlogo.png"/></div>
            </c:when>
            <c:otherwise><img src="${memberUser.headRealPath}"/></div></c:otherwise>
        </c:choose>
    <p>${memberUser.nickName}</p>
</div>
<!--内容-->
<div class="login-content">
    <ul>
        <li class="goods" id="click1">我的订单</li>
        <li class="purse" id="myWallet">我的钱包<a></a></li>
        <li class="collect" id="myCollect">我的收藏<a></a></li>
        <%--<li class="activity" style="border: none;">我的活动<a href="/member/myActivity.htm?id=${memberUser.id}"></a></li>--%>
        <li class="kong"></li>
        <li class="set" id="mySet">我的设置<a></a></li>
        <c:choose>
         <c:when test="${flag==1}"></c:when>
            <c:when test="${flag==2}">
                <li class="activity" id="coachInformation">教练信息<a></a></li>
                <li class="activity"  id="mycourselist">我的课程信息<a></a></li>
            </c:when>
            <c:when test="${flag==3}">
                <li class="activity" id="mySite">主场馆信息<a></a></li>
                <li class="activity" id="otherSite">我的场馆列表<a></a></li>
            </c:when>
        </c:choose>
        <li class="password" id="updatePassword">修改密码<a></a></li>
        <li class="exit" id="logout">退出登录<a></a></li>
    </ul>

    <ol class="b" id = "show1">
        <li class="right"><a href="/member/myOrder.htm?id=${memberUser.id}&role=Member">买家</a></li>
        <li class="left"><a href="/member/order.htm?id=${memberUser.id}&role=Shop">卖家</a></li>
    </ol>

</div>
<div class="tb">
    <ul>
        <li><a href=""><i><img src="<%=uiPath%>site/images/index_logo.png" alt="" /></i><br />首页</a></li>
        <li><a href="/venue/venueListInit.htm"><i><img src="<%=uiPath%>site/images/site_logo.png" alt="" /></i><br />约场馆</a></li>
        <li><a href="/coach/coachListInit.htm"><i><img src="<%=uiPath%>site/images/train_logo.png" alt="" /></i><br />约教练</a></li>
        <li><a href="/activity/activityListInit.htm"><i><img src="<%=uiPath%>site/images/friend_logo.png" alt="" /></i><br />约好友</a></li>
        <li class="cur"><a href="/member/showMemberInfo.htm"><i><img src="<%=uiPath%>site/images/my_act.png" alt="" /></i><br />我的</a></li>
    </ul>
</div>
</body>

<script>

    $(function(){
        var oW=$(document.body).width()+"px";
        $(".tb").css("width",oW);
        $("#click1").click(function(){
            if($("#show1").css("display")=="none"){
                $("#show1").css("display","block");
            }else{
                $("#show1").css("display","none");
            }
        })
    });

    $(function(){
        $("#show1 li").each(function(){
            $(this).click(function(){
                $("#show1 li").each(function(){
                    $(this).removeClass('onlick');
                });
                $(this).addClass("onlick");
            })
        })
    });
    $(function () {
        $("#myWallet").click(function () {
            location.href="/member/myWallet.htm?id=${memberUser.id}";
        });
        $("#myCollect").click(function () {
            location.href="/member/myCollect.htm?id=${memberUser.id}&status_=${status_}";
        });
        $("#mySet").click(function () {
            location.href="/member/mySet.htm";
        });
        $("#coachInformation").click(function () {
            location.href="coach/coachInformation.htm"
        });
        $("#mycourselist").click(function () {
            location.href="coach/mycourselist.htm?memberId=${memberUser.id}"
        });
        $("#updatePassword").click(function () {
            location.href="/member/updatePasswordInit.htm?id=${memberUser.id}";
        });
        $("#mySite").click(function () {
            location.href="/venue/mySite.htm?id=${memberUser.id}";
        });
        $("#otherSite").click(function () {
            location.href="/venue/otherSite.htm?id=${memberUser.id}";
        });
        $("#logout").click(function () {
            if(!confirm("确定退出登录？")){
                return;
            }else {
                location.href="/member/logout.htm";
            }
        });
    });
</script>
</html>