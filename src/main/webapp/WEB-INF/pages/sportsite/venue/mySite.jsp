<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>主场馆信息</title>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/new_train.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link href="<%=uiPath%>site/css/mobiscroll_date.css" rel="stylesheet" />
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/newort.js"></script>
    <script src="<%=uiPath%>site/js/mobiscroll_date.js"></script>
    <script src="<%=uiPath%>site/js/mobiscroll.js"></script>
    <script src="<%=uiPath%>lib/jqueryForm/jquery.form.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script src="<%=uiPath%>site/js/iscroll1.js"></script>
    <script src="<%=uiPath%>site/js/sports.js"></script>
    <script src="<%=uiPath%>site/js/iosSelect.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=uiPath%>lib/mui/css/mui.picker.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>lib/mui/css/mui.poppicker.css">
    <script type="text/javascript" src="<%=uiPath%>lib/mui/js/mui.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>lib/mui/js/mui.picker.js"></script>
    <script type="text/javascript" src="<%=uiPath%>lib/mui/js/mui.poppicker.js"></script>
    <script type="text/javascript" src="<%=uiPath%>lib/mui/js/city.data-3.js"></script>

    <script>
        $(function () {
            $(".edit").click(function () {
                var name=$(".name").val();
                if (name==null||name==''){
                    alert("场馆名称不能为空！");
                    return;
                }
                var address=$(".address").val();
                if (address==null||address==''){
                    alert("场馆地址不能为空！");
                    return;
                }
                var contact=$(".contact").val();
                if (contact==null||contact==''){
                    alert("联系人不能为空！");
                    return;
                }
                var telephone=$(".telephone").val();
                if (telephone==null||telephone==''){
                    alert("联系电话不能为空！");
                    return;
                }
                var licenseNo=$(".licenseNo").val();
                if (licenseNo==null||licenseNo==''){
                    alert("营业执照编号不能为空！");
                    return;
                }
                var artificilPerson=$(".artificilPerson").val();
                if (artificilPerson==null||artificilPerson==''){
                    alert("法人代表不能为空！");
                    return;
                }
                var organizationType=$(".name").val();
                if (organizationType==null||organizationType==''){
                    alert("组织形式不能为空！");
                    return;
                }
                var busInfo=$(".busInfo").val();
                if (busInfo==null||busInfo==''){
                    alert("公交信息不能为空！");
                    return;
                }
                var subwayInfo=$(".subwayInfo").val();
                if (subwayInfo==null||subwayInfo==''){
                    alert("地铁信息不能为空！");
                    return;
                }
                var serviceInfo=$(".serviceInfo").val();
                if (serviceInfo==null||serviceInfo==''){
                    alert("服务信息不能为空！");
                    return;
                }
                var hardware=$(".hardware").val();
                if (hardware==null||hardware==''){
                    alert("硬件设施不能为空！");
                    return;
                }
                var name=$(".name").val();
                if (name==null||name==''){
                    alert("场馆名称不能为空！");
                    return;
                }
                var name=$(".name").val();
                if (name==null||name==''){
                    alert("场馆名称不能为空！");
                    return;
                }

                var introduction=document.getElementById("introduction").value.length;
                console.log(introduction);
                if(introduction>500){
                    alert("场馆简介不能超过500个字符！");
                    return;
                }
                var data = $("#dataForm").serialize();
                var position = {
                    type: "POST",
                    url: "/venue/updateVenue.htm",
                    data: data,
                    dataType: "json",
                    success: function (data) {
                        var jsonReturn = eval(data);
                        if (jsonReturn.resultFlg == 1) {
                            alert("保存成功");
                           // new Toast({message:'修改成功!'}).show();
                            setTimeout("location.href='/member/showMemberInfo.htm'",1500);
                        }else{
                            alert("??");
                          //  new Toast({message:'修改失败!'}).show();
                        }
                    }
                };
                $("#dataForm").ajaxSubmit(position);
            });
        });

        (function($, doc) {
            $.init();
            $.ready(function() {
                var cityPicker3 = new $.PopPicker({
                    layer: 3
                });
                cityPicker3.setData(cityData3);
                var showCityPickerButton = doc.getElementById('showCityPicker3');
                var cityResult3 = doc.getElementById('zoneName');
                var site_range = doc.getElementById("site_range");
                site_range.addEventListener('tap', function(event) {
                    cityPicker3.show(function(items) {
                        var zoneId = (items[2] || {}).value;
                        doc.getElementById("zoneId").value=zoneId;
                        cityResult3.innerText = "" + (items[2] || {}).text;
                        //返回 false 可以阻止选择框的关闭
                        //return false;
                    });
                }, false);
            });
        })(mui, document);

    </script>
