<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">

<head>
    <meta charset="UTF-8">
    <title>我的设置</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/my_set.css">
    <link href="<%=uiPath%>site/css/mobiscroll_date.css" rel="stylesheet" />
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script src="<%=uiPath%>site/js/mobiscroll_date.js" charset="gb2312"></script>
    <script src="<%=uiPath%>site/js/mobiscroll.js"></script>
</head>
<body>
<div class="content">
    <div class="head">
        <div class="head">
            <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt=""></a>
            我的设置
        </div>
    </div>
    <div class="no"></div>
    <form method="" action="">
        <div class="detail">
            <ul>
                <li>
                    头像
                    <img src="${memberUser.headRealPath}" alt="">
                </li>
                <li>
                    昵称
                    <a href="./my_name.html"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <span>${memberUser.nickName}</span>
                </li>
                <div class="no"></div>
                <li >
                    生日
                    <input type="text" name="" value="${memberUser.birthday}" id="USER_AGE">
                    <%--<fmt:formatDate value='${memberUser.birthday}' pattern='yyyy.MM.dd'/>--%>
                </li>
                <li>
                    性别
                    <a href="#" class="sex_btn"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <span class="sex_">${memberUser.sex eq 1 ? "男":"女"}</span>
                </li>
                <li>
                    身高
                    <input type="text" name="" value="${memberUser.heigth}" class="self_height" placeholder="170cm">
                </li>
                <div class="no"></div>

                <li>
                    体重
                    <input type="text" name="" value="${memberUser.weigth}" class="self_weight">
                </li>
                <li>
                    运动爱好
                    <input type="text" name="" value="${memberUser.hobby}" class="self_xq">
                </li>
                <li>
                    签名
                    <a href="./self_intro.html"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <span>${memberUser.signature}</span>
                </li>
            </ul>
        </div>
        <div class="exit">
            <input type="submit" name="" value="保存修改">
        </div>
    </form>
    <div class="sexsel_wrap">
        <div class="sex_sel">
            性别
            <div class="man_wrap">
                男<input type="radio" name="sex" value="男" checked="checked">
            </div>
            <div class="wom_wrap">
                女<input type="radio" name="sex" value="女">
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        /*性别选择*/
        var dHeight=$(document.body).height();
        $(".sex_btn").click(function(){
            $(".sexsel_wrap").css({'height':dHeight+"px",'display':'block'});
        });
        $("input[name=sex]").on("click",function(){
            $(".sexsel_wrap").css("display","none");
            $(".sex_").html($(this).val());
        });

        /*选择生日*/
        var currYear = (new Date()).getFullYear();
        var opt={};
        opt.date = {preset : 'date'};
        opt.datetime = {preset : 'datetime'};
        opt.time = {preset : 'time'};
        opt.default = {
            theme: 'android-ics light', //皮肤样式
            display: 'modal', //显示方式
            mode: 'scroller', //日期选择模式
            dateFormat: 'yyyy.mm.dd',
            lang: 'zh',
            showNow: true,
            nowText: "今天",
            startYear: currYear - 50, //开始年份
            endYear: currYear + 10 //结束年份
        };

        $("#USER_AGE").mobiscroll($.extend(opt['date'], opt['default']));

        /*修改身高*/
        $(".self_height").click(function(){
            $(this).select();
        });

        /*修改体重*/
        $(".self_weight").click(function(){
            $(this).select();
        });

        /*修改兴趣*/
        $(".self_xq").click(function(){
            $(this).select();
        })
    })
</script>
</body>
</html>
