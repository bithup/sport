<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>运动登录</title>
    <base href="<%=basePath%>">
    <link href="<%=uiPath%>site/css/common.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/login.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
</head>
<body>
<div class="content">
    <form id="dataForm">

        <div class="log_tit">
            <a href="javascript:history.go(-1)">
                <img src="<%=uiPath%>site/images/return.png" alt="" />
            </a>
            <div class="log_tit02">
                <strong>登录</strong>
            </div>
        </div>
        <div class="log_sf">
            <input name="roleType" type="hidden" id="roleType" value="1">
            <ul>
                <li class="active"><a name="role1" href="javascript:void(0);" onclick="roleTypeChange(1)">会员</a></li>
                <li><a name="role2" href="javascript:void(0);" onclick="roleTypeChange(2)">教练</a></li>
                <li><a name="role3" href="javascript:void(0);" onclick="roleTypeChange(3)">场馆</a></li>
            </ul>
        </div>
        <div class="log_yh">
            <input type="text" id="account" name="account" placeholder="请输入手机号" value="">
        </div>
        <div class="log_mm">
            <input type="password" id="password" name="password" placeholder="请输入密码" value="">
        </div>
        <div class="log_zh">
            <i><a href="/portal/forgetPasswordInit.htm">忘记密码</a></i><strong><a href="/portal/registerInit.htm">用户注册</a></strong>
        </div>
        <div class="log_an">
            <input type="button" value="登录" style="display: none;">
            <a href="javascript:void(0);" onclick="login_form()">
                <i>登</i>录
            </a>
        </div>
    </form>
    <div class="log_yb" style="display: none;">
        推荐好友有银币送哦
    </div>
</div>
<script type="text/javascript">

    $(function () {
        $(".log_sf ul li").on("click",function(){
            $(this).addClass("active").siblings().removeClass("active");
        });

        var as = document.getElementsByName('role');
        var len = as.length;
        for (var i = 0; i < len; i++) {
            as[i].onclick = function () {
                for (var j = 0; j < len; j++) {
                    if (as[j].className == 'active') {
                        as[j].className = '';
                        break;
                    }
                }
                return this.className = 'active';
            }
        }

        if (len > 0) {
            as[0].className = 'active';
        }
    });

    function roleTypeChange(val) {

        $("#roleType").val(val);

    }

    function login_form() {
        //$('#dataForm').bootstrapValidator('validate');
        //if ($('#dataForm').data('bootstrapValidator').isValid())
        //    login();
        var account = $.trim($('#account').val());
        var password = $.trim($('#password').val());

        var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;

        if (null == account || '' == account ) {
            new Toast({message: '请输入手机号码！'}).show();
            return;
        }
        if (account.length != 11 ||!reg.test(account)) {
            new Toast({message: '请输入正确手机号码！'}).show();
            return;
        }
        if (null == password || '' == password) {
            new Toast({message: '密码不能为空！'}).show();
            return;
        }
        login();
    }

    function login() {

        $.ajax({
            type:"POST",
            url: "/portal/login.htm",
            dataType: "json",
            data:{account:$('#account').val(),password:$('#password').val(),type:$("#roleType").val()},
            success: function (data) {
                var jsonResult = eval(data);
                if(jsonResult.resultFlg=="1"){
                    new Toast({message: '登录成功！'}).show();
                    location.href="/home/baseInfo.htm";
                }else{
                    new Toast({message: jsonResult.resultMsg}).show();
                }
            }
        })
    }
</script>

</body></html>