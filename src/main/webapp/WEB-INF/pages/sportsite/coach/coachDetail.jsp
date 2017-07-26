<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>教练详情</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/train_xq.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var type=1;
        var dataId=${coach.id};
        var isLogin=${isLogin};
        var flag=${flag};
        $(function(){
            if(flag==1){$(".collect").addClass("collect01");}
            $(".collect").click(function(){
                if(isLogin==0){location.href="/portal/loginInit.htm"}
                else {
                    $.ajax({
                        url:"/house/addToHouse.htm",
                        type:"POST",
                        data:{type:type,dataId:dataId},
                        dataType:"json",
                        success:function (data) {
                            var jsonReturn=eval(data);
                            if(jsonReturn.resultFlg==1){
                                new Toast({message:jsonReturn.resultMsg}).show();
                            }else if(jsonReturn.resultFlg==2){
                                new Toast({message:jsonReturn.resultMsg}).show();
                            }else{
                                new Toast({message:'操作失败'}).show();
                                return;
                            }
                            $(".collect").toggleClass("collect01");
                            if(flag==0){
                                $(".collect").attr("value",1);
                                flag=1;
                            }else{
                                $(".collect").attr("value",0);
                                flag=0;
                            }
                        }
                    });
                }

            })
        })


    </script>
</head>
<body>
<!--头部开始-->
<div class="content wrap">
    <div class="head">
        <div class="return">
            <c:choose>
                <c:when test="${empty type_ and empty status_}">
                    <i><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></i>
                </c:when>
                <c:when test="${!empty type_ and empty status_}">
                    <i><a href="/home/baseInfo.htm?type_=${type_}"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></i>
                </c:when>
                <c:when test="${empty type_ and !empty status_}">
                    <i><a href="/member/myCollect.htm?status_=${status_}"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></i>
                </c:when>
            </c:choose>
        </div>


        教练详情
        <div class="coll collect" value="0"></div>
        <div class="coll"><a href=""><img src="<%=uiPath%>site/images/share01.png" alt="" /></a> </div>
    </div>
    <!--图片开始-->
    <div class="pic">
        <ul>
            <li><img src="${coach.picRealPath}" alt=""/></li>
        </ul>
        <div class="name wrap">
            <div>${coach.name}<br /><p>${coach.kindsName}教练</p><span>${coach.age}岁/${coach.height}cm/${coach.weight}kg</span></div>

        </div>
    </div>
    <!--详情开始-->
    <div class="name_yd wrap">
        <ul>
            <li>运动经历:${coach.teachingCareer}</li>
            <li>常驻场馆:${coach.venueName}</li>
            <li>个人介绍:${coach.intro}</li>
            <li>咨询电话:${coach.telPhone}</li>
        </ul>
    </div>
    <div class="null"></div>
    <!--课程开始-->
    <div class="evaluate_wrap" >
        <!--判断 remarkList是否有值，为空时显示暂无评价-->
        <c:choose>
            <c:when test="${empty remarkList}">暂无评价</c:when>
            <c:otherwise>
                <c:forEach items="${remarkList}" var="item">
                    <span>${item}%</span>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <!--销量-->
    <div class="evaluate_wrap" >
        销量：${coach.data7}
    </div>
    <div class="course">
        <ul>
            <li class="course_k">课程</li>
            <c:forEach items="${courseList}" var="item">
                <li>
                    <p>${item.courseName}
                        <c:choose>
                            <c:when test="${item.courseType==1}">(月)</c:when>
                            <c:when test="${item.courseType==2}">(季)</c:when>
                            <c:when test="${item.courseType==3}">(年)</c:when>
                        </c:choose>
                        <br /><span>${item.introduct}</span></p>
                    <ul class="right_b">
                        <li class="pay">¥${item.salesPrice}</li>
                        <li class="button">	<a href="/coach/confirm_jl.htm?courseId=${item.id}">报名</a></li>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- 实景图展示开始	 -->
    <div class="pic_show">实景视图
        <c:forEach items="${fileData}" var="item">
            <img src="${item.url}" alt=""/>
        </c:forEach>

    </div>
</div>
</body>
</html>