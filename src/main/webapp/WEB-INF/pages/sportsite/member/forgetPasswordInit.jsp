<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>忘记密码</title>
    <base href="<%=basePath%>">
    <link href="<%=uiPath%>site/css/common.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/registered.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".log_sf ul li").on("click",function(){
                $(this).addClass("active").siblings().removeClass("active");
            });
        });
        var cliclcount=0;
        function resetPassword() {
            var account=$.trim($('#account').val());
            var password=$.trim($('#password').val());
            var confirmPassword=$.trim($('#confirmPassword').val());
            var validationCode=$(".reg_yz02>input").val();
            var type=$("#roleType").val();
            var reg =  /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if (null == account || '' == account ) {
                new Toast({message: '手机号不能为空!'}).show();
                return;
            }
            if (account.length != 11 || !reg.test(account)) {
                new Toast({message: '请输入正确手机号码!'}).show();
                return;
            }
            if (accountIsRepeat()){
                new Toast({message: '此号码未注册！'}).show();
                return;
            }
            if(validationCode==null||validationCode==''){
                new Toast({message: '验证码不能为空!'}).show();
                return;
            }
            if(password==null||password==''){
                new Toast({message: '密码不能为空!'}).show();
                return;
            }
            var p1=/^[\w]{6,20}$/;
            if (!p1.test(password)){
                new Toast({message: '密码格式为：6-20位数字、字母、下划线!'}).show();
                return;
            }
            if(password!==confirmPassword){
                new Toast({message: '两次密码输入不同!'}).show();
                return;
            }

            $.post("/portal/forgetPassword.htm?account="+account+"&password="+password+"&validationCode="+validationCode+"&type="+type , function (data) {
                var jsonReturn = eval("(" + data + ")");
                if(jsonReturn.resultFlg=='1'){
                    new Toast({message:'重置密码成功！'}).show();
                    setTimeout("location.href='/portal/loginInit.htm'",2000);
                } else {
                    new Toast({message:jsonReturn.resultMsg}).show();
                }
            });
        }
        function roleTypeChange(val) {


            $("#roleType").val(val);

        }
        function MathRand()
        {
            var second=0;
            var clickTime = new Date().getTime();
            if(second>0 && cliclcount>0 ){return;}
            cliclcount=cliclcount+1;
            var account = $.trim($('#account').val());
            var password=$.trim($('#password').val());
            var reg =  /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;

            if (null == account || '' == account || account.length != 11 || !reg.test(account)) {
                new Toast({message: '请输入正确手机号码!'}).show();
                return;
            }
            //发验证码之前判断是否注册
            if (accountIsRepeat()){
                new Toast({message: '此号码未注册！'}).show();
                return;
            }

            var Num="";
            for(var i=0;i<6;i++)
            {
                Num+=Math.floor(Math.random()*10);
            }

            $.post("/sportsite/shortMessage.htm?telPhone="+account+"&content="+Num, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.code == 1) {
                    new Toast({message: jsonReturn.msg}).show();}
            });
            $("#newpass").val(Num);
            var Timer = setInterval(function(){
                var nowTime = new Date().getTime();
                var  second  = Math.ceil(60-(nowTime-clickTime)/1000);
                if(second>0){
                    document.getElementById("a").innerHTML=second+"s后重新获取";

                }else{
                    clearInterval(Timer);
                    document.getElementById("a").innerHTML= "获取验证码";
                }
            },1000);
        }
        function accountIsRepeat() {
            var flag=false;
            var account=$.trim($('#account').val());
            $.ajax({
                type:"POST",
                url:"/portal/accountIsRepeat.htm?account="+account,
                dataType:"json",
                async:false,
                success:function (data) {
                    if(data.resultFlg=='0'){
                        flag=true;
                    }
                }
            });
            return flag;
        }

    </script>
</head>
<body>
<div class="content">
    <div class="log_tit">
        <a href="javascript:history.go(-1)"></a>
        <strong>忘记密码</strong>
    </div>
    <form id="dataForm">
        <div class="log_sf">
            <input name="roleType" type="hidden" id="roleType" value="1">
            <ul>
                <li class="active"><a name="role1" onclick="roleTypeChange(1)">会员</a></li>
                <li><a name="role2" onclick="roleTypeChange(2)">教练</a></li>
                <li><a name="role3" onclick="roleTypeChange(3)">场馆</a></li>
            </ul>
        </div>
        <div class="reg_sj">
            <input type="text" name="account" id="account" placeholder="请输入手机号" value="">
        </div>
        <div class="reg_yz clearfix">
            <div class="reg_yz02">
                <input type="text" id="oldpass" placeholder="验证码">
            </div>
            <div class="reg_yz03">
                <input type="text" id="newpass" style="display: none">
                <a onclick="MathRand()" id="a">获取验证码</a>
            </div>
        </div>
        <div class="reg_mm">
            <input type="password" id="password" name="password" placeholder="新密码" value="">
        </div>
        <div class="reg_qr">
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="重新输入密码" value="">
        </div>
        <div class="reg_an">
            <a onclick="resetPassword()">重置密码</a>
        </div>
    </form>
</div>

</body></html>