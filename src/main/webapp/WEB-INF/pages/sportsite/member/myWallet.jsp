<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
	<meta charset="UTF-8">
	<title>我的钱包</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/wallet.css">
	<script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
	<script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
	<div class="content">
		<div class="head">
			<a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt=""></a>
			我的钱包
		</div>
		<div class="balance">
			账户余额：￥${purseBalance}
		</div>
		<div class="balance_wrap">
			<p class="statement">声明：提现会有2‰的手续费，提现金额24小时到账</p>
			<div class="default_way">
				<p>默认银行卡</p>
				<ul>
					<li>
						<img src="<%=uiPath%>site/images/cardpay.png" alt="">
						<div class="con_right">
							<span>xxxx</span>
							<span>尾号xxx</span>
						</div>
					</li>
				</ul>
			</div>
			<div class="wallet_btn">
				<input type="submit" name="" value="确认提现">
			</div>
			<span class="wallet_pass"><a href="###">设置交易密码</a></span>
		</div>
	</div>
</body>
</html>