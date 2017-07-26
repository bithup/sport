<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>首页</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/index.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/yxMobileSlider.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript">
        var type_ = '${type_}';
        $(function(){
            var oW=$(document.body).width()+"px";
            $(".tb").css("width",oW);
            $("#venue").click(function(){
                $("#venue").parent("li").addClass("cur");
                $("#venue").parent("li").siblings().removeClass("cur");
                $(".pic").eq(0).show();
                $(".pic").eq(1).hide();
                $(".pic").eq(2).hide();
            });
            $("#coach").click(function(){
                $("#coach").parent("li").addClass("cur");
                $("#coach").parent("li").siblings().removeClass("cur");
                $(".pic").eq(1).show();
                $(".pic").eq(0).hide();
                $(".pic").eq(2).hide();
            });
            $("#activity").click(function(){
                $("#activity").parent("li").addClass("cur");
                $("#activity").parent("li").siblings().removeClass("cur");
                $(".pic").eq(2).show();
                $(".pic").eq(1).hide();
                $(".pic").eq(0).hide();
            });
            $(".center_search").focus(function(){
                location.href="/index/indexSearchInit.htm";
            })

            if(type_==1){
                $('#venue').trigger("click");
            }else if(type_==2){
                $('#coach').trigger("click");
            }
            else if(type_==3){
                $('#activity').trigger("click");
            }

        });

    </script>

</head>
<body>
<!--头部-->
<div class="content">
    <div class="head">
        <p><a href="/zone/switchZone.htm">[&nbsp${zoneName}&nbsp]</a></p>
        <div class="center">
            <span><img src="<%=uiPath%>site/images/images/soso.png" alt=""/></span>
            <input type="text" name="" value="" class="center_search">
        </div>
        <div class="per">
            <a href="/member/showMemberInfo.htm"><img src="<%=uiPath%>site/images/images/wo.png" alt=""/></a>
        </div>
    </div>
    <!--banner-->
    <div class="slider">
            <ul>
        <c:forEach items="${list}" var="var" varStatus="status">
          <c:choose>
              <c:when test="${not empty var.subjectUrl}"><li class="cur"><a href="${var.subjectUrl}"><img src="${var.relativePath}"></a></li></c:when>
              <c:otherwise><li><a ><img src="${var.relativePath}" ></a></li></c:otherwise>
          </c:choose>
        </c:forEach>
            </ul>
    </div>
    <script type="text/javascript">
        $(".slider").yxMobileSlider({width:640,height:300,during:3000});
    </script>

    <!--图标开始-->
    <div class="icon wrap">
        <ul>
            <c:forEach items="${kindsList}" var="item">
                <li><a href="/kinds/kindIndex.htm?goodsType=1&condition=1&id=${item.id}&name=${item.name}"><i><img src="${item.sportRealUrl}"/></i>${item.name}</a></li>
            </c:forEach>
            <li><a href="/kinds/otherKinds.htm?goodsType=1"><i><img src="<%=uiPath%>site/images/icon_8.png"/></i>更多</a></li>

        </ul>
    </div>
    <!--热门开始-->
    <div class="hot wrap">
        <ul>
            <li class="cur"><a href="javascript:;" id="venue">热门场馆</a></li>
            <li><a href="javascript:;" id="coach">热门教练</a></li>
            <li><a href="javascript:;" id="activity">热门活动</a></li>
        </ul>
    </div>

    <!--热门场馆-->
    <div class="pic">
        <ul>
            <c:forEach items="${venueList}" var="item" >
                    <li>
                        <a href="/venue/venueDetail.htm?id=${item.id}&type_=1">
                        <h3><img src="${item.picRealPath}" alt="" /></h3>
                        <p>
                            <b><c:if test="${item.isRefund==1}"><i class="rejected">退</i></c:if><i>${item.venueName}</i></b><br/>
                            <span>${item.zoneName}</span>
                            <i>￥${item.price}</i>
                        </p>
                        </a>
                    </li>
            </c:forEach>
        </ul>
    </div>
    <!--热门教练-->
    <div class="pic" style="display: none">
        <ul class="show">
            <c:forEach items="${coachList}" var="item">
                <li>
                    <a href="/coach/coachDetail.htm?coachId=${item.id}&type_=2">
                    <h3><img src="${item.headRealPath}" alt="" /></h3>
                    <p>
                        <b>${item.coachName}</b><b class="sex_bar">
                        <c:choose>
                            <c:when test="${item.sex==0}">男</c:when>
                            <c:when test="${item.sex==1}">女</c:when>
                        </c:choose>
                        </b><br />
                        <span>${item.intro}</span>
                        <i><a>${item.kindsName}</a></i>
                    </p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!--热门活动-->
    <div class="pic" style="display: none">
        <ul>
            <c:forEach items="${activityList}" var="item">
                <li>
                    <a href="/activity/activityDetail.htm?activityId=${item.id}&type_=3">
                    <h3><img src="${item.activityRealPath}" alt="" /></h3>
                    <p>
                        <b>${item.activityName}</b><br /><!--热门活动显示的应该详情页的活动名称，而不是运动类型-->
                        <span>${item.zoneName}</span>
                        <c:choose>
                            <c:when test="${item.isFree==0}">
                                <i>￥${item.activityPrice}</i>
                            </c:when>
                            <c:when test="${item.isFree==1}">
                                <i>免费</i>
                            </c:when>
                            <c:when test="${item.isFree==2}">
                                <i>AA制</i>
                            </c:when>
                        </c:choose>
                    </p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <!--我有创意-->
    <div class="con_db">
        <ul>
            <li><a href="/sportsite/recommend/init.htm?kind=1"><i><img src="<%=uiPath%>site/images/icons05.png"></i><br/>我有好创意</a></li>
            <li><a href="/sportsite/recommend/init.htm?kind=2"><i><img src="<%=uiPath%>site/images/icons06.png"></i><br/>我想挑毛病</a></li>
            <li><a href="/sportsite/recommend/init.htm?kind=3"><i><img src="<%=uiPath%>site/images/icons07.png"></i><br/>我吐槽一下</a></li>
            <li><a href="http://www.fangshangqu.com/"><i><img src="<%=uiPath%>site/images/icons08.png"></i><br/>去源头看看</a></li>
            <br style="clear: both;"/>
        </ul>
    </div>
    <!--图标开始-->
    <div class="tb">
        <ul>
            <li class="cur"><a href=""><i><img src="<%=uiPath%>site/images/index_act.png" alt="" /></i><br />首页</a></li>
            <li><a href="/venue/venueListInit.htm"><i><img src="<%=uiPath%>site/images/site_logo.png" alt="" /></i><br />约场馆</a></li>
            <li><a href="/coach/coachListInit.htm"><i><img src="<%=uiPath%>site/images/train_logo.png" alt="" /></i><br />约教练</a></li>
            <li><a href="/activity/activityListInit.htm"><i><img src="<%=uiPath%>site/images/friend_logo.png" alt="" /></i><br />约好友</a></li>
            <li><a href="/member/showMemberInfo.htm"><i><img src="<%=uiPath%>site/images/my_logo.png" alt="" /></i><br />我的</a></li>
            <br style="clear: both;"/>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $(".head p a").html("[&nbsp"+localStorage.getItem("cityName")+"&nbsp]");
    })
</script>
</html>