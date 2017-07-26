<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>搜索</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/search.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <script src="<%=uiPath%>site/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
</head>
<body>
<div class="content">
    <div class="head">
        <i onclick="history.go(-1)"></i>
        <div class="left"><input type="text" placeholder="输入场馆的名称" /></div>
        <div class="right"><a >搜索</a></div>
    </div>
    <div class="pic">
        <ul class="show">

        </ul>
    </div>

</div>
<script type="text/javascript">
    var page=1;
    var pagesize=80;
    $(function(){
        $(".right").click(function () {
            var indexCondition=$(".left>input").val();
            if(indexCondition==''||indexCondition==null){new Toast({message:'搜索内容不能为空!'}).show();return}
            var jsonReturn=reqData(1,80,indexCondition);

            if(jsonReturn.resultFlg==0){
                $(".show").html("暂无数据");
                return;
            }
            if(jsonReturn.resultFlg==1){
                var resultStr=getResultStr(jsonReturn);
                $(".show").html(resultStr);
            }
        });
    });
    //获取后台数据
    function reqData(page,pagesize,indexCondition) {
        var jsonReturn=new Object();
        $.ajax({
            url:"/venue/getListVenueSearch.htm",
            type:"POST",
            async:false,
            data:{page:page,pagesize:pagesize,venueName:indexCondition},
            dataType:"json",
            success:function (data) {
                var result=eval(data);
                jsonReturn=result;
            }
        });
        return jsonReturn;
    }
    //将返回数据拼接成字符串
    // IChildVenueDao.xml type 1:场馆、2:教练、3:活动
    //isRefund 0:不能退款、1:可以退款
    function getResultStr(jsonReturn){
        var resultStr='';
        $(jsonReturn.resultData).each(function () {


            if(this.isRefund==0){
                resultStr+="<li><a href='/venue/venueDetail.htm?id="+this.id+"'>" +
                    "<h3><img src='"+this.picRealPath+"'/></h3>" +
                    "<p>" +
                    "<b><i>"+this.venueName+"</i></b><br/>" +
                    "<span>"+this.zoneName+"</span>" +
                    "<i>￥"+this.salesPrice+"</i>" +
                    "</p>" +
                    "</a></li>";
            }else {
                resultStr+="<li><a href='/venue/venueDetail.htm?id="+this.id+"'>" +
                    "<h3><img src='"+this.picRealPath+"'/></h3>" +
                    "<p>" +
                    "<b><i class='rejected'>退</i><i>"+this.venueName+"</i></b><br/>" +
                    "<span>"+this.zoneName+"</span>" +
                    "<i>￥"+this.salesPrice+"</i>" +
                    "</p>" +
                    "</a></li>";
            }

        });
        return resultStr;
    }
    //加载html标签

</script>
</body>
</html>