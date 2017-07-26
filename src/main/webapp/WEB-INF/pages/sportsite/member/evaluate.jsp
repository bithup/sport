<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>评价</title>
    <base href="<%=basePath%>">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" href="<%=uiPath%>site/css/evaluate.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <!--导航开始-->
    <div class="head">
        <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a>评价
    </div>
    <!-- 内容开始 -->
    <div class="eva_content">
        评价：
        <form method="" action="" id="eva_form">
            <span><input type="radio"class="input_check" id="check1" name="eva" value="0" checked="checked"><label for="check1"></label>好评</span>
            <span><input type="radio"class="input_check" id="check2" name="eva" value="1"><label for="check2"></label>中评</span>
            <span><input type="radio"class="input_check" id="check3" name="eva" value="2"><label for="check3"></label>差评</span>
            <input type="button" name="" value="评价" class="eva_btn">
        </form>
    </div>
</div>
<script>
    var orderId="${orderId}";
    var goodsId="${goodsId}";
    var memberId="${memberId}";
    $(function () {

        $(".eva_btn").click(function () {
            var kind=$("input[type='radio']:checked").val();
            $.ajax({
                url:"/order/remark.htm?orderId="+orderId+"&dataId="+goodsId+"&kind="+kind,
                type:"POST",
                async:false,
                dataType:"json",
                success:function (data) {
                    if(data.resultFlg==1){
                        alert("评价成功！");
                        location.href="/member/myOrder.htm?id="+memberId+"&role=Member";
                        //new Toast({message: '确认订单成功'}).show();
                    } else {
                        alert(data.resultMsg+"请重试");
                        location.href="/member/myOrder.htm?id="+memberId+"&role=Member";
                        //new Toast({message: data.resultMsg}).show();
                    }
                },
                error:function () {
                    alert("ajax error");
                }
            });
//            $.post("/order/remark.htm?orderId="+orderId+"&dataId="+goodsId+"&kind="+kind,function (data) {
//                var jsonReturn=eval(“"("+data+")");
//                if(jsonReturn.resultFlg==1){
//
//                    location.href=history.go(-1);
//                } else {
//                    aler("评价失败请重试！");
//                }
//            });
        });
    });
//    checkboxToRadio("eva");
//    function checkboxToRadio(checkboxName, form) {
//        if (checkboxName == null) return;
//        var f = form || document.forms[0];
//        checkboxs = document.getElementsByName(checkboxName);
//        for(i = 0; i < checkboxs.length; i++){
//            checkboxs[i].onclick = function(){
//                for (j = 0; j < checkboxs.length; j++ ){
//                    if (this.value != checkboxs[j].value && checkboxs[j].checked == true){
//                        checkboxs[j].checked = false;
//                    }
//                }
//            }
//        }
//    }

</script>
</body>
</html>