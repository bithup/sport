<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>我的场馆列表</title>
    <base href="<%=basePath%>">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/new_mysite.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script src="<%=uiPath%>site/js/newort.js"></script>
    <script>
        var page=1;
        var pageSize=80;
        function reqData(op) {
            if("up"==op){page++;}
            if("down"==op){page=1;}
            var url="/venue/getChildVenueList.htm";
            var venueId=${venueId};
            var result='';
            $.ajax({
                type:'POST',
                url:url,
                data:{venueId:venueId},
                dataType:'json',
                success:function (data) {
                    var jsonResult=eval(data);
                    if(jsonResult.resultFlg==1){
                        $(jsonResult.resultData).each(function () {

                            result+="<li>"+
                                "<a href='/venue/addVenue.htm?id=${memberId}&flag=Detail&childVanueId=${childVanueId.id}'>"+
                                "<h3><img src='"+this.pictureUrl+"' class='con_left'></h3>"+
                                "<span class='con_right'>" +
                                "<i>"+this.childVenueName+"</i>"+
                                "<i>"+this.kindsName+"</i>"+
                                "<i>"+this.salesPrice+"</i>"+
                                "</span>"+
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
                    url: "/venue/deleteChildVenue.htm?childVenueId="+id,
                    type: "POST",
                    data: {orderId: id},
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        var jsonResult = eval(data);
                        if(jsonResult.resultFlg=="1"){
                            alert("操作成功");
                            location.href="venue/otherSite.htm?id=${memberId}";
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
    我的场馆列表
    <i class="plus">添加</i>
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
        $(".plus").click(function () {
            location.href="/venue/addVenue.htm?flag=add&id=${memberId}";
        });
    });
</script>
</body>
</html>