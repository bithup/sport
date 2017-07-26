<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>活动确认订单</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/confirm_hd.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
</head>
<body>
<div class="content">
    <div class="head">
        <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a>
        确认活动
    </div>
    <div class="pic">
        <div class="pic_1">
            <h3><img src="${activity.activityRealPath}" alt="" /></h3>
            <div class="font">
                <p class="b">${activity.activityName}</p>

                <p>活动人数：${activity.activityCount}</p>
            </div>
        </div>
    </div>
    <div class="course">
        <ul>
            <!--转换时间格式-->
            <li class="sbj">活动时间：<br /><p><fmt:formatDate value="${activity.startDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>-<fmt:formatDate value="${activity.endDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></p></li>
            <li>活动地点:<br /><span>${activity.activityAddress}</span></li>
            <li>联系人：<br /><span><input class="contact" value="" placeholder="联系人姓名"></span></li>
            <li>联系电话：<br /><span><input class="telephone" value="" placeholder="联系电话"></span></li>

            <c:choose>
                <c:when test="${activity.isFree==1}">
                    <li>活动金额：<br /><i>免费活动</i></li>
                </c:when>
                <c:when test="${activity.isFree==2}">
                    <li>活动金额：<br /><i>AA制活动</i></li>
                </c:when>
                <c:otherwise>
                    <li>活动金额：<br /><i>￥${activity.activityPrice}</i></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
    <div class="button">
        <a>提交订单</a>
    </div>

</div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $(".button").click(function () {
            if(!(/^1(3|4|5|7|8)\d{9}$/.test($(".telephone").val()))){
                new Toast({message: '请输入正确手机号码!'}).show();
                $(".telephone").val("");
                $(".telephone").focus();
                return;
            }
            var memberId=${memberId};
            var contact=$(".contact").val();
            var telephone=$(".telephone").val();
            var goodsId=${activity.id};
            var orderAmount=${activity.activityPrice};

            if(memberId==null||goodsId==null||orderAmount==null){
                new Toast({message: 'params is null!'}).show();
                return;
            }
            if(contact==null||contact==''){
                new Toast({message: '联系人不能为空!'}).show();
                return;
            }
            if(telephone==null||telephone==''){
                new Toast({message: '联系人电话不能为空!'}).show();
                return;
            }

            $.ajax({
                type:"POST",
                url:"/activity/createActiveOrder.htm",
                data:{memberId:memberId,contact:contact,telephone:telephone,goodsId:goodsId,orderAmount:orderAmount},
                dataType:"json",
                success:function (data) {
                    var jsonReturn=eval(data);
                    if(jsonReturn.resultFlg=='1'){
                        location.href="/pay/payWay.htm?orderAmount="+orderAmount+"&orderId="+jsonReturn.resultData;
                    }else{
                        new Toast({message: jsonReturn.resultMsg}).show();
                        <!--返回预约页面-->
                        setTimeout("history.go(-1)",3000);

                    }
                }
            });
        });
    });

</script>
</html>