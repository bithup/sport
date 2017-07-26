<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>教练确认订单</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/confirm_jl.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
    $(function () {
        $(".button").click(function () {
            if(!(/^1(3|4|5|7|8)\d{9}$/.test($(".reg_sj>input").val()))){
                alert("请输入正确的手机号码！");
                $(".reg_sj>input").val("");
                $(".reg_sj>input").focus();
                return;
            }
            var memberId=;
            var contact=;
            var telephone=;
            var goodsId=;
            var orderAmount=;
            $.ajax({
                type:"POST",
                url:"/coach/createCoachOrder.htm",
                data:{memberId:memberId,contact:contact,telephone:telephone,goodsId:goodsId,orderAmount:orderAmount},
                dataType:"json",
                success:function (data) {
                    var jsonReturn=eval('('+data+')');
                    alert(jsonReturn.resultMsg);
                }
            });
        });
    });

</script>
</head>
<body>
<div class="content">
    <div class="head">
        <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a>
        确认订单
    </div>
    <div class="pic">
        <div class="pic_1">
            <h3><img src="<%=uiPath%>site/images/images/qr.png" alt="" /></h3>
            <div class="font">
                <p class="name">${name}<span>羽</span></p>
                <p>${age}岁/${height}cm/${weight}kg</p>
                <p>${introduct}</p>
            </div>
        </div>
    </div>
    <div class="course">
        <ul>
            <li class="sbj">课程: <b>${course}</b> </li>
            <li>课程项目：<b>${kindsName}</b></li>
            <li>课程地址：<b>北京鸿禧庄园一号场</b></li>
            <li>课程时间：<b>6月26日  15：00-16：00</b></li>
            <li>联系人：<b>${name}</b></li>
            <li>联系电话：<b>${telPhone}</b></li>
            <li>课程总额：<span>￥${salesPrice}</span></li>
        </ul>
    </div>
    <div>
        <form>
            姓名：<input type="text" value="" name="">
            电话：<input type="text" value="" name="">
        </form>
    </div>
    <div class="button">
        <a>提交订单</a>
    </div>

</div>
</body>
</html>