<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>约好友</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/friend.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/dropload.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/dropload.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <div class="head">
        <i><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></i>
        约好友
        <span><a href="/activity/activitySearchInit.htm"><img src="<%=uiPath%>site/images/search.png" alt="" /></a></span>
    </div>
    <!--导航开始-->
    <div class="list">
        <ul>
            <li class="cur" id="">全部</li>
            <c:forEach items="${headKindsList}" var="item">
                <li id="${item.id}">${item.name}</li>
            </c:forEach>
            <li id="other"><a href="/kinds/otherKinds.htm?flg=act">其他</a></li>
        </ul>
    </div>
    <!--详情开始-->
    <div class="pic">
        <ul class="show">

        </ul>
    </div>

    <!--底部开始-->
    <div class="tb">
        <ul>
            <li><a href=""><i><img src="<%=uiPath%>site/images/index_logo.png" alt="" /></i><br />首页</a></li>
            <li><a href="/venue/venueListInit.htm"><i><img src="<%=uiPath%>site/images/site_logo.png" alt="" /></i><br />约场馆</a></li>
            <li><a href="/coach/coachListInit.htm"><i><img src="<%=uiPath%>site/images/train_logo.png" alt="" /></i><br />约教练</a></li>
            <li class="cur"><a href="/activity/activityListInit.htm"><i><img src="<%=uiPath%>site/images/friend_act.png" alt="" /></i><br />约好友</a></li>
            <li><a href="/member/showMemberInfo.htm"><i><img src="<%=uiPath%>site/images/my_logo.png" alt="" /></i><br />我的</a></li>
            <br style="clear: both;"/>

        </ul>
    </div>
</div>
<script>
    //存放当前栏目运动类型id
    var sportId="${sportId}";
    var page=1;
    var pageSize=80;
    //请求后台数据
    function reqData(op) {
        if("up"==op){page++;}
        if("down"==op){page=1;}
        var result='';
        $.ajax({
            type:'POST',
            url:'/activity/getActivityBySportId.htm',
            data:{sportId:sportId,page:page,pageSize:pageSize},
            dataType:'json',
            success:function (data) {
                var length=data.length;
                $(data).each(function () {
                    if(this.isFree==0){
                        result+="<li><a href='/activity/activityDetail.htm?activityId="+this.id+"'>" +
                            "<h3><img src='"+this.activityRealPath+"'/></h3>" +
                            "<p>" +
                            "<b>"+this.activityName+"</b><br />" +
                            "<span>"+this.zoneName+"</span><i>￥"+this.activityPrice +
                            "</i>" +
                            "</p>" +
                            "</a></li>";
                    }
                    if(this.isFree==1){
                        result+="<li><a href='/activity/activityDetail.htm?activityId="+this.id+"'>" +
                            "<h3><img src='"+this.activityRealPath+"'/></h3>" +
                            "<p>" +
                            "<b>"+this.activityName+"</b><br />" +
                            "<span>"+this.zoneName+"</span><i class='spe_status'>"+"免费" +
                            "</i>" +
                            "</p>" +
                            "</a></li>";
                    }
                    if(this.isFree==2){
                        result+="<li><a href='/activity/activityDetail.htm?activityId="+this.id+"'>" +
                            "<h3><img src='"+this.activityRealPath+"'/></h3>" +
                            "<p>" +
                            "<b>"+this.activityName+"</b><br />" +
                            "<span>"+this.zoneName+"</span><i class='spe_status'>"+"AA制"+
                            "</i>" +
                            "</p>" +
                            "</a></li>";
                    }

                });
                if(op=="up"){
                    $('.show').append(result);
                }else {
                    $('.show').html(result);
                }
            }
        });

    }
    $(function(){
        var oW=$(document.body).width()+"px";
        $(".tb").css("width",oW);
        //第一次访问请求数据
        if(sportId!=""){
            $(".cur").removeClass("cur");
            $("#other").addClass("cur");
        }
        reqData("down");
        //选择不同运动
        $(".list>ul>li").click(function(){
            var i=$(this).index();
            $(".list>ul>li").eq(i).addClass("cur").siblings().removeClass("cur");
            sportId=this.id;
            if(this.id=="other"){return;}
            reqData("down");
        });
        //滑动操作

    })
</script>
</body>
</html>