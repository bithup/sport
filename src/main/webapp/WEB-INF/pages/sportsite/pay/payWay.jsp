<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>支付方式</title>
    <base href="<%=basePath%>">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <link rel="stylesheet" href="<%=uiPath%>site/css/payway.css">
    <link rel="stylesheet" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <div class="payway_title">
        <span>付款</span>
        <div class="left"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
    </div>
    <div class="payway_content">
        <p>需要支付</p>
        <b class="pay_m">￥${orderAmount}</b>
        <ul class="sel_payway">选择支付方式
            <li class="alipay payway_active">支付宝支付
                <img src="<%=uiPath%>site/images/alipay.png" alt="">
            </li>
            <%--<li class="cardpay">银联卡支付--%>
                <%--<img src="<%=uiPath%>site/images/cardpay.png" alt="">--%>
            <%--</li>--%>
        </ul>
        <div class="pay_btn"><a>确认支付</a></div>
    </div>
</div>
<script>
    var flag=0;
    $(function(){
        $(".sel_payway>li").click(function(){
            var index=$(this).index();
            flag=index;
            $(".sel_payway>li").eq(index).addClass("payway_active").siblings().removeClass("payway_active");
        })
    });
    $(function () {
        $(".pay_btn").click(function () {
            var url="";
            if(flag==0){
                url="/alipay/alipayMoney.htm?orderId=${orderId}";
            }
            if(flag==1){
            }
            location.href=url;
        })
    });
</script>
</body>
</html>