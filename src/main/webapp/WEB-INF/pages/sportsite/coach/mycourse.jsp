<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>我的课程</title>
	<base href="<%=basePath%>">
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/new_train.css">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
	<link href="<%=uiPath%>site/css/mobiscroll_date.css" rel="stylesheet" />
	<script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
	<script src="<%=uiPath%>site/js/newort.js"></script>
	<script src="<%=uiPath%>site/js/mobiscroll_date.js" charset="gb2312"></script>
	<script src="<%=uiPath%>site/js/mobiscroll.js"></script>
	<script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
	<script type="text/javascript">

		var coachCourseId = '${coachCourse.id}';

        $(function () {


            if(coachCourseId!=''){
                $("#id").val(coachCourseId);
			}else{
                $("#id").val(0);
			}

            $(".edit").click(function () {
                var courseName=document.getElementById("courseName").value;
                if(courseName==null|| courseName==""){
                    alert("课程名称不能为空");
                    return;
				}
                var courseType=document.getElementById("courseType").value;
                if(courseType==null|| courseType==""){
                    alert("课程类型不能为空");
                    return;
                }
                var price=document.getElementById("price").value;
                if(price==null|| price==""){
                    alert("单价不能为空");
                    return;
                }
                var salesPrice=document.getElementById("salesPrice").value;
                if(salesPrice==null|| salesPrice==""){
                    alert("优惠价不能为空");
                    return;
                }
                var starttime=document.getElementById("starttime").value;
                if(starttime==null|| starttime==""){
                    alert("开始时间不能为空");
                    return;
                }
                var endtime=document.getElementById("endtime").value;
                if(endtime==null|| endtime==""){
                    alert("结束时间不能为空");
                    return;
                }
                var introduct=document.getElementById("introduct").value;
                if(introduct==null|| introduct==""){
                    alert("课程简介不能为空");
                    return;
                }
                var data = $("#dataForm").serialize();
                $.ajax({
                    type: "POST",
                    url: "/coach/saveCoachCourse.htm",
                    data: data,
                    dataType: "json",
                    success: function (data) {
                        var jsonReturn = eval(data);
                        if (jsonReturn.resultFlg == 1) {
                            new Toast({message:'保存成功!'}).show();
                            setTimeout("refresh()",1000)
                        }else{
                            new Toast({message:'保存失败!'}).show();
                        }
                    }
                });

            });

        });
		function refresh() {
            location.href='coach/mycourselist.htm?memberId=${memberId}';
        }

	</script>
</head>
<body>
<!--头部开始-->
	<div class="head">
		<div class="return">
			<a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/ret.png" alt="" /></a>
		</div>
		我的课程
		<i class="edit">完成</i>
	</div>
<!-- 内容开始 -->
<form id="dataForm" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="id" value="" id="id">
	<input type="hidden" name="coachId" value="${coachId}">
	<div class="content">
		<ul>
			<li class="con_top">
				<i>课程名称</i>
				<input type="text" name="courseName" id="courseName" value="${coachCourse.courseName}" placeholder="请填写课程名称">
			</li>
			<li class="con_top">
				<a>
					<i>课程类型</i>
					<c:choose>
						<c:when test="${empty coachCourse.courseType}">
							<input type="text" name="courseType_" value="月" class="coursetype">
						</c:when>
							<c:when test="${coachCourse.courseType==1}">
								<input type="text" name="courseType_" value="月" class="coursetype">
							</c:when>
							<c:when test="${coachCourse.courseType==2}">
								<input type="text" name="courseType_" value="季" class="coursetype">
							</c:when>
							<c:when test="${coachCourse.courseType==3}">
								<input type="text" name="courseType_" value="年" class="coursetype">
							</c:when>
					</c:choose>
					<input type="hidden" name="courseType" value="1" id="courseType">

				</a>
			</li>
			<li class="con_top">
				<i>单价</i>
				<b>￥</b><input type="text" name="price" id="price" value="${coachCourse.price}" placeholder="0">
			</li>
			<li class="con_top">
				<i>优惠价</i>
				<b>￥</b><input type="text" name="salesPrice" id="salesPrice" value="${coachCourse.salesPrice}" placeholder="0">
			</li>
			<li class="con_top">
				<a>
					<i>开始时间</i>
					<input type="text" name="startDate" value="${coachCourse.startDate}" id="starttime">
				</a>
			</li>
			<li class="con_top">
				<a>
					<i>结束时间</i>
					<input type="text" name="endDate" value="${coachCourse.endDate}" id="endtime">
				</a>
			</li>
			<li class="con_bottom">
				<i>课程介绍</i>
				<br>
				<textarea name="introduct" class="textwrap" placeholder="请填写课程介绍" id="introduct">${coachCourse.introduct}</textarea>
			</li>
		</ul>

<!-- 课程类型选择框 -->
		<div class="sexsel_wrap">
			<div class="sex_sel">
				课程类型
				<div class="man_wrap">
					年<input type="radio" name="course" value="3" checked="checked">
				</div>
				<div class="man_wrap">
					季<input type="radio" name="course" value="2">
				</div>
				<div class="wom_wrap">
					月<input type="radio" name="course" value="1">
				</div>
			</div>
		</div>
	</div>
</form>
	<script>
		$(function(){

			//课程类型选择
			var dHeight=$(document).height()+"px";
			var dWidth=$(document.body).width()+"px";
			$(".sexsel_wrap").css({
				"width":dWidth,
				"height":dHeight
			});
			$(".coursetype").parents("li").click(function(){
				$(".sexsel_wrap").css('display','block');
				var ab_top=($(window).scrollTop()+($(document).height()/5))+"px";
				$(".sex_sel").css("top",ab_top);
			});
			$("input[name=course]").on("click",function(){
				$(".sexsel_wrap").css("display","none");
				var sex_opt=$(this).val();
				if(sex_opt==1){
					$(".coursetype").val("月");
                    $("#courseType").val("1");
				}else if(sex_opt==2){
					$(".coursetype").val("季");
                    $("#courseType").val("2");
				}else{
					$(".coursetype").val("年");
                    $("#courseType").val("3");
				}
			});

			//开始时间
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
			$("#starttime").mobiscroll($.extend(opt['date'], opt['default']));

			//结束时间
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
			$("#endtime").mobiscroll($.extend(opt['date'], opt['default']));

			//填写课程介绍
//			$("#exp").focus(function(){
//				$(this).html("");
//			});
//			$("#exp").blur(function(){
//				var textword=$(this).html();
//				if(textword==""){
//					$("#exp").html("请填写课程介绍");
//				}else{
//				    $("#introduct").val(textword);
//				}
//			});
		});
	</script>
</body>
</html>