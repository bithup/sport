<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>我的课程列表</title>
	<base href="<%=basePath%>">
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/new_courselist.css">
	<link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
	<script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
	<script src="<%=uiPath%>site/js/newort.js"></script>
	<script>
        var page=1;
        var pageSize=80;
            function reqData(op) {
                if("up"==op){page++;}
                if("down"==op){page=1;}
                var url="/coach/getCourseListByMemId.htm";
                var coachId=${coachId};
                var result='';
                $.ajax({
                    type:'POST',
                    url:url,
                    data:{coachId:coachId},
                    dataType:'json',
                    success:function (data) {
                        var jsonResult=eval(data);
                        if(jsonResult.resultFlg==1){
                            $(jsonResult.resultData).each(function () {
                                var courseType='';
                                if(this.courseType==1){courseType='月';}
                                if(this.courseType==2){courseType='季';}
                                if(this.courseType==3){courseType='年';}

                                    result+="<li>"+
                                        "<a href='/coach/mycourse.htm?memberId=${memberId}&flag=Detail&coachCourseId="+this.id+"'>"+
                                        "<span class='coursetit'>" +
                                        "<i class='tit_left'>"+this.courseName+"（"+courseType+"）</i>"+
                                        "<i class='tit_right'>"+this.salesPrice+"元</i>"+
                                        "</span>"+
                                        "<span class='coursecon'>"+this.introduct+"</span>" +
                                        "</a>"	+
                                        "<b class='delete' onclick='cancel("+this.id+")'>删除</b>"+
                                        "</li>";
                       });
                            if(op=="up"){
                                $('.show').append(result);
                            }else {
                                $('.show').html(result);
                            }
                        }
					}
				});
			}
        $(function(){
            console.log("start");
            reqData("down");
        });
//删除数据
        function cancel(id) {
            if(confirm("确认删除课程吗？")){
                $.ajax({
                    url: "/coach/deleteCoachCourse.htm?id="+id,
                    type: "POST",
                    data: {orderId: id},
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        var jsonResult = eval(data);
                        if(jsonResult.resultFlg=="1"){
                           alert("操作成功");
                           location.href="/coach/mycourselist.htm?memberId=${memberId}";
                        } else {
                            alert(jsonResult.resultMsg);
                        }
                    },
                    error: function () {
                        alert("ajax error");
                    }
                });
            }

        }
	</script>
</head>
<body>
<!--头部开始-->
	<div class="head">
		<div class="return">
			<a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/ret.png" alt="" /></a>
		</div>
		我的课程列表
		<i class="plus" id="mycourse">添加<a></a></i>
		<i class="edit">编辑</i>
	</div>
<!-- 内容开始 -->
	<div class="content">
		<ul class="show">

		</ul>
	</div>
	<script>
		$(function(){
			$(".edit").click(function(){
				$(".delete").toggle();
			});
		});
        $(function () {
            $("#mycourse").click(function () {
                location.href="coach/mycourse.htm?flag=add&memberId=${memberId}&coachCourseId=${coachCourseId.id}";
            });
        });
	</script>
</body>
</html>