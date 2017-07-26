<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta charset="UTF-8">
    <title>${sportName}</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/index_yu.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/dropload.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.easing.min.js"></script>
    <script src="<%=uiPath%>site/js/dropload.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript">
        var page=1;
        var pageSize=80;
        var url='';
        var sportId=${sportId};
        //查询条件：运动项目、区域范围、排序方式(场馆：大小、价格);年龄、教练性别;收费方式
        var searchCondition=${searchCondition};
        var condition=${condition};
        //商品类别 1：场馆，2：教练，3：活动
        var goodsType=${goodsType};

        //根据商品类型和查询条件获取请求数据的url
        function getUrl(goodsType,searchCondition) {
            switch(goodsType){
                case 1:
                    //场馆按运动类型（id）查询
                    if(condition==1){
                        url="/venue/getVenueBySportId.htm?kindsId="+sportId+"&page="+page+"&pageSize="+pageSize;
                        return;
                    }
                    //场馆按区域排序
                    if(condition==2){url="/venue/getVenueByZone.htm?kindsId="+sportId+"&zoneId="+searchCondition+"&page="+page+"&pagesize="+pageSize;return;}
                    //场馆按价格排序
                    if(condition==4){url="/venue/getVenueByPrice.htm?kindsId="+sportId+"&page="+page+"&pagesize="+pageSize;return;}
                    //场馆按大小排序
                    if(condition==3){url="/venue/getVenueBySize.htm?kindsId="+sportId+"&page="+page+"&pagesize="+pageSize;return;}
                    break;
                case 2:
                    //教练按运动类型（id）查询
                    if(condition==1){url="/coach/getCoachBySportId.htm?sportId="+sportId+"&page="+page+"&pageSize="+pageSize;return;}
                    //教练按年龄排序
                    if(condition==2){url="/coach/getCoachByAge.htm?kindsId="+sportId+"&page="+page+"&pagesize="+pageSize;return;}
                    //教练按性别排序
                    if(condition==3){url="/coach/getCoachBySex.htm?kindsId="+sportId+"&sex="+searchCondition+"&page="+page+"&pagesize="+pageSize;return;}
                    break;
                case 3:
                    //活动按运动类型（id）查询
                    if(condition==1){url="/activity/getActivityBySportId.htm?sportId="+sportId+"&page="+page+"&pageSize="+pageSize;return;}
                    //活动按区域范围查询
                    if(condition==2){url="/activity/getActivityByZoneId.htm?kindsId="+sportId+"&zoneId="+searchCondition+"&page="+page+"&pageSize="+pageSize;return;}
                    //活动按收费方式查询
                    if(condition==3){url="/activity/getActivityByIsFree.htm?kindsId="+sportId+"&isFree="+searchCondition+"&page="+page+"&pageSize="+pageSize;return;}
                    break;
            }
        }
        //请求数据
        function reqData(url) {
            $.ajax({
                type:"POST",
                url:url,
                dataType:"json",
                success:function (data) {
                    var result=getHtml(data);
                    $('.show').html(result);
                }
            });
        }
        //将JSON数据拼接成HTML
        function getHtml(data) {
            var result="";
            if(goodsType==1){
                $(data).each(function () {
                    if(this.isRefund==1){
                        result+="<li><a href='/venue/venueDetail.htm?id="+this.id+"'>"+
                            "<h3><img src='"+this.picRealPath+"'/></h3>"+
                            "<p>"+
                            "<b><i class='rejected'>退</i><i>"+this.venueName+"</i></b><br/>"+
                            "<span>"+this.zoneName+"</span>"+
                            "<i>￥"+this.price+"</i>"+
                            "</p>"+
                            "</a></li>";
                    }else {
                        result+="<li><a href='/venue/venueDetail.htm?id="+this.id+"'>"+
                            "<h3><img src='"+this.picRealPath+"'/></h3>"+
                            "<p>"+
                            "<b><i>"+this.venueName+"</i></b><br/>"+
                            "<span>"+this.zoneName+"</span>"+
                            "<i>￥"+this.price+"</i>"+
                            "</p>"+
                            "</a></li>";
                    }
                });
            }else if(goodsType==2){
                $(data).each(function () {
                    if(this.sex==1){
                        result+="<li><a href='/coach/coachDetail.htm?coachId="+this.id+"'>" +
                            "<h3><img src='"+this.picRealPath+"'/></h3>" +
                            "<p>" +
                            "<b>"+this.name+"</b><b class='sex_bar'>男</b><br />" +
                            "<span>"+this.intro+"</span>" +
                            "<i>"+this.kindsName+"</i>" +
                            "</p></a>" +
                            "</li>";
                    }else {
                        result+="<li><a href='/coach/coachDetail.htm?coachId="+this.id+"'>" +
                            "<h3><img src='"+this.picRealPath+"'/></h3>" +
                            "<p>" +
                            "<b>"+this.name+"</b><b class='sex_bar'>女</b><br />" +
                            "<span>"+this.intro+"</span>" +
                            "<i>"+this.kindsName+"</i>" +
                            "</p></a>" +
                            "</li>";
                    }

                });
            }else if(goodsType==3){
                $(data).each(function () {
                    result+="<li><a href='/activity/activityDetail.htm?activityId="+this.id+"'>" +
                        "<h3><img src='"+this.activityRealPath+"'/></h3>" +
                        "<p>" +
                        "<b>"+this.activityName+"</b><br />" +
                        "<span>"+this.zoneName+"</span><i>￥"+this.activityPrice +
                        "</i>" +
                        "</p>" +
                        "</a></li>";
                });
            }
            return result;
        }

        $(function () {
            getUrl(goodsType,searchCondition);
            reqData(url);
            if(goodsType==1){
                $("#li-venue").addClass("cur");
            }else if(goodsType==2){
                $("#li-coach").addClass("cur");
            }else if(goodsType==3){
                $("#li-activity").addClass("cur");
            }
            $(".list>ul>li").click(function(){
                var index=$(this).index();
                $(".list>ul>li").eq(index).addClass("cur").siblings().removeClass("cur");
                goodsType=index+1;
                condition=1;
                getUrl(goodsType,searchCondition);
                reqData(url);
            });

            //下拉框动态效果
            var num;
            $('.nav_main>li[id]').hover(function(){
                /*下拉框出现*/
                var Obj = $(this).attr('id');
                num = Obj.substring(3, Obj.length);
                $('#box'+num).slideDown(300);
                $(this).addClass("cur").siblings().removeClass("cur");
            },function(){
                /*下拉框消失*/
                $('#box'+num).hide();
            });
            $('.hidden_box').hover(function(){
                $(this).show();
            },function(){
                $(this).slideUp(200);
            });

        });
        //教练按年龄查询
        function getCoachByAge() {
        condition=2;
        getUrl(2,searchCondition);
        reqData(url);
        }

    </script>

