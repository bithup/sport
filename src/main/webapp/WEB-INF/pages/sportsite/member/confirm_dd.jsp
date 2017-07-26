<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>确认订单</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=uiPath%>site/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/confirm_dd.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var orderId="${orderId}";
        var memberId="${memberId}";
        $(function () {
            $("#butt").click(
                function () {
                    $.ajax({
                        url:"/order/confirmOrder.htm",
                        type:"POST",
                        data:{orderId:orderId,code:$("#code").val()},
                        async:false,
                        dataType:"json",
                        success:function (data) {
                            if(data.resultFlg==1){
                                alert("确认成功！");
                                //location.href="/order/getListAsShop.htm?memberId="+memberId+"&page=1&pagesize=80";
                                location.href="/member/myOrder.htm?id="+memberId+"&role=Shop";
                                //new Toast({message: '确认订单成功'}).show();
                            } else {
                                alert(data.resultMsg+"请重试");
                                location.href="/member/myOrder.htm?id="+memberId+"&role=Shop";
                                //new Toast({message: data.resultMsg}).show();
                            }
                        },
                        error:function () {
                            alert("ajax error");
                        }
                    });
                }
            );
        });

    </script>
</head>
<body>
<div class="content">

        <div class="head">
            <div class="left"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a>
            </div>
            确认订单
        </div>
        <div class="con_wrap">
            <input type="text" id="code" value="" placeholder="请输入消费凭证码">
        </div>
        <div class="submit_btn">
            <input type="button" id="butt" name="" value="确认">
        </div>

</div>
</body>
</html>