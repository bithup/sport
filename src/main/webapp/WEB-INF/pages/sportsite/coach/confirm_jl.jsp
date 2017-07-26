<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>教练确认订单</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/confirm_jl.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".button").click(function () {
                if(!(/^1(3|4|5|7|8)\d{9}$/.test($(".telephone").val()))){
                    new Toast({message:'请输入正确的手机号码'}).show();
                    $(".telephone").val("");
                    $(".telephone").focus();
                    return;
                }
                var memberId='${memberId}';
                var contact=$(".contact").val();
                var telephone=$(".telephone").val();
                var courseId=${course.id};
                var orderAmount='${course.salesPrice}';

                if(contact==null||contact==''){
                    new Toast({message:'联系人不能为空！'}).show();
                    return;
                }
                if(telephone==null||telephone==''){
                    new Toast({message:'联系人电话不能为空！'}).show();
                    return;
                }

                $.ajax({
                    url:"/coach/createCoachOrder.htm",
                    type:"POST",
                    data:{memberId:memberId,contact:contact,telephone:telephone,goodsId:courseId,orderAmount:orderAmount},
                    dataType:"json",
                    success:function (data) {
                        var jsonReturn=eval(data);
                        if(jsonReturn.resultFlg=='1'){
                            location.href="/pay/payWay.htm?orderAmount="+orderAmount+"&orderId="+jsonReturn.resultData;
                        }else{
                            new Toast({message:jsonReturn.resultMsg}).show();
                            <!--返回报名页面-->
                            setTimeout("history.go(-1)",3000);
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
        <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return_2.png" alt="" /></a>
        确认订单
    </div>
    <div class="pic">
        <div class="pic_1">
            <h3><img src="${coach.picRealPath}" alt="" /></h3>
            <div class="font">
                <p class="name">${coach.name}<span>羽</span></p>
                <p>${coach.age}岁/${coach.height}cm/${coach.weight}kg</p>
                <p>${coach.intro}</p>
            </div>
        </div>
    </div>
    <div class="course">
        <ul>
            <li class="sbj">课程名称: <b>${course.courseName}</b> </li>
            <li>运动类型：<b>${coach.kindsName}</b></li>
            <li>上课地址：<b>${coach.courseAddress}</b></li>
            <li>课程类型：
                <b>
                    <c:choose>
                    <c:when test="${course.courseType==1}">月</c:when>
                    <c:when test="${course.courseType==2}">季</c:when>
                    <c:when test="${course.courseType==3}">年</c:when>
                        <c:otherwise>无</c:otherwise>
                    </c:choose>

            </b></li>
            <li>联系人：<input type="text" name="" class="contact" placeholder="请输入姓名"/></li>
            <li>联系电话：<input type="text" name="" class="telephone" placeholder="请输入手机号"/></li>
            <li>课程总额：<span>￥${course.salesPrice}</span></li>
        </ul>
    </div>
    <div class="button">
        <a>提交订单</a>
    </div>

</div>
</body>
</html>