<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>教练信息</title>
	<base href="<%=basePath%>">
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/new_train.css">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
	<link href="<%=uiPath%>site/css/mobiscroll_date.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=uiPath%>site/css/drop-down.css" />
	<script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
	<script src="<%=uiPath%>lib/jqueryForm/jquery.form.js"></script>
	<script src="<%=uiPath%>site/js/newort.js"></script>
	<script src="<%=uiPath%>site/js/mobiscroll_date.js"></script>
	<script src="<%=uiPath%>site/js/mobiscroll.js"></script>
	<script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
	<%--<script src="<%=uiPath%>site/js/jquery-ui.min.js"></script>--%>
	<%--<script src="<%=uiPath%>site/js/select-widget-min.js"></script>--%>

	<%--选择城市区域--%>
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>lib/mui/css/mui.picker.css">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>lib/mui/css/mui.poppicker.css">
	<script type="text/javascript" src="<%=uiPath%>lib/mui/js/mui.min.js"></script>
	<script type="text/javascript" src="<%=uiPath%>lib/mui/js/mui.picker.js"></script>
	<script type="text/javascript" src="<%=uiPath%>lib/mui/js/mui.poppicker.js"></script>
	<script type="text/javascript" src="<%=uiPath%>lib/mui/js/city.data-3.js"></script>
	<script type="text/javascript">
		var kinds=[{value:'1',text:'有氧运动',children:[{value:'11',text:'篮球'},{value:'12',text:'足球'}]},{value:'2',text:'无氧运动',children:[{value:'21',text:'游泳'},{value:'22',text:'攀岩'}]}];
        $(function(){
            $.ajax({
                type:'POST',
                url:'/kinds/getChildVenueKinds.htm',
                dataType:'json',
				async:false,
                success:function(data){
                    kinds = [];

                    if(data.resultFlg==1){
                        var list = data.resultData;
                        $.each(list,function(i,item){
                            var obj = {};
                            obj.value = item.parentKinds.id+'';
                            obj.text = item.parentKinds.name+'';
                            obj.children = [];
                            kinds.push(obj);
                            var childList = item.childList;
                            $.each(childList,function(j,child){
                                var childObj = {};
                                childObj.value = child.id+'';
                                childObj.text = child.name+'';
                                obj.children.push(childObj);
                            });
                        });
                        console.log(kinds);
                        console.log("ajax");
                    }else{
                        alert(data.resultMsg);
                    }
                }
            });
        });
	</script>
	<script type="text/javascript">
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
                        cityResult3.innerText = (items[2] || {}).text+'';// (items[0] || {}).text + " " + (items[1] || {}).text + " " +
                        //返回 false 可以阻止选择框的关闭
                        //return false;
                    });
                }, false);
            });
        })(mui, document);
        (function($, doc) {
            $.init();
            $.ready(function() {
                var cityPicker3 = new $.PopPicker({
                    layer: 2
                });
                console.log(kinds);
                console.log("ddddd");
                cityPicker3.setData(kinds);
                var showCityPickerButton = doc.getElementById('showCityPicker3');
                var cityResult3 = doc.getElementById('kindName');
                var site_range = doc.getElementById("site_range2");
                site_range.addEventListener('tap', function(event) {
                    cityPicker3.show(function(items) {
                        var sportId = (items[1] || {}).value;
                        doc.getElementById("sportId").value=sportId;
                        cityResult3.innerText = " " + (items[1] || {}).text ;
                        //返回 false 可以阻止选择框的关闭
                        //return false;
                    });
                }, false);
            });
        })(mui, document);

	</script>
	<script type="text/javascript">
        $(function () {


            $(".edit").click(function () {

                var name = $(".name").val();
                if (name == null || name == '') {
                    new Toast({message:'姓名不能为空!'}).show();
                    return;
                }

                if (document.getElementById("teachCareer").value.length>400){
                    alert("教学经历输入过长！");
                    return 0;
				}
                if (document.getElementById("intro").value.length>500){
                    alert("个人简介输入过长！");
                    return 0;
                }
                var data = $("#dataForm").serialize();
                var position={
                    url:"/coach/updateCoachInfo.htm",
                    type:"POST",
                    data:data,
                    dataType:"json",
                    success:function (data) {
                        var jsonReturn=eval(data);
                        if(jsonReturn.resultFlg==1){
                            new Toast({message:'修改成功!'}).show();
                            setTimeout("location.href='/coach/coachInformation.htm'",3000);
                        }else{
                            new Toast({message:'修改失败!'}).show();
                            return;
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
		教练信息
		<i class="edit">编辑</i>
	</div>
<!-- 内容开始 -->
<form id="dataForm" method="POST" enctype="multipart/form-data">
	<div class="content">
		<ul>
			<input type="hidden" name="id" value="${coach.id}">
			<input type="hidden" name="data" value="${coach.zoneId}" id="zoneId">
			<input type="hidden" name="sportId" value="${coach.sportId}" id="sportId">
			<li class="con_top" id="headPic">
				<a>
					<i>教练头像</i>
					<c:choose>
						<c:when test="${ empty coach.picRealPath}">
							<img src="<%=uiPath%>site/images/defaultlogo.png" class="trainlogo" id="touxiang">
						</c:when>
						<c:otherwise>
							<img src=${coach.picRealPath} alt="" class="trainlogo" id="touxiang">
						</c:otherwise>
					</c:choose>
					<input type="file" name="logoFile"  class="trainlogo_sel" accept="image/*" multiple>
				</a>
			</li>
			<li class="con_top">
				<i>教练姓名</i>
				<input type="text" name="name" value="${coach.name}" class="name ipt_w">
			</li>
			<li class="con_top" id="site_range">
				<a>
					<i class="site_menu" id="cityResult3">所属区域</i>
					<span id="zoneName">${coach.zoneName}</span>
				</a>
			</li>

			<li class="con_top">
				<i>上课地址</i>
				<input type="text" name="data2" value="${coach.courseAddress}" placeholder="请填写地址" class="ipt_w">
			</li>
			<li class="con_top">
				<i>常驻场馆</i>
				<input type="text" name="venueName" value="${coach.venueName}" placeholder="请填写常驻馆名称" class="ipt_w">
			</li>
			<li class="con_top">
				<a>
					<i>性别</i>
					<span class="sex_"><c:choose>
						<c:when test="${coach.sex==1}">男</c:when>
						<c:when test="${coach.sex==2}">女</c:when>
						<c:otherwise></c:otherwise>
					</c:choose></span>
					<input type="hidden"  name="sex" value="${coach.sex}" class="sex_btn">
				</a>
			</li>
			<li class="con_top">
				<a>
					<i>生日</i>
					<input type="text" name="birthday0" value="<fmt:formatDate value='${coach.birthday}' pattern='yyyy-MM-dd'/>" id="USER_AGE" placeholder="请填写生日">
				</a>
			</li>
			<li class="con_top">
				<i>身高</i>
				<input type="text" name="height" value="${coach.height}" placeholder="请填写身高(CM)">
			</li>
			<li class="con_top">
				<i>体重</i>
				<input type="text" name="weight" value="${coach.weight}" placeholder="请填写体重(KG)">
			</li>
			<li class="con_top" id="site_range2">
				<a>
					<i class="site_menu" id="kindResult3">运动类型</i>
					<span id="kindName">${sportName}</span>
				</a>
			</li>

			<li class="con_bottom">
				<i>教学经历</i>
				<br>
				<textarea class="textwrap"  name="teachingCareer" placeholder="请填写教学经历" id="teachCareer">${coach.teachingCareer}</textarea>
				<%--<input type="text" name="teachingCareer" value="${coach.teachingCareer}" placeholder="请填写教学经历">--%>

			</li>
			<li class="con_bottom">
				<i>个人简介</i>
				<br>
				<%--<input type="text" name="intro" value="${coach.intro}" placeholder="请填写个人简介">--%>
				<textarea  class="textwrap"  name="intro" placeholder="请填写个人简介" id="intro">${coach.intro}</textarea>

			</li>
			<li class="con_bottom">
				<p>身份证照片（正面）</p>
				<div class="imgwrap" id="imgwrapf">
					<c:choose>
						<c:when test="${empty coach.idCardFront}">
							<img src="<%=uiPath%>site/images/cameralogo.png" alt="" id="idcard_fsrc">
						</c:when>
						<c:otherwise>
							<img src="${coach.idCardFront}" alt="" id="idcard_fsrc">
						</c:otherwise>
					</c:choose>

				</div>
					<input type="file" name="idCardFrontFile"  class="idcard_front" accept="image/*" multiple id="idcard_f">
			</li>
			<li class="con_bottom">
				<p>身份证照片（反面）</p>
				<div class="imgwrap" id="imgwrapb">
					<c:choose>
						<c:when test="${empty coach.idCardBack}">
							<img src="<%=uiPath%>site/images/cameralogo.png" alt="" id="idcard_bsrcb">
						</c:when>
						<c:otherwise>
							<img src="${coach.idCardBack}" alt="" id="idcard_bsrcb">
						</c:otherwise>
					</c:choose>

				</div>
					<input type="file" name="idCardBackFile" class="idcard_front" accept="image/*" multiple id="idcard_b">
			</li>
			<li class="con_bottom">
				<p>实景照片（最多可上传6张）</p>
				<div style="display: none" id="rp"></div>
				<div class="imgwrap1" id="realphoto">
					<c:forEach  items="${fileDataList}" var="item">
						<img src="${item.url}" alt=""  class="${item.id}" onclick="del(${item.id})">
					</c:forEach>
					<img src="<%=uiPath%>/site/images/cameralogo.png" alt="" id="idcard_bsrc" onclick="fileSelect()">
					<%--<input type="file" name="1" class="idcard_front" id="photos" accept="image/*" multiple>--%>
					<input type="file" id="file_input0" style="display:none;" onchange="fileSelected(this);" name="picData0"/>
					<input type="file" id="file_input1" style="display:none;" onchange="fileSelected(this);" name="picData1"/>
					<input type="file" id="file_input2" style="display:none;" onchange="fileSelected(this);" name="picData2"/>
					<input type="file" id="file_input3" style="display:none;" onchange="fileSelected(this);" name="picData3"/>
					<input type="file" id="file_input4" style="display:none;" onchange="fileSelected(this);" name="picData4"/>
					<input type="file" id="file_input5" style="display:none;" onchange="fileSelected(this);" name="picData5"/>

				</div>
			</li>
		</ul>

	</div>
</form>
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
	<script type="text/javascript">
        var size = '${lengh}';
        var fileId = 1;
		$(function(){
            if(parseInt(size)>=0){
                fileId = parseInt(size);
            }

			//上传头像
			function readURL(input) {
				if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function (e) {
						$('#touxiang').attr('src', e.target.result);
					};
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


				}else{
					$(".sex_").html("女");
				}
                $(".sex_btn").attr("value", sex_opt);
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

			//上传正面身份证
			function readURL1(input) {
				if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function (e) {
						$('#idcard_fsrc').attr('src', e.target.result);
					};
					reader.readAsDataURL(input.files[0]);
				}
			}

            $("#imgwrapf").on("click",function () {
                $("#idcard_f").trigger("click");
            });
            $("#idcard_f").change(function(){
                readURL1(this);
            });

			//上传反面身份证
			function readURL2(input) {
				if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function (e) {
						$('#idcard_bsrcb').attr('src', e.target.result);
					};
					reader.readAsDataURL(input.files[0]);
				}
			}
			$("#imgwrapb").on("click",function () {
                $("#idcard_b").trigger("click");
            });
            $("#idcard_b").change(function(){
                readURL2(this);
            });



			//上传实景照片
//			$("#idcard_bsrc1").on("click",function () {
//			    if($(".imgwrap1 img").length=7){
//			        alert("最多上传6张图片！");
//			        return;
//				}
//				var inputHtml="<input type='file' id='"+imgCount+"'>";
//
//                $("#rp").append(inputHtml);
//
//                $("#"+imgCount).trigger("click");
//                $("#"+imgCount).change(function(){
//                    readURL3(this);
//                    imgCount++;
//                });
//            });

			//上传实景照片
//			var imgCount=0;
//			function readURL3(input) {
//				if (input.files && input.files[0]) {
//						var reader = new FileReader();
//						reader.onload = function (e) {
//
//							var imgs="<img src='"+e.target.result+"'>";
//							var inp="<input type='file'>";
//							$("#realphoto").before(imgs);
//							if(imgCount>5){
//								$("#realphoto").hide();
//							}
//					};
//					reader.readAsDataURL(input.files[0]);
//				}
//			}
//			$("#photos").change(function(){
//				readURL3(this);
//				imgCount++;
//			});

			//填写教学经历
//			$("#exp").blur(function(){
//				var textword=$(this).html();
//				if(textword==""){
//					$("#exp").html("请填写教学经历");
//				}
//			});

			//填写个人介绍

//			$("#intro").blur(function(){
//				var textword=$(this).html();
//				if(textword==""){
//					$("#intro").html("请填写个人简介");
//				}
//			});


		});
		//删除实景图
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
                            location.href="/coach/coachInformation.htm?id=${memberId}";
                    }else{
                        new Toast({message:jsonReturn.resultMsg}).show();

                    }
                }
            });
        }
        //shijtu

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