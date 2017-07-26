<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport"
      content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">

<head>
    <meta charset="UTF-8">
    <title>我的设置</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/my_set.css">
    <link href="<%=uiPath%>site/css/mobiscroll_date.css" rel="stylesheet"/>
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script src="<%=uiPath%>site/js/mobiscroll_date.js" charset="gb2312"></script>
    <script src="<%=uiPath%>site/js/mobiscroll.js"></script>
    <script src="<%=uiPath%>lib/jqueryForm/jquery.form.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
       $(function () {
            $(".exit").click(function () {

                var nickName = $(".nickName").val();
                if (nickName == null || nickName == '') {
                    new Toast({message:'昵称不能为空!'}).show();
                    return;
                }
                var data = $("#dataForm").serialize();
                var position = {
                    type: "POST",
                    url: "/member/updateMemberInfo.htm",
                    data: data,
                    dataType: "json",
                    success: function (data) {
                        var jsonReturn = eval(data);
                        if (jsonReturn.resultFlg == 1) {
                            //alert("修改成功");
                            new Toast({message:'修改成功!'}).show();
                            setTimeout("location.href='/member/showMemberInfo.htm'",3000);
                            //location.href="/member/mySet.htm";
                        }else{
                            new Toast({message:'修改失败!'}).show();
                        }
                    }
                };
                $("#dataForm").ajaxSubmit(position);
            });
        });
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
    <div   class="no"></div>
    <form id="dataForm" method="POST" enctype="multipart/form-data">
        <div class="detail">
            <ul>
                <input type="hidden" name="id" value="${memberUser.id}">
                <li>
                    头像
                    <c:choose>
                        <c:when test="${ empty memberUser.headRealPath}">
                            <img src="<%=uiPath%>site/images/defaultlogo.png" class="per_logo" id="touxiang">
                        </c:when>
                        <c:otherwise>
                            <img src="${memberUser.headRealPath}" class="per_logo" id="touxiang">
                        </c:otherwise>
                    </c:choose>
                    <input type="file" name="touxiang"  class="per_logo_sel"  onchange="selectImage(this);">
                </li>
                <li>
                    昵称
                    <input type="text" name="nickName" value="${memberUser.nickName}" class="nickName" placeholder="昵称">
                </li>
                <div class="no"></div>
                <li>
                    生日
                    <input type="text" name="birthday" value="${memberUser.birthday}" id="USER_AGE" class="birthday"  placeholder="生日">
                </li>
                <li class="sex_btn">
                    性别
                    <span class="sex_">${memberUser.sex eq 1 ? "男":"女"}</span>
                    <input type="text" name="sex" value="${memberUser.sex}" class="sex_ipt" placeholder="性别">
                </li>
                <li>
                    身高
                    <i>CM</i>
                    <input type="text" name="heigth" value="${memberUser.heigth}" class="self_height" placeholder="身高" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                </li>
                <div class="no"></div>

                <li>
                    体重
                    <i>KG</i>
                    <input type="text" name="weigth" value="${memberUser.weigth}" class="self_weight" placeholder="体重" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                </li>
                <li>
                    运动爱好
                    <input type="text" name="hobby" value="${memberUser.hobby}" class="self_xq" placeholder="请输入您的运动爱好">
                </li>
                <li>
                    签名
                    <input type="text" name="signature" value="${memberUser.signature}" class="self_intro" placeholder="请输入您的个性签名">
                </li>
            </ul>
        </div>
    </form>
    <div class="exit">
        <input type="button" name="" value="保存修改" class="button">
    </div>
    <div class="sexsel_wrap">
        <div class="sex_sel">
            性别
            <div class="man_wrap">
                男<input type="radio" name="sex" value="1" checked="checked">
            </div>
            <div class="wom_wrap">
                女<input type="radio" name="sex" value="2">
            </div>
        </div>
    </div>
</div>
<script>
    var image = '';
    function selectImage(file) {
        if (!file.files || !file.files[0]) {
            return;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            document.getElementById('touxiang').src = evt.target.result;
            image = evt.target.result;
            console.log(evt.target.result)
        };
        reader.readAsDataURL(file.files[0]);
    }
</script>
<script>
    $(function () {
        /*性别选择*/
        var dHeight = $(document.body).height();
        $(".sex_btn").click(function () {
            $(".sexsel_wrap").css({'height': dHeight + "px", 'display': 'block'});
        });
        $("input[name=sex]").on("click", function () {
            $(".sexsel_wrap").css("display", "none");
            var sex_opt = $(this).val();
            if (sex_opt == 1) {
                $(".sex_").html("男");
                //$(".sex_ipt").attr("value",1);
            } else {
                $(".sex_").html("女");
                //$(".sex_ipt").attr("value",2);
            }
            $(".sex_ipt").attr("value", sex_opt);
        });
        //选择生日
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
    })
</script>
</body>
</html>