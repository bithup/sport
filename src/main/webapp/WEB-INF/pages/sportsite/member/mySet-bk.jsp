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


    <script   type="text/javascript">
        window.onload=function () {
            $(".button").click(function () {
                var nickName=$(".nickName").val();
                alert(nickName);
                var birthday=$(".birthday").val();
                alert(birthday);
                var heigth=$(".heigth").val();
                alert(heigth);
                var weigth=$(".weigth").val();
                alert(weigth);
                var hobby=$(".hobby").val();
                alert(hobby);
                var signature=$(".signature").val();
                alert(signature);
                if(nickName==null||nickName==""){
                    alert("昵称为空！");
                    return;
                }else {
                    $.ajax({
                        type:"POST",
                        url: "/member/updateMemberInfo.htm",
                        dataType: "json",
                        data:{nickName:nickName,birthday:birthday,heigth:heigth,weigth:weigth,hobby:hobby,signature:signature},
                        success: function (data) {
                            var jsonResult = eval(data);
                            if(jsonResult.resultFlg=="1"){
                                location.href="/home/baseInfo.htm";
                            }else{
                                alert(jsonResult.resultMsg);
                            }
                        }
                    })

                }
            });

        }


    </script>

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
                    <img src="${memberUser.headRealPath}" alt="" class="per_logo">
                </li>
                <li>
                    昵称
                    <a href="./self_intro.html"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <input class="nickName"  type="text" name="nickName" value="${memberUser.nickName}">
                </li>
                <div class="no"></div>
                <li >
                    生日
                    <a href="javascript:void(0);"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <input class="birthday" type="text" name="" value="${memberUser.birthday}" id="USER_AGE">
                </li>
                <li>
                    性别
                    <a href="javascript:void(0);" class="sex_btn"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <span class="sex_">${memberUser.sex eq 1 ? "男":"女"}</span>
                </li>
                <li>
                    身高
                    <a href="javascript:void(0);"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <i>CM</i>
                    <input  class="heigth"  type="text" name="" value="${memberUser.heigth}" class="self_height" >
                </li>
                <div class="no"></div>

                <li>
                    体重
                    <a href="javascript:void(0);"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <i>KG</i>
                    <input class="weigth"  type="text" name="" value="${memberUser.weigth}" class="self_weight">
                </li>
                <li>
                    运动爱好
                    <a href="javascript:void(0);"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <input class="hobby"  type="text" name="" value="${memberUser.hobby}" class="self_xq">
                </li>
                <li>
                    签名
                    <a href="javascript:void(0);"><img src="<%=uiPath%>site/images/my_zk.png" alt=""></a>
                    <input  class="signature" type="text" name="" value="${memberUser.signature}" class="self_intro">
                </li>
            </ul>
        </div>
        <div class="exit">
            <input type="submit" name="" value="保存修改" class="button">
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
        $(".detail ul li").on("click",function(){
            $(this).find("input").select();
        });
        /*性别选择*/
        var dHeight=$(document.body).height();
        $(".sex_btn").parent("li").click(function(){
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
            dateFormat: 'yyyy-mm-dd',
            lang: 'zh',
            showNow: true,
            nowText: "今天",
            startYear: currYear - 50, //开始年份
            endYear: currYear + 10 //结束年份
        };

        $("#USER_AGE").mobiscroll($.extend(opt['date'], opt['default']));

        $(".detail input").on("click",function(){
            $(this).select();
        });
    })
</script>
</body>
</html>