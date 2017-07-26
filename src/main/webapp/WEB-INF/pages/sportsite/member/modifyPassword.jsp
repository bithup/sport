<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">

<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/change.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var id=${id};
        window.onload=function () {
            $(".button").click(function () {
                var oldPass=$(".oldPassword").val();

                var password=$(".newPassword").val();

                var passwordc=$(".newPasswordc").val();

                if(password!=passwordc){
                    new Toast({message:'密码不一致，请重新输入密码！'}).show();
                    $(".newPassword").val("");
                    $(".newPasswordc").val("");
                    $(".newPassword").focus();
                    return false;
                }
                var p1=/^[\w]{6,20}$/;
                if (!p1.test(password)){
                    new Toast({message: '密码格式为：6-20位数字、字母、下划线!'}).show();
                    return;
                }
                    var id=${id};
                    var oldPass=$(".oldPassword").val();
                    var password=$(".newPassword").val();
                    $.post("/member/updatePassword.htm?id="+id+"&password="+password+"&oldPass="+oldPass,function (data) {
                        var jsonReturn=eval('('+data+')');
                        if(jsonReturn.resultFlg=='1'){
                            new Toast({message:'修改成功！'}).show();
                        }else {
                            new Toast({message:jsonReturn.resultMsg}).show();
                        }

                    });

            });
        }
    </script>
</head>
<body>
<div class="content">
    <div class="head">
        <div class="head">
            <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt=""></a>
            修改密码
        </div>
    </div>
        <div class="detail">
            <ul>
                <li class="password">原 密 码<input class="oldPassword" type="password" placeholder="请输入原来的密码"></li>
                <li>新 密 码<input class="newPassword" type="password" placeholder="请设置新密码"></li>
                <li>确认密码<input class="newPasswordc" type="password" placeholder="请确认新的密码"></li>
            </ul>
            <p>密码是由6-20位英文字母、数字或符号组成</p>
            <input  name="" value="保存" class="button">
        </div>
</div>
</body>
</html>