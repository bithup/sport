<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<%--场馆详情--%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>场馆详情</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/index_xq.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var type=0;
        var dataId=${venueDetail.id};
        var isLogin=${isLogin};
        var flag=${flag};
        var type_ = '${type_}';
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
        });


    </script>
</head>
<body>
<div class="content">
    <div class="head">
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

        场馆详情
        <div class="coll collect" value="0"></div>
        <div class="coll"><a href=""><img src="<%=uiPath%>site/images/share01.png" alt="" /></a> </div>
    </div>

    <div class="pic">
        <h1><img src="${venueDetail.picRealPath}" alt="" /></h1>
        <div class="pic_content">
            <p>${venueDetail.venueName}</p>
            <c:choose>
                <c:when test="${venueDetail.isRefund==1}"><p>提前${venueDetail.refundDeadline}小时可退款</p></c:when>
            </c:choose>

        </div>
    </div>
    <div class="font">注意：${venueDetail.tips}</div>
    <div class="evaluate_wrap" >
        <!--判断 remarkList是否有值，为空时显示暂无评价-->
        <c:choose>
            <c:when test="${empty remarkList}">暂无评价</c:when>
            <c:otherwise>
                <c:forEach items="${remarkList}" var="item">
                    <span>${item}</span>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <!--销量-->
    <div class="evaluate_wrap">
        销量：${venueDetail.data5}
    </div>
    <div class="font font_yu">
        <i>${venueDetail.kindsName}</i>
    </div>

    <div class="box">
        <ul class="week">
            <%--<li class="cur"><span>今天</span>6月16日<i><a href="./film_xz.html">预订</a></i></li>--%>
            <c:forEach items="${weekList}" var="item">
                <li><span>${item.week}</span>${item.date}<i><a href="/childVenueCutting/getVenueCutting.htm?id=${venueDetail.id}&selectDate=${item.rightDate}&kindsName=${venueDetail.kindsName}&address=${venueDetail.address}">预订</a></i></li>
            </c:forEach>
        </ul>
    </div>

    <ul class="phone wrap">
        <li><img src="<%=uiPath%>site/images/phone.png" alt="" /><span>${venueDetail.telephone}</span></li>
        <li class="address"><img src="<%=uiPath%>site/images/map.png" alt="" /><span>${venueDetail.address}</span></li>

    </ul>



    <div class="info wrap">
        <ul>
            <li>
                <p class="bold traffic">交通信息</p>
                <p>公交 <span>${venueDetail.busInfo}</span></p>
                <p>地铁<span>${venueDetail.subwayInfo}</span></p>
            </li>
            <li>
                <p class="bold facilities">场地设施</p>
                <p>${venueDetail.hardware}</p>
                <%--<p>未处理地板 <span>塑胶地板</span></p>--%>
                <%--<p>灯光 <span>侧灯</span></p>--%>
            </li>
            <li>
                <p class="bold flag">场馆服务</p>
                <p>${venueDetail.serviceInfo}</p>
                <%--<p>器材租借  <span>储物柜</span></p>--%>
                <%--<p>器材维护  <span>拉线</span></p>--%>
                <%--<p>更多服务  <span>WIFI、支持刷卡、会员卡、专业培训</span></p>--%>
                <%--<p>场馆卖品  <span>饮料</span></p>--%>
                <%--<p>洗浴设施  <span>更衣室</span></p>--%>
                <%--<p>停车  <span>免费停车</span></p>--%>
            </li>
        </ul>
    </div>
    <div class="evaluate_wrap" >实景视图</div>
    <div class="next_wrap">
    <ul class="next">
    <c:forEach items="${fileDataList}" var="item">
    <li>
    <img src="${item.url}"/>

    </li>
    </c:forEach>
    </ul>
    </div>
</div>
</body>
</html>