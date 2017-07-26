<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta charset="UTF-8">
    <title>收藏</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/collect.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var page=1;
        var pagesize=100;
        var memberId=${memberId};
        var status_='${status_}';
        var apHtml=   "<div class='no'></div> <div class='all_sel'> <b class='allsel'></b> <span>全选</span> <i>删除</i> </div>";
        $(function(){
            reqFist();
            $("#sate").click(function(){
                $("#sate").parent("li").addClass("cur");
                $("#sate").parent("li").siblings().removeClass("cur");

                var result=reqData(0);
                var html="<ul class='show'>";
                $(result.resultData).each(function () {
                    var hId=this.id;
                    html+="<li id='"+hId+"'><a href='/venue/venueDetail.htm?id="+this.venueId+"' class='color_a'><b class='sel'></b>" +
                        "<div class='wrap'>" +
                        "<h3><img src='"+this.picRealPath+"'/></h3>" +
                        "<div class='font'>" +
                        "<p class='b'>"+this.venueName+"</p>" +
                        "<p>"+this.zoneName+"</p>" +
                        "<p class='color'>￥"+this.salesPrice+"</p>" +
                        "</div>" +
                        "</div></a>" +
                        "</li>";
                });
                html+="</ul><div class='no'></div> <div class='all_sel'> <b class='allsel'></b> <span>全选</span> <i>删除</i> </div>";
                $(".pic").html(html);
            });
            $("#trainer").click(function(){
                $("#trainer").parent("li").addClass("cur");
                $("#trainer").parent("li").siblings().removeClass("cur");
                $(".no,.sel,.all_sel").toggle();
                var result=reqData(1);
                //alert(result.resultMsg);
                var html="<ul class='show'>";
                $(result.resultData).each(function () {
                    var se='';
                    if(this.sex==1){
                        se="男";
                    }else {
                        se="女";
                    }
                    html+="<li id='"+this.id+"'><a href='/coach/coachDetail.htm?coachId="+this.coachId+"&status_=2'><b class='sel'></b>" +
                        "<div class='wrap'>" +
                        "<h3><img src='"+this.picRealPath+"'/></h3>" +
                        "<div class='font'>" +
                        "<p class='b'>"+this.name+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+se+"</p>" +
                        "<p>"+this.intro+"</p>" +
                        "<p class='color'><i>"+this.kindsName+"</i></p>" +
                        "</div>" +
                        "</div></a>" +
                        "</li>";
                });
                //alert(html);
                html+="</ul><div class='no'></div> <div class='all_sel'> <b class='allsel'></b> <span>全选</span> <i>删除</i> </div>";
                $(".pic").html(html);
            });
            $("#activity").click(function(){
                $("#activity").parent("li").addClass("cur");
                $("#activity").parent("li").siblings().removeClass("cur");
                $(".no,.sel,.all_sel").toggle();
                var result=reqData(2);
                var html="<ul class='show'>";
                $(result.resultData).each(function () {
                    var price="";
                    if(this.isFree==0){
                        price="￥"+this.activityPrice;
                    }
                    if(this.isFree==1){
                        price="免费";
                    }
                    if(this.isFree==2){
                        price="AA制";
                    }
                    html+="<li id='"+this.id+"'><b class='sel'><a href='/activity/activityDetail.htm?activityId="+this.activityId+"&status_=3'></b>" +
                        "<div class='wrap'>" +
                        "<h3><img src='"+this.activityRealPath+"'/></h3>" +
                        "<div class='font'>" +
                        "<p class='b'>"+this.activityName+"</p>" +
                        "<p>"+this.activityAddress+"</p>" +
                        "<p class='color'>"+price+"</p>" +
                        "</div>" +
                        "</div></a>" +
                        "</li>";
                });
                html+="</ul><div class='no'></div> <div class='all_sel'> <b class='allsel'></b> <span>全选</span> <i>删除</i> </div>";
                $(".pic").html(html);

            });
            /**************************************************************************************/
            /*编辑操作*/
            $(".head>span").click(function(){
                $(".pic .wrap").toggleClass("wrap_edit");
                $(".no,.sel,.all_sel").toggle();
                /*逐个删除*/
                $(".sel").on("click",function(){
                    var _this=$(this);
                    $(this).toggleClass("docol");
                    //alert($(this).parent("li").attr("id"));
                });
                /*全选删除*/
                $(".allsel").click(function(){
                    var $this=$(".allsel");//全选
                    var $sel=$(".sel");//单选
                    $this.toggleClass("docol");
                    if($this.hasClass("docol")){
                        $sel.addClass("docol");
                    }else{
                        $sel.removeClass("docol");
                    }
//                    if($sel.hasClass("docol")&&$this.hasClass("docol")){
//                        $sel.addClass("docol");
//                    }
//                    if($sel.hasClass("docol")){
//                        if(!$this.hasClass("docol")){
//                            $sel.removeClass("docol");
//                        }
//                    }
//                    if(!$sel.toggleClass("docol")){
//                        if($this.hasClass("docol")){
//                            $sel.addClass("docol");
//                        }
//                    }
//                    if(!$this.hasClass("docol")){
//                        $sel.removeClass("docol");
//                    }
//                    $(".sel").on("click",function(){
//                        var hasN=$(".pic ul li").children("b:not(.docol)").length;
//                        if(hasN>0){
//                            $this.removeClass("docol");
//                        }
//                    });

                });
                //删除操作
                $(".all_sel i").click(function(){
                    //console.log($(".pic .show .docol").parent("li").attr("id"));
                    var ids='';
                    $(".pic .show .docol").each(function(){
                        //console.log($(this).parent("li").attr("id"));
                        ids+=','+$(this).parent("li").attr("id");
                    });
                    //$(this).parent("li").attr("id");
                    ids=ids.substring(1);
                    $.ajax({
                        url:"/house/deleteHouseActive.htm",
                        type:"POST",
                        data:{"houseId":ids},
                        async:false,
                        dataType:"json",
                        success:function (data) {
                            var jsonReturn=eval(data);
                            if(jsonReturn.resultFlg==1){
                                $(".pic .show .docol").each(function(){
                                    $(this).parent("li").remove();
                                });
                            }else{
                                new Toast({message:jsonReturn.resultMsg}).show();
                            }
                        }
                    });

                });
            });
            if(status_==1){
                $('#sate').trigger("click");
            }else if(status_==2){
                $('#trainer').trigger("click");
            }
            else if(status_==3){
                $('#activity').trigger("click");
            }

            /**************************************************************************************/
        });
        /***************************************************************************************************************/
        function reqData(type) {
            var result=new Object();
            $.ajax({
                url:"/house/getHouseList.htm",
                type:"POST",
                data:{type:type,memberId:memberId,page:page,pageSize:pagesize},
                async:false,
                dataType:"json",
                success:function (data) {
                    var jsonReturn=eval(data);
                    result=jsonReturn;
                }
            });
            return result;
        }
        function reqFist() {
            $("#sate").parent("li").addClass("cur");
            $("#sate").parent("li").siblings().removeClass("cur");

            var result=reqData(0);
            var html="<ul class='show'>";
            $(result.resultData).each(function () {
                var hId=this.id;
                html+="<li id='"+hId+"'><a href='/venue/venueDetail.htm?id="+this.venueId+"' class='color_a'><b class='sel'></b>" +
                    "<div class='wrap'>" +
                    "<h3><img src='"+this.picRealPath+"'/></h3>" +
                    "<div class='font'>" +
                    "<p class='b'>"+this.venueName+"</p>" +
                    "<p>"+this.zoneName+"</p>" +
                    "<p class='color'>￥"+this.salesPrice+"</p>" +
                    "</div>" +
                    "</div></a>" +
                    "</li>";
            });
            html+="</ul><div class='no'></div> <div class='all_sel'> <b class='allsel'></b> <span>全选</span> <i>删除</i> </div>";
            $(".pic").html(html);
        }

    </script>
</head>
<body>
<div class="content">
    <div class="head">
        <h3><a href="/member/showMemberInfo.htm"><img src="<%=uiPath%>site/images/return.png" alt=""></a></h3>
        <ul>
            <li class="cur"><a href="javascript:;" id="sate" >收藏场馆</a></li>
            <li><a href="javascript:;" id="trainer">收藏教练</a></li>
            <li><a href="javascript:;" id="activity">收藏活动</a></li>
        </ul>
        <span>编辑</span>
    </div>
    <!--收藏场馆-->
    <div class="pic">
        <%--<ul class="show">--%>
        <%--</ul>--%>
        <%--<div class="no"></div>--%>
        <%--<div class="all_sel">--%>
            <%--<b class="allsel"></b>--%>
            <%--<span>全选</span>--%>
            <%--<i>删除</i>--%>
        <%--</div>--%>
    </div>
</div>
</body>
</html>