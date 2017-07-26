<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>订单详情</title>
    <base href="<%=basePath%>">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=uiPath%>site/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/train_dd.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <!-- 标题 -->
    <div class="log_tit">
        <i onclick="javascript:history.go(-1);"></i>
        订单详情
    </div>
    <!-- 正文开始 -->
    <div class="train_intro">
        <img src="${map.picRealPath}" alt="">
        <div class="intro_wrap">
            <c:choose>
                <c:when test="${map.orderType==0}">
                    <span class="train_name">${map.goodsName}</span>
                    <span>${map.zoneName}</span>
                    <span class="other_col">价格：￥${map.salesPrice}</span>
                </c:when>
                <c:when test="${map.orderType==1}">
                    <span class="train_name">${map.coachName}&nbsp&nbsp&nbsp${map.sex eq 1 ? "男" : "女"}</span>
                    <span>${map.intro}</span>
                    <span class="other_col">${map.sportName}</span>
                </c:when>
                <c:when test="${map.orderType==2}">
                    <span class="train_name">${map.goodsName}</span>
                    <span>${map.activityAddress}</span>
                    <span class="other_col">
                        <c:choose>
                            <c:when test="${map.isFree==0}">￥${map.orderAmount}</c:when>
                            <c:when test="${map.isFree==1}">免费</c:when>
                            <c:when test="${map.isFree==2}">AA制</c:when>
                        </c:choose>
                    </span>
                </c:when>
            </c:choose>
        </div>
    </div>
    <div class="deal_de">
        <ul>
            <li>
                <div>
                    <p class="other_p">订 单 号：<b>${map.orderNo}</b></p>
                    <i>
                        <c:choose>
                            <c:when test="${map.orderStatus==0}">待支付</c:when>
                            <c:when test="${map.orderStatus==1}">待开始</c:when>
                            <c:when test="${map.orderStatus==2}">待评价</c:when>
                            <c:when test="${map.orderStatus==3}">已完成</c:when>
                            <c:when test="${map.orderStatus==4}">交易取消</c:when>
                        </c:choose>
                    </i>
                    <p>交 易 号：<b>${map.tradeNo}</b></p>
                </div>
            </li>
            <li>
                <p>姓 名：<b>${map.contact}</b></p>
                <p>联系方式：<b>${map.telephone}</b></p>
            </li>
            <c:choose>
                <c:when test="${map.orderType==0}">
                    <li>

                        <c:forEach items="${map.cuttingList}" var="item">
                            <p class="flex_wrap"><b>${item.venueNo}</b><b>${item.selectDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.timePeriods}</b><b class="other_col">￥${item.venueAmount}</b></p>
                        </c:forEach>
                    </li>
                </c:when>
                <c:when test="${map.orderType==1}">
                    <li>
                        <p>课程名称：<b>${map.goodsName}</b></p>
                        <p>课程类型：<b>
                            <c:choose>
                                <c:when test="${map.courseType==1}">月</c:when>
                                <c:when test="${map.courseType==2}">季</c:when>
                                <c:when test="${map.courseType==3}">年</c:when>
                            </c:choose>

                        </b></p>
                    </li>
                </c:when>
                <c:when test="${map.orderType==2}">
                    <li>
                        <p>开始时间：<b><fmt:formatDate value="${map.startDate}" pattern="yyyy-MM-dd HH:mm:SS"></fmt:formatDate></b></p>
                        <p>结束时间：<b><fmt:formatDate value="${map.endDate}" pattern="yyyy-MM-dd HH:mm:SS"></fmt:formatDate></b></p>
                    </li>
                </c:when>
            </c:choose>

            <li>
                <p>成交时间：<b>${map.closingTime}</b></p>
                <p>付款时间：<b><fmt:formatDate value="${map.payTime}" pattern="yyyy-MM-dd HH:mm:SS"></fmt:formatDate></b></p>
                <p>确认时间：<b>${map.confirmTime}</b></p>
                <p>实付款：<b class="other_col">
                    <c:choose>
                        <c:when test="${map.orderType==2}">
                            <c:choose>
                                <c:when test="${map.isFree==0}">￥${map.orderAmount}</c:when>
                                <c:when test="${map.isFree==1}">免费</c:when>
                                <c:when test="${map.isFree==2}">AA制</c:when>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            ￥${map.orderAmount}
                        </c:otherwise>
                    </c:choose>
                </b></p>
            </li>
        </ul>
    </div>
</div>
</body>
</html>