</head>
<body>
<!--头部开始-->
<div class="head">
    <div class="return">
        <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/ret.png" alt="" /></a>
    </div>
    主场馆信息
    <i class="edit">完成</i>
</div>
<!-- 内容开始 -->
<form id="dataForm" method="POST" enctype="multipart/form-data">
<div class="content">
    <ul>
        <input type="hidden" name="id" value="${venue.id}" id="id">
        <input type="hidden" name="venueId" value="${venueId}">
        <input type="hidden" name="zoneId" value="${venue.zoneId}" id="zoneId">
        <li class="con_top">
            <a>
                <i class="site_menu">上传头像</i>
                <c:choose>
                    <c:when test="${ empty venue.picRealPath}">
                        <img src="<%=uiPath%>site/images/images/sitelogo.png" alt="" class="sitelogo" id="touxiang">
                    </c:when>
                    <c:otherwise>
                        <img src="${venue.picRealPath}" class="sitelogo" id="touxiang">
                    </c:otherwise>
                </c:choose>
                <input type="file" name="venueLogo" value="" class="trainlogo_sel" accept="image/*" multiple>
            </a>
        </li>
        <li class="con_top">
            <i class="site_menu">场馆名称</i>
            <input type="text" name="name" value="${venue.name}" placeholder="*请填写主场馆名称" class="name ipt_w">
        </li>

        <li class="con_top" id="site_range">
            <a>
                <i class="site_menu" id="cityResult3">场馆所属区域</i>
                <span id="zoneName">${zoneName}</span>
            </a>
        </li>
        <li class="con_top">
            <i class="site_menu">详细地址</i>
            <input type="text" name="address" value="${venue.address}" placeholder="*请填写详细地址" class="address ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">联系人</i>
            <input type="text" name="contact" value="${venue.contact}" placeholder="*请填联系人" class="contact ipt_w">
        </li>
        <li class="con_top">
            <a>
                <i class="site_menu">联系人性别</i>
                <span class="sex_">${venue.sex eq 1 ? "男":"女"}</span>
                <input type="text" name="sex" value="${venue.sex}" class="sex_btn" style="opacity: 0">
            </a>
        </li>
        <li class="con_top">
            <i class="site_menu">联系电话</i>
            <input type="text" name="telephone" value="${venue.telephone}" placeholder="*请填写联系电话" maxlength="11" class="telephone">
        </li>
        <li class="con_top">
            <a>
                <i class="site_menu">营业开始时间</i>
                <input type="text" name="startTime" value="${venue.startTime}" placeholder="09:00" id="start_t" class="spe_ipt">
            </a>
        </li>
        <li class="con_top">
            <a>
                <i class="site_menu">营业结束时间</i>
                <input type="text" name="endTime"  value="${venue.endTime}" placeholder="24:00" id="start_e" class="spe_ipt">
            </a>
        </li>
        <li class="con_top">
            <i class="site_menu">营业执照编号</i>
            <input type="text" name="licenseNo" value="${venue.licenseNo}" placeholder="*请填写营业执照编号" class="licenseNo ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">法人代表</i>
            <input type="text" name="artificilPerson" value="${venue.artificilPerson}" placeholder="*请填写法人代表" class="artificilPerson ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">组成形式</i>
            <input type="text" name="organizationType" value="${venue.organizationType}" placeholder="*请填写组织形式" class="organizationType ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">公交信息</i>
            <input type="text" name="busInfo" value="${venue.busInfo}" placeholder="*请填写公交信息" class="busInfo ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">地铁信息</i>
            <input type="text" name="subwayInfo" value="${venue.subwayInfo}" placeholder="*请填写地铁信息" class="subwayInfo ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">服务信息</i>
            <input type="text" name="serviceInfo" value="${venue.serviceInfo}" placeholder="*标准格式如：停车位：免费停车；场馆卖品：饮食" class="serviceInfo ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">硬件设施</i>
            <input type="text" name="hardware" value="${venue.hardware}" placeholder="*格式如：场地：塑胶场地；灯光：侧灯" class="hardware ipt_w">
        </li>
        <li class="con_top">
            <a>
                <i class="site_menu">是否可退款</i>
                <span class="delmoney_">${venue.isRefund eq 1 ? "是":"否"}</span>
                <input type="hidden" name="isRefund" value="${venue.isRefund}" class="delmoneyipt" style="opacity: 0" id="refund_mon">
            </a>
        </li>
        <li class="con_top" id="refund_show">
            <i class="site_menu">最晚退款时间</i>
            <b>提前</b>
            <span class="plusminus"><img src="<%=uiPath%>site/images/images/minus.png" alt="" id="minus" class="minus"></span>
            <input type="text" name="" value="0" class="lately" style="ime-mode:Disabled">
            <span class="plusminus" style="margin-right:48px"><img src="<%=uiPath%>site/images/images/plus.png" alt="" id="plus" class="plus"></span>
            <b>小时</b>
        </li>
        <li class="con_bottom">
            <i>场馆简介</i>
            <br>
            <textarea  class="textwrap"  name="introduction" id="introduction" placeholder="请填写场馆介绍（不能超过500字符）">${venue.introduction}</textarea>

        <%--li class="con_bottom">
            <i class="site_menu">场馆简介</i>
            <br>
            <c:choose>
                <c:when test="${empty venue.introduction}">
                    <div class="textwrap" contenteditable="true" id="exp"  >请填写场馆介绍</div>
                </c:when>
                <c:when test="${not empty venue.introduction}">
                    <input type="text" value="${venue.introduction}" class="introduction ipt_w">
                </c:when>
            </c:choose>
            <input type="hidden" name="introduction" value="${venue.introduction}" id="introduction">
        </li>--%>
        <li class="con_bottom">
            <p>营业执照照片</p>
            <div class="imgwrap">
                <c:choose>
                    <c:when test="${empty venue.licenseUrl}">
                        <img src="<%=uiPath%>site/images/cameralogo.png" alt="" id="idcard_fsrc"  onclick="imageSelect()">
                    </c:when>
                    <c:when test="${not empty venue.licenseUrl}">
                        <img src=${venue.licenseUrl} alt="" id="idcard_fsrc" onclick="imageSelect()">
                    </c:when>
                </c:choose>
                <input type="file" name="licenseFile"  class="idcard_front" accept="image/*" multiple id="idcard_f">
            </div>
        </li>
        <li class="con_bottom">
            <p>实景图（最多可上传6张）</p>
            <div class="imgwrap" id="realphoto">
                <%--<c:forEach  items="${fileDataList}" var="item">--%>
                    <%--<c:choose>--%>
                       <%--<c:when test="${empty fileDataList}">--%>
                            <%--<img src="<%=uiPath%>site/images/cameralogo.png" alt="" id="idcard_bsrc">--%>
                        <%--</c:when>--%>
                       <%--<c:otherwise>--%>
                            <%--<img src="${item.url}" alt="" id="idcard_bsrc">--%>
                       <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
               <%--</c:forEach>--%>
                    <c:forEach  items="${fileDataList}" var="item">
                        <img src="${item.url}" alt="" id="idcard_bsrc"  class="${item.id}" onclick="del(${item.id})">
                    </c:forEach>
                    <img src="<%=uiPath%>/site/images/cameralogo.png" alt="" id="idcard_bsrc" onclick="fileSelect()">
                <%--<input type="file" name="venuePic" value="" class="idcard_front" id="photos" accept="image/*" multiple>--%>
                    <input type="file" id="file_input0" style="display:none;" onchange="fileSelected(this);" name="picData0"/>
                    <input type="file" id="file_input1" style="display:none;" onchange="fileSelected(this);" name="picData1"/>
                    <input type="file" id="file_input2" style="display:none;" onchange="fileSelected(this);" name="picData2"/>
                    <input type="file" id="file_input3" style="display:none;" onchange="fileSelected(this);" name="picData3"/>
                    <input type="file" id="file_input4" style="display:none;" onchange="fileSelected(this);" name="picData4"/>
                    <input type="file" id="file_input5" style="display:none;" onchange="fileSelected(this);" name="picData5"/>
            </div>
        </li>
    </ul>
    <!-- 性别选择框 -->
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
    <!-- 是否可退款 -->
    <div class="delmoney_wrap">
        <div class="delmoney_sel">
            是否可退款
            <div class="man_wrap">
                是<input type="radio" name="delmoney" value="1" checked="checked">
            </div>
            <div class="wom_wrap">
                否<input type="radio" name="delmoney" value="0">
            </div>
        </div>
    </div>
