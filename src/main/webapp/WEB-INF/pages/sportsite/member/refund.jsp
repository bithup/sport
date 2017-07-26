<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<title>退款</title>
	<base href="<%=basePath%>">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link href="<%=uiPath%>site/css/global.css" rel="stylesheet" type="text/css">
	<link href="<%=uiPath%>site/css/refund.css" rel="stylesheet" type="text/css">
	<script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
	<script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
	<div class="content">
		<!-- 标题 -->
		<div class="log_tit">
		    <i onclick="window:history.back(-1);"></i>
			退款
		</div>
		<!-- 正文开始 -->
		<form method="" action="">
			<div class="refund_con">
				<ul class="refund_way">
					<input name="type" type="hidden" id="type" value="1">
					<li class="refund_mon act_way" onclick="changeType(1)" >
						<i>按金额：</i>
						<input type="text" name=""  placeholder="请输入金额" id="mon_ipt" >
						<i>元</i>
					</li>
					<li class="refund_mon"  onclick="changeType(2)">
						<i>按百分比：</i>
						<input type="text" name=""  placeholder="请输入百分比" id="per_ipt">
						<i>%</i>
					</li>
				</ul>
				<div class="refund_reason">
					<p>退款原因：</p>
					<textarea name="" id="refund_txt" placeholder="请输入退款原因"></textarea>
				</div>
				<div class="refund_btn">
					<input type="button" name="" value="申请退款" onclick="refund()">
				</div>
			</div>
		</form>
	</div>
	<script>
		var memberId="${memberId}";
        function changeType(val) {
            $("#type").val(val);
          console.log($("#type").val());
         }
	    function refund() {
	    var orderId=${orderId};
        var reason=$("#refund_txt").val();
        var type=$("#type").val();
        var refundAmount=$("#mon_ipt").val();
        var percent= $("#per_ipt").val();
        if(type==1){
            if(refundAmount==null||refundAmount==''){
                alert("请输入退款金额！");
                return;
            }
		}else{
            if(percent==null||percent==''){
                alert("请输入百分比！");
                return;
            }
            if(percent!=null&&percent!=''){
                if(parseFloat(percent)>100||parseFloat(percent)<0){
                    alert("请填写0到100之间的百分比！");
                    return ;
                }
            }
		}
            if(reason==null||reason==''){
                alert("请输入退款原因！");
                return;
            }
        $.ajax({
            url:"/order/refundMoney.htm",
            type:"POST",
            data:{orderId:orderId,reason:reason,type:type,refundAmount:refundAmount,percent:percent},
            async:false,
            dataType:"json",
            success:function (data) {
                //var jsonReturn = eval(data);
                if(data.resultFlg==1){
                    alert("退款申请成功！");
                    location.href="/member/myOrder.htm?id="+memberId+"&role=Member";
                   // new Toast({message:'申请退款成功!'}).show();
                } else {
                    alert(data.resultMsg);
                    //location.href="/member/myOrder.htm?id="+memberId+"&role=Member";
                    //new Toast({message:'申请退款失败!'}).show();
                }
            },
            error:function () {
                alert("ajax error");
            }
        });
    }

	</script>
	<script>
		$(function(){
			$(".refund_way li").on("click",function(){
				$(this).addClass("act_way").siblings().removeClass("act_way");
			});

			$("#refund_txt").focus(function(){
				$(this).html("");
			});

			$("#refund_txt").blur(function(){
				var $txt=$(this).html();
				if($txt==""){
					$("#refund_txt").html("请输入退款说明");
				}
			});

			//只能输入数字
			function check(item){
				var i=$(item).attr("value");
				if ( isNaN(i) ) {
					$(item).attr("value","");
				}
			}

			$("#mon_ipt").keyup(function(){
				check($("#mon_ipt"));
			});
			$("#per_ipt").keyup(function(){
				check($("#per_ipt"));
			});
		});
	</script>
</body>
</html>