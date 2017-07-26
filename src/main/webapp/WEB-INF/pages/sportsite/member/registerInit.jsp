<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>注册</title>
    <base href="<%=basePath%>">
    <link href="<%=uiPath%>site/css/common.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/registered.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
</head>
<body>
<div class="content">
    <div class="log_tit">
        <a href="javascript:history.go(-1)"></a>
        <strong>注册</strong>
    </div>
    <div class="yd-k"></div>
    <form id="dataForm" class="reg_form">
        <div class="log_sf">
            <input name="roleType" type="hidden" id="roleType" value="1">
            <ul>
                <li class="active"><a name="role1" onclick="roleTypeChange(1)">会员</a></li>
                <li><a name="role2" onclick="roleTypeChange(2)">教练</a></li>
                <li><a name="role3" onclick="roleTypeChange(3)">场馆</a></li>
            </ul>
        </div>
        <div class="reg_sj">
            <input type="text" id="telPhone" name="telPhone" placeholder="请输入手机号" value="">
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
            <input type="password" id="password" name="password" placeholder="密码" value="">
        </div>
        <div class="reg_qr">
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="确认密码" value="">
        </div>
        <div class="reg_an">
            <a onclick="register()"><i>注</i>册</a>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    var cliclcount=0;
    $(function () {
        $(".log_sf ul li").on("click",function(){
            $(this).addClass("active").siblings().removeClass("active");
        });
    });
    function MathRand()
    {
        var  second=0;
        var clickTime = new Date().getTime();
        if(second>0 && cliclcount>0 ){return;}
        cliclcount=cliclcount+1;
        var telPhone = $.trim($('#telPhone').val());
        var reg =  /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if(telPhone==""||telPhone==null)
        {
            new Toast({message:'请输入手机号'}).show();
            return;
        }
        if (telPhone.length != 11 || !reg.test(telPhone)) {
            new Toast({message: '请输入正确手机号码!'}).show();
            return;
        }
        if (accountIsRepeat()){
            new Toast({message: '此号码已被注册！'}).show();
            return;
        }

        var Num="";
        for(var i=0;i<6;i++)
        {
            Num+=Math.floor(Math.random()*10);
        }
        $.post("/sportsite/shortMessage.htm?telPhone="+telPhone+"&content="+Num,function (data)  {
            var jsonReturn=eval("("+data+")");
            if(jsonReturn.resultFlg==1){
                new Toast({message: jsonReturn.resultMsg}).show();
            } else {
                new Toast({message: jsonReturn.resultMsg}).show();
                //alert(jsonReturn.resultMsg);
            }
        });
        $("#newpass").val(Num);
        var Timer = setInterval(function(){
            var nowTime = new Date().getTime();
            second = Math.ceil(60-(nowTime-clickTime)/1000);
            if(second>0){
                document.getElementById("a").innerHTML=second+"s后重新获取";

            }else{
                clearInterval(Timer);
                document.getElementById("a").innerHTML= "获取验证码";
            }
        },1000);
    }
    //切换角色
    function roleTypeChange(val) {
        $("#roleType").val(val);
    }
    //注册
    function register() {
//        var _zone = localStorage.getItem("zone");
//        var zone = eval("(" + _zone + ")");
//        zoneCode = zone.code;
        var telPhone = $.trim($('#telPhone').val());
        var confirmPassword=$.trim($('#confirmPassword').val());
        var password = $.trim($('#password').val());
        var reg =  /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if (null == telPhone || '' == telPhone ) {
            new Toast({message: '手机号不能为空!'}).show();
            return;
        }
        if (telPhone.length != 11 || !reg.test(telPhone)) {
            new Toast({message: '请输入正确手机号码!'}).show();
            return;
        }
        if (null == password || '' == password || null == confirmPassword || '' == confirmPassword) {
            new Toast({message: '密码不能为空!'}).show();
            return;
        }
        if(password.length < 6)
        {
            new Toast({message: '密码设置不能低于6位！'}).show();
            return;
        }
        if(password.length > 20)
        {
            new Toast({message: '密码设置不能多于20位！'}).show();
            return;
        }

        var confirmPassword = $.trim($('#confirmPassword').val());

        var p1=/^[\w]{6,20}$/;

        if (!p1.test(password)){
            new Toast({message: '密码格式为：6-20位数字、字母、下划线!'}).show();
            return;
        }

        if (password != confirmPassword) {
            new Toast({message: '两次密码输入不同!'}).show();
            return;
        }
        var account=$(".reg_sj>input").val();
        var password=$(".reg_qr>input").val();
        var validationCode=$(".reg_yz02>input").val();
        var type=$("#roleType").val();
        $.post("/portal/register.htm?account="+account+"&password="+password+"&validationCode="+validationCode+"&type="+type , function (data) {
            var jsonReturn=eval('('+data+')');
            if(jsonReturn.resultFlg=='1'){
                new Toast({message: '注册成功'}).show();
                setTimeout('location.href="/member/showMemberInfo.htm"',1500);

            }else{
                new Toast({message: jsonReturn.resultMsg}).show();
            }
        });
    }
    function accountIsRepeat() {
        var flag=false;
        var account=$(".reg_sj>input").val();
        $.ajax({
            type:"POST",
            url:"/portal/accountIsRepeat.htm?account="+account,
            dataType:"json",
            async:false,
            success:function (data) {
                if(data.resultFlg=='1'){

                    flag=true;

                }

            }
        });
        return flag;

//        $.post("/portal/accountIsRepeat.htm?account="+account,function (data) {
//            var jsonReturn=eval('('+data+')');
//            if(jsonReturn.resultFlg=='1'){
//                new Toast({message: '此号码已被注册！'}).show();
//                return;
//            }
//            }
//        )
    }

</script>