</div>
</form>
<script>

    var size = '${lengh}';
    var fileId = 1;
    var imageId=1;
    $(function(){
        var isRefund=${venue.isRefund};
        if (isRefund==1){
            $("#refund_show").show();
        }else {
            $("#refund_show").hide();
        }

        if(parseInt(size)>=0){
            fileId = parseInt(size);
        }


        //上传头像
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#touxiang').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        $(".trainlogo_sel").change(function(){
            readURL(this);
        });

        //性别选择
        var dHeight=$(document).height()+"px";
        var dWidth=$(document.body).width()+"px";
        $(".sexsel_wrap").css({
            "width":dWidth,
            "height":dHeight
        });
        $(".sex_btn").parents("li").click(function(){
            $(".sexsel_wrap").css('display','block');
            var ab_top=($(window).scrollTop()+($(document).height()/5))+"px";
            $(".sex_sel").css("top",ab_top);
        });
        $("input[name=sex]").on("click",function(){
            $(".sexsel_wrap").css("display","none");
            var sex_opt=$(this).val();
            if(sex_opt==1){
                $(".sex_").html("男");
                //$(".sex_btn").attr("value","男");
            }else{
                $(".sex_").html("女");
                //$(".sex_btn").attr("value","女");
            }
            $(".sex_btn").attr("value", sex_opt);
        });

        //是否可退款
        var dHeight=$(document).height()+"px";
        var dWidth=$(document.body).width()+"px";
        $(".delmoney_wrap").css({
            "width":dWidth,
            "height":dHeight
        });
        $(".delmoneyipt").parents("li").click(function(){
            $(".delmoney_wrap").css('display','block');
            var ab_top=($(window).scrollTop()+($(document).height()/5))+"px";
            $(".delmoney_sel").css("top",ab_top);
        });
        $("input[name=delmoney]").on("click",function(){
            $(".delmoney_wrap").css("display","none");
            var sex_opt=$(this).val();

            if(sex_opt==1){
                $(".delmoney_").html("是");
                //$(".delmoneyipt").attr("value","是");
                $("#refund_show").show();
            }else{
                $(".delmoney_").html("否");
                //$(".delmoneyipt").attr("value","否");
                $("#refund_show").hide();
            }
            $(".delmoneyipt").attr("value", sex_opt);
        });

        //营业开始时间
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
            nowText: "现在",
            startYear: currYear - 50, //开始年份
            endYear: currYear + 10 //结束年份
        };

        var optTime = $.extend(opt['time'], opt['default']);
        $("#start_t").mobiscroll(optTime).time(optTime);

        //营业结束时间
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
            nowText: "现在",
            startYear: currYear - 50, //开始年份
            endYear: currYear + 10 //结束年份
        };
        var optTime = $.extend(opt['time'], opt['default']);
        $("#start_e").mobiscroll(optTime).time(optTime);

        //上传营业执照
        function readURL1(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#idcard_fsrc').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        $("#idcard_f").change(function(){
            readURL1(this);
        });

