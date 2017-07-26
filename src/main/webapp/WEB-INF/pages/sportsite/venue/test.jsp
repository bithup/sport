<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>登录</title>
    <script src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        window.onload=function(){
            $("#butt").click(function(){
                var account=$(".login_account").val();
                var password=$(".login_password").val();
                new Toast({message:account}).show();
                if(account==""||account==null){
                    new Toast({message:'请输入账号!'}).show();
                    return;
                }
                if(password==""||account==null){
                    new Toast({message:'请输入密码!'}).show();
                    return;
                }

                new Toast({message:account}).show();

                $.ajax({
                    type:"POST",
                    url: "http://192.168.3.106:8088/portal/login.htm",
                    data:{account:account,password:password},
                    dataType: "json",
                    success: function (data) {
                        new Toast({message:'!!'}).show();

                    }

                });


            });

        }
    </script>
</head>
<body>
<div class="content">

    Account:<input type="text" placeholder="请输入手机号" class="login_account"></input>

    Password:<input type="password" placeholder="请输入密码" class="login_password"></input>

    <input id="butt" type="button" value="登录">

</div>
</body>
</html>


