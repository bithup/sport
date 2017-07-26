<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>活动详情</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/friend_xq.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var dataId=${activity.id};
        var isLogin=${isLogin};
        var flag=${flag};
        var type=2;
        $(function(){
            if(flag==1){$(".collect").addClass("collect01");}
            $(".collect").click(function(){
                if(isLogin==0){location.href="/portal/loginInit.htm"}
                else {
                    $.ajax({
                        url:"/house/addToHouse.htm",
                        type:"POST",
                        data:{type:type,dataId:dataId},
                        dataType:"json",
                        success:function (data) {
                            var jsonReturn=eval(data);
                            if(jsonReturn.resultFlg==1){
                                new Toast({message:jsonReturn.resultMsg}).show();
                            }else if(jsonReturn.resultFlg==2){
                                new Toast({message:jsonReturn.resultMsg}).show();
                            }else{
                                new Toast({message:'操作失败'}).show();
                                return;
                            }
                            $(".collect").toggleClass("collect01");
                            if(flag==0){
                                $(".collect").attr("value",1);
                                flag=1;
                            }else{
                                $(".collect").attr("value",0);
                                flag=0;
                            }
                        }
                    });
                }

            })
        })
    </script>
</head>
<body>
<!--头部开始-->
<div class="content wrap">
    <div class="head">
        <div class="return">
            <c:choose>
                <c:when test="${empty type_ and empty status_}">
                    <i><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></i>
                </c:when>
                <c:when test="${!empty type_ and empty status_}">
                    <i><a href="/home/baseInfo.htm?type_=${type_}"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></i>
                </c:when>
                <c:when test="${empty type_ and !empty status_}">
                    <i><a href="/member/myCollect.htm?status_=${status_}"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></i>
                </c:when>
            </c:choose>
        </div>
        活动详情
        <div class="coll collect" value="0"></div>
        <div class="coll"><a href=""><img src="<%=uiPath%>site/images/share01.png" alt="" /></a> </div>
    </div>
    <!--图片开始-->
    <div class="pic">
        <img src="${activity.activityRealPath}" alt=""/>
        <div class="name wrap">
            <p>${activity.activityName}</p>
            <p>活动人数：${activity.activityCount}人</p>
        </div>
    </div>
    <!--详情开始-->
    <div class="detail">
        <input type="hidden" value="${activity.id}" id="goodsId">

        <ul>
            <li>活动时间：<p><fmt:formatDate value="${activity.startDate}" type="both" pattern="yyyy-MM-dd  HH:mm:ss"/>至<fmt:formatDate value="${activity.endDate}" type="both" pattern="yyyy-MM-dd  HH:mm:ss"/></p></li>
            <li>活动地点：<p>${activity.activityAddress}</p></li>
            <li>相关介绍：<p>${activity.activityIntroduce}</p></li>
            <li>报名费用：<c:choose>
                <c:when test="${activity.isFree==0}"><p>￥${activity.activityPrice}</p></c:when>
                <c:when test="${activity.isFree==1}"><p>免费</p></c:when>
                <c:when test="${activity.isFree==2}"><p>AA制</p></c:when>
            </c:choose><span class="yuyue"><a href="/activity/confirm_hd.htm?activityId=${activity.id}">预约</a></span></li>
            <li>报名截止日期：<p><fmt:formatDate value="${activity.endDate}" type="both" pattern="yyyy-MM-dd  HH:mm:ss"/></p></li>
            <li>咨询电话：${activity.contactsPhone}</li>
        </ul>
    </div>
    <div class="evaluate_wrap" >
        <!--判断 remarkList是否有值，为空时显示暂无评价-->
        <c:choose>
            <c:when test="${empty remarkList}">暂无评价</c:when>
            <c:otherwise>
                <c:forEach items="${remarkList}" var="item">
                    <span>${item}%</span>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <!--销量-->
    <div class="evaluate_wrap" >
        销量：${enrollCount}
    </div>
    <!-- 实景图展示开始	 -->
    <div class="pic_show">
        <c:forEach items="${fileData}" var="item">
           <img src="${item.url}" alt=""/>
       </c:forEach>
   </div>
</div>
</body>
</html>