//        //上传实景照片
//        var imgCount=0;
//        function readURL3(input) {
//            if (input.files && input.files[0]) {
//                var reader = new FileReader();
//                reader.onload = function (e) {
//                    var imgs="<img src='"+e.target.result+"'>";
//                    $("#realphoto").before(imgs);
//                    if(imgCount>5){
//                        $("#realphoto").hide();
//                    }
//                }
//                reader.readAsDataURL(input.files[0]);
//            }
//        }
//        $("#photos").change(function(){
//            readURL3(this);
//            imgCount++;
//        });

        /*//填写场馆简介
        $("#exp").focus(function(){
            $(this).html("");
        });
        $("#exp").blur(function(){
            var textword=$(this).html();
            if(textword==""){
                $("#exp").html("请填写场馆简介");
            }else{
                $("#introduction").val(textword);
            }
        });*/

        //可退款时间
        function check(){
            var i=$(".lately").attr("value");
            if ( isNaN(i) ) {
                $(".lately").attr("value","");
            }
        }
        $(".lately").keyup(function(){
            check();
        });
        //减按钮
        $("#minus").click(function(){
            var i=$(".lately").attr("value");
            i--;
            if(i<=0){
                i=0;
            }
            $(".lately").attr("value",i);
        });
        //加按钮
        $("#plus").click(function(){
            var i=$(".lately").attr("value");
            i++;
            $(".lately").attr("value",i);
        });

        /*//区域选择
        $("#site_range").click(function(){
            var siteSelect = new IosSelect(1,[site_range],
                {
                    title: '所属区域',
                    itemHeight: 35,
                    headerHeight:100,
                    oneLevelId: site_range,
                    callback: function (selectOneObj) {
                        $("#site_range span").html(selectOneObj.value);
                    }
                });
        });*/
    });
    function del(id) {
        if(!confirm("确定删除吗？")){
            return;
        }
        $.ajax({
            url:"/fileData/deleteFileData.htm",
            type:"POST",
            data:{id:id},
            async:false,
            dataType:"json",
            success:function (data) {
                var jsonReturn=eval(data);
                if(jsonReturn.resultFlg==1){
                        location.href="/venue/mySite.htm?id=${memberId}";
                }else{
                    new Toast({message:jsonReturn.resultMsg}).show();

                }
            }
        });
    }
    function imageSelect() {
        if(imageId>1){
            alert("营业执照最多上传1张");
        }
        imageId+=1;
    }
    function fileSelect() {
        if(fileId<6){
            document.getElementById("file_input"+fileId).click();
        }else{
            alert("最多上传6张");
        }
    }

    function fileSelected(fileDom) {

        //判断是否支持FileReader
        if (window.FileReader) {
            var reader = new FileReader();
        } else {
            alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
        }

        //获取文件
        var file = fileDom.files[0];
        var imageType = /^image\//;
        //是否是图片
        if (!imageType.test(file.type)) {
            alert("请选择图片！");
            return;
        }
        //读取完成
        reader.onload = function(e) {
            //获取图片dom
            var imgs="<img src='"+e.target.result+"'>";
            /*$("#realphoto").before(imgs);*/
            if(fileId<=6){
                $("#idcard_bsrc").before(imgs);
            }else{
                $("#idcard_bsrc").before(imgs);
                $("#idcard_bsrc").hide();
            }
            //图片路径设置为读取的图片
        };
        reader.readAsDataURL(file);
        fileId += 1;
        //alert(fileId);
    }
</script>
</body>
</html>