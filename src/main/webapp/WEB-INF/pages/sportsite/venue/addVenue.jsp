<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>分场馆信息</title>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/new_train.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link href="<%=uiPath%>site/css/mobiscroll_date.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=uiPath%>site/css/iosSelect.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/newort.js"></script>
    <script src="<%=uiPath%>lib/jqueryForm/jquery.form.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script src="<%=uiPath%>site/js/iscroll1.js"></script>
    <script src="<%=uiPath%>site/js/sports.js"></script>
    <script src="<%=uiPath%>site/js/iosSelect.js"></script>
    <script type="text/javascript">
        var childVenueId = '${childVenue.id}';

        $(function () {

            $("#sportstype").click(function(){
                var sports_type=new IosSelect(2,[sports,sports_item],{
                    title:'运动类型',
                    itemHeight:35,
                    relation:[1,0,0,0],
                    callback:function(selectOneObj, selectTwoObj){
                        $("#sport_txt").html(selectTwoObj.value);
                        console.log(selectTwoObj)
                        $("#sportId").val(selectTwoObj.id);
                    }
                })
            });

            if(childVenueId!=''){
                $("#id").val(childVenueId);
            }else{
                $("#id").val(0);
            }

            $(".edit").click(function () {
                var venueName=$(".venueName").val();
                if (venueName==null||venueName==''){
                    alert("场馆名称不能为空！");
                    return;
                }
                var price=$(".price").val();
                if (price==null||price==''){
                    alert("单价不能为空！");
                    return;
                }
                var facility=$(".facility").val();
                if (facility==null||facility==''){
                    alert("场馆设施不能为空！");
                    return;
                }
                var serviceInfo=$(".serviceInfo").val();
                if (serviceInfo==null||serviceInfo==''){
                    alert("服务信息不能为空！");
                    return;
                }
                var tips=$(".tips").val();
                if (tips==null||tips==''){
                    alert("提示信息不能为空！");
                    return;
                }
                var lately=$(".lately").val();
                if (lately==null||lately==''){
                    alert("最大容纳人数不能为空！");
                    return;
                }
                var data = $("#dataForm").serialize();
                var position = {
                    type: "POST",
                    url: "/venue/savaChildVenue.htm?childVenueId=${childVenue.id}",
                    data: data,
                    dataType: "json",
                    success: function (data) {
                        var jsonReturn = eval(data);
                        if (jsonReturn.resultFlg == 1) {
                            alert("保存成功");
                            //new Toast({message:'保存成功!'}).show();
                            location.href="/venue/otherSite.htm?id=${memberId}";
                        }else{
                            new Toast({message:'保存失败!'}).show();
                        }
                    }
                };
            $("#dataForm").ajaxSubmit(position);
            });
        });


    </script>
</head>
<body>
<!--头部开始-->
<div class="head">
    <div class="return">
        <a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/ret.png" alt="" /></a>
    </div>
    分场馆信息
    <i class="edit">完成</i>
