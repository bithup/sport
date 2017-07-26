<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>场馆确认订单</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/confirm_cg.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var memberId='${memberId}';
        var cuttings='${cuttings}';
        var selectDate='${selectDate}';
        $(function () {
            $(".button").on("click",function () {
                var telephone=$("#tel").val();
                var contact=$("#name").val();
                if(contact==null||contact==''){
                    new Toast({message:'联系人不能为空！'}).show();
                    return;
                }
                if(telephone==null||telephone==''){
                    new Toast({message:'联系人电话不能为空！'}).show();
                    return;
                }
                if(!(/^1(3|4|5|7|8)\d{9}$/.test($("#tel").val()))){
                    new Toast({message:'请输入正确的手机号码'}).show();
                    $("#tel").val("");
                    $("#tel").focus();
                    return;
                }

                $.ajax({
                    url:"/order/createVenueOrder.htm",
                    type:"POST",
                    data:{memberId:memberId,cuttings:cuttings,selectDate:selectDate,contact:contact,telephone:telephone},
                    async:false,
                    dataType:"json",
                    success:function (data) {
                       if(data.resultFlg==1){
                           location.href="/pay/payWay.htm?orderAmount=${totalPrice}&orderId="+data.resultData;
                       }
                    }

                });

            });
        });

    </script>
</head>
<body>
<div class="content">
    <div class="head">
        <div class="left"><a href="javascript:history.back()"><img src="<%=uiPath%>site/images/return.png" alt="" /></a>
        </div>
        确认订单
    </div>

    <div class="xq">
        <span>${kn}</span>${kindsName}
        <p>地址：<b class="xq_map">${address}</b></p>
       <c:forEach items="${cutList}" var="item">
           <p>场次：<b>${item.no}号场</b></p>
           <p>时间：<b><fmt:formatDate value="${date}" pattern="MM月dd日"></fmt:formatDate> &nbsp${week}&nbsp ${item.tp}</b></p>
       </c:forEach>
        <p>联系人：<input type="text" id="name"> </p>
        <p>电话：<input type="text" id="tel"></p>
        <p>订单总额：<i>¥${totalPrice}</i></p>
    </div>
    <div class="button">立即支付</div>

</div>

</body>
</html>