</head>
<body>
<div class="content">
    <div class="head">
        <div class="fh"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
        ${sportName}
    </div>
    <div class="list">
        <ul class="nav_main">
            <li id="li-venue">场馆<img src="<%=uiPath%>site/images/down.png" alt=""></li>
            <li id="li-coach">教练<img src="<%=uiPath%>site/images/down.png" alt=""></li>
            <li id="li-activity">活动<img src="<%=uiPath%>site/images/down.png" alt=""></li>
        </ul>
        <!-- 场馆菜单 -->
        <div class="changguan_memu hidden_box" id="boxvenue">
            <ul>
                <li><a href="/kinds/otherKinds.htm?goodsType=1">运动项目</a></li>
                <li class="zone" id="1"><a>区域范围</a></li>
                <li><a href="/kinds/index_fp.htm?sportId=${sportId}&sportName=${sportName}">排序方式</a></li>
            </ul>
        </div>
        <!-- 教练菜单 -->
        <div class="jiaolian_memu hidden_box" id="boxcoach">
            <ul>
                <li onclick="getCoachByAge()"><a>教练年龄</a></li>
                <li><a href="/kinds/trainer_xb.htm?sportId=${sportId}&sportName=${sportName}">教练性别</a></li>
            </ul>
        </div>
        <!-- 活动菜单 -->
        <div class="huodong_memu hidden_box" id="boxactivity">
            <ul>
                <li><a href="/kinds/otherKinds.htm?goodsType=3">运动项目</a></li>
                <li class="zone" id="3"><a>区域范围</a></li>
                <li><a href="/kinds/activity_px.htm?sportId=${sportId}&sportName=${sportName}">收费标准</a></li>
            </ul>
        </div>
    </div>

    <div class="detail_wrap">
        <div class="pic">
            <ul class="show">

            </ul>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $(".zone").on("click",function () {
            location.href="/kinds/index_zone.htm?sportId=${sportId}&sportName=${sportName}&cityName="+localStorage.getItem("cityName")+"&goodsType="+this.id;
        });
    });
</script>
</html>