</div>
<!-- 内容开始 -->
<form id="dataForm" method="POST" enctype="multipart/form-data">
<div class="content">
    <input type="hidden" name="id" value="" id="id">
    <input type="hidden" name="venueId" value="${venueId}">
    <ul>
        <li class="con_top">
            <a>
                <i class="site_menu">上传头像</i>
                <c:choose>
                    <c:when test="${ empty childVenue.picRealPath}">
                        <img src="<%=uiPath%>site/images/images/sitelogo.png" alt="" class="sitelogo" id="touxiang">
                    </c:when>
                    <c:otherwise>
                        <img src="${childVenue.picRealPath}" class="sitelogo" id="touxiang">
                    </c:otherwise>
                </c:choose>
                <input type="file" name="childVenueLogo" value="" class="trainlogo_sel" accept="image/*" multiple>
            </a>
        </li>
        <li class="con_top">
            <i class="site_menu">分场馆名称</i>
            <input type="text" name="venueName" value="${childVenue.venueName}" placeholder="*请填写分场馆名称" class="venueName ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">编号</i>
            <span class="plusminus"><img src="<%=uiPath%>site/images/images/minus.png" alt="" id="numminus" class="minus"></span>
            <input type="text" name="venueNo" value="${childVenue.venueNo}" placeholder="0" class="lately" style="ime-mode:Disabled" id="numcount">
            <span class="plusminus" style="margin-right:48px"><img src="<%=uiPath%>site/images/images/plus.png" alt="" id="numplus" class="plus"></span>
        </li>
        <li class="con_top" id="sportstype">
            <a>
                <i class="site_menu">运动类型</i>
                <span id="sport_txt">${kindName}</span>
                <input type="hidden" name="sportId" id="sportId" value="${childVenue.sportId} ">
            </a>
        </li>
        <li class="con_top">
            <i class="site_menu">单价</i>
            <b>￥</b><input type="text" name="price" value="${childVenue.price}" placeholder="0.0" class="price">
        </li>
        <li class="con_top">
            <i class="site_menu">特价</i>
            <b>￥</b><input type="text" name="salesPrice" value="${childVenue.salesPrice}" placeholder="0.0" class="salesPrice">
        </li>
        <li class="con_top">
            <i class="site_menu">场馆设施</i>
            <input type="text" name="facility" value="${childVenue.facility}" placeholder="*格式如：场地：塑胶场地；灯光：侧灯" class="facility ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">服务信息</i>
            <input type="text" name="serviceInfo" value="${childVenue.serviceInfo}" placeholder="*标准格式如：停车位：免费停车；场馆卖品：饮食" class="serviceInfo ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">提示信息</i>
            <input type="text" name="tips" value="${childVenue.tips}" placeholder="*请填写提示信息" class="tips ipt_w">
        </li>
        <li class="con_top">
            <i class="site_menu">最大容纳人数</i>
            <span class="plusminus"><img src="<%=uiPath%>site/images/images/minus.png" alt="" id="lagminus" class="minus"></span>
            <input type="text" name="capacity" value="${childVenue.capacity}" class="lately" style="ime-mode:Disabled" id="lagcount" placeholder="*20">
            <span class="plusminus" style="margin-right:48px"><img src="<%=uiPath%>site/images/images/plus.png" alt="" id="lagplus" class="plus"></span>
        </li>
        <li class="con_top">
            <i class="site_menu">排序</i>
            <span class="plusminus"><img src="<%=uiPath%>site/images/images/minus.png" alt="" id="sortminus" class="minus"></span>
            <input type="text" name="ord" value="${childVenue.ord}" class="lately" style="ime-mode:Disabled" id="sort" placeholder="0">
            <span class="plusminus" style="margin-right:48px"><img src="<%=uiPath%>site/images/images/plus.png" alt="" id="sortplus" class="plus"></span>
        </li>
        <li class="con_bottom">
            <p>实景图（最多可上传6张）</p>
            <div class="imgwrap" id="realphoto">
                <%--<c:choose>
                    <c:when test="${empty fileDataList}">
                        <img src="<%=uiPath%>/site/images/cameralogo.png" alt="" id="idcard_bsrc" onclick="fileSelect()">
                    </c:when>
                    <c:otherwise>
                        <c:forEach  items="${fileDataList}" var="item">
                            <img src="${item.url}" alt="" id="idcard_bsrc" onclick="fileSelect()">
                        </c:forEach>
                    </c:otherwise>
                </c:choose>--%>
                    <c:forEach  items="${fileDataList}" var="item">
                        <img src="${item.url}" alt="" id="idcard_bsrc"  class="${item.id}" onclick="del(${item.id})">
                    </c:forEach>
                <img src="<%=uiPath%>/site/images/cameralogo.png" alt="" id="idcard_bsrc" onclick="fileSelect()">
                <%--<input type="file" name="pics" value="" class="idcard_front" id="photos" accept="image/*" multiple="multiple">--%>
                <input type="file" id="file_input1" style="display:none;" onchange="fileSelected(this);" name="picData1"/>
                <input type="file" id="file_input2" style="display:none;" onchange="fileSelected(this);" name="picData2"/>
                <input type="file" id="file_input3" style="display:none;" onchange="fileSelected(this);" name="picData3"/>
                <input type="file" id="file_input4" style="display:none;" onchange="fileSelected(this);" name="picData4"/>
                <input type="file" id="file_input5" style="display:none;" onchange="fileSelected(this);" name="picData5"/>
                <input type="file" id="file_input6" style="display:none;" onchange="fileSelected(this);" name="picData6"/>
            </div>
        </li>
    </ul>
</div>
</form>
<script>

    var size = '${lengh}';
    var fileId = 1;
    $(function(){

        if(parseInt(size)>0){
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

        //上传实景照片
        /*var imgCount=0;
        function readURL3(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var imgs="<img src='"+e.target.result+"'>";
                    $("#realphoto").before(imgs);
                    if(imgCount>5){
                        $("#realphoto").hide();
                    }
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        $("#photos").change(function(){
            readURL3(this);
            imgCount++;
        });*/

        //可退款时间
        //只能输入数字
        function check(item){
            var i=$(item).attr("value");
            if ( isNaN(i) ) {
                $(item).attr("value","");
            }
        }
        $("#numcount").keyup(function(){
            check("#numcount");
        });
        $("#lagcount").keyup(function(){
            check("#lagcount");
        });
        $("#sort").keyup(function(){
            check("#sort");
        });
        //减按钮
        function minusbtn(obj_m){
            var i=$(obj_m).attr("value");
            i--;
            if(i<=0){
                i=0;
            }
            $(obj_m).attr("value",i);
        }
        $("#numminus").click(function(){
            minusbtn("#numcount");
        });
        $("#lagminus").click(function(){
            minusbtn("#lagcount");
        });
        $("#sortminus").click(function(){
            minusbtn("#sort");
        });
        //加按钮
        function plusbtn(obj_p){
            var i=$(obj_p).attr("value");
            i++;
            $(obj_p).attr("value",i);
        }
        $("#numplus").click(function(){
            plusbtn("#numcount");
        });
        $("#lagplus").click(function(){
            plusbtn("#lagcount");
        });
        $("#sortplus").click(function(){
            plusbtn("#sort");
        });
    });

    function del(id) {
        $.ajax({
            url:"/fileData/deleteFileData.htm",
            type:"POST",
            data:{id:id},
            async:false,
            dataType:"json",
            success:function (data) {
                var jsonReturn=eval(data);
                if(jsonReturn.resultFlg==1){
                    if(!confirm("确定删除吗？")){
                        return;
                    }else {
                        location.href="/venue/addVenue.htm?id=${memberId}&flag=Detail&childVanueId=${childVanueId}";
                    }
                }else{
                    new Toast({message:jsonReturn.resultMsg}).show();
                }
            }
        });
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