<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>场次选择</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/film_xz.css">

    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/swiper.min.css">
    <script src="<%=uiPath%>site/js/jquery-1.10.1.min.js"></script>
    <script src="<%=uiPath%>site/js/swiper.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        var totalPrice=0;
        var id = ${id};
        var week='';
        var selectDate = '${selectDate}';
        var memberId='${memberId}';
        $(function () {
            getList();




        });
        //获取后台数据
        function  getList(){
            $.ajax({
                url:"/childVenueCutting/getVenueCuttings.htm?id="+id+"&selectDate="+selectDate,
                type:"POST",
                dataType:"json",
                success:function(data){
                    console.log(data);
                    var resultData = data;
                    var html = "";
                    var weekList = resultData.resultData.weekList;
                    /*遍历日期*/
                    $.each(weekList,function (i,item) {
                        var addclass = "";
                        if(item.rightDate==selectDate){
                            addclass = "<div class='swiper-slide swiper-slide-active' id='"+item.rightDate+"'>";
                        }else{
                            addclass = "<div class='swiper-slide' id='"+item.rightDate+"'>";
                        }
                        html += addclass+
                            "<span>"+ item.week+"</span>"+
                            "<p >"+item.date+"</p>"+
                            "</div>";

                    });
                    /*遍历时间表*/
                    var endTime=parseInt(resultData.resultData.venueList[0].endTime);
                    //console.log(endTime);
                    var startTime=parseInt(resultData.resultData.venueList[0].startTime);
                    //console.log(startTime);
                    var timeTable="";
                    for(var i=startTime;i<=endTime;i++){
                        timeTable+="<li>"+i+":00</li>";
                        //console.log(i);
                    }
                    //console.log(timeTable);
                    /*遍历场馆预定信息*/
                    var venueInfo="";
                    $.each(resultData.resultData.venueList,function (i,item) {
                        var cutt=item.cuttingList.split(',');
                        venueInfo+="<ul class='swiper-slide' name='"+item.venueNo+"' id='"+item.id+"'> <li id='0' class='site_num'>"+item.venueNo+"号场</li>";
                        for(var i=startTime;i<=endTime;i++){
                            if($.inArray(i+'',cutt)>-1){
                                venueInfo+="<li class='fulled' name='"+i+"' id='"+item.salesPrice+"'></li>";
                                $(".fulled").attr("disabled","disabled");
                            }else{
                                venueInfo+="<li name='"+i+"' id='"+item.salesPrice+"'></li>";
                            }

                        }
                        venueInfo+="</ul>";
                    });
                    //console.log(venueInfo);
                    $("#allData").html(html);
                    $("#timeTable").html(timeTable);
                    $("#venueInfo").html(venueInfo);
                    var mySwiper3 = new Swiper('#menu_swiper',{
                        freeMode : true,
                        slidesPerView : 'auto'
                    });

                    //选择日期点击操作
                    $("#allData div").on("click",function () {
                        //console.log("1111111111111111111111111111111111");
                        //console.log($(this).find("span").html());
                        week=$(this).find("span").html();

                        selectDate=this.id;
                        //console.log(selectDate);
                        totalPrice=0;
                        $("#price").html("价钱：¥"+totalPrice);
                        getList();
                        // location.href="/childVenueCutting/getVenueCutting.htm?id="+id+"&selectDate="+this.id;
                    });
                    //选择场次点击操作
                    $(".right_wrap ul li:not(.fulled)").on("click",function(){
                        //计算总价钱
                        if($(this).hasClass("seled")){
                            totalPrice=(parseFloat(totalPrice)-parseFloat(this.id)).toFixed(2);
                        }else {
                            totalPrice=(parseFloat(totalPrice)+parseFloat(this.id)).toFixed(2);
                        }
                        //改变选中状态
                        $(this).toggleClass("seled");
                        $("#price").html("价钱：¥"+totalPrice);

                    });

                    //提交订单操作
                    $(".button").on("click",function () {
                        if(totalPrice==0){
                            new Toast({message:'请选择场次!'}).show();
                            return;
                        }
                        var cuttings="";
                        var flag='';
                        var cut='';
                        $("#venueInfo ul li").each(function () {

                            if($(this).attr("name")==startTime){
                                //console.log("start------------------");
                                flag='';
                            }
                            if($(this).hasClass("seled")){
                                //console.log($(this).parent("ul").attr("id"));
                                flag+=$(this).attr("name")+',';
                                cut+=$(this).parent("ul").attr("name")+':'+$(this).attr("name")+';';
                            }
                            if($(this).attr("name")==endTime){
                                //console.log("end------------------------");
                                if(flag!=''){
                                    //console.log(flag);
                                    flag=flag.substring(0,flag.length-1);
                                    cuttings+=$(this).parent("ul").attr("id")+':'+flag+';';
                                }
                            }



                        });
                        cuttings=cuttings.substring(0,cuttings.length-1);
                        cut=cut.substring(0,cut.length-1);
                        //console.log(cut);
                        location.href="/venue/confirm_cg.htm?cuttings="+cuttings+"&kindsName=${kindsName}&address=${address}&selectDate="+selectDate+"&totalPrice="+totalPrice+"&cut="+cut+"&week="+week;
                        //console.log(cuttings);
//                        $.ajax({
//                            url:"/venue/confirm_cg.htm",
//                            type:"POST",
//                            data:{cuttings:2,selectDate:selectDate,contact:4,telephone:5},
//                            async:false,
//                            dataType:"json",
//                            success:function (data) {
//                                var jsonReturn=eval(data);
//                            }
//                        });
                    });

                }
            });
        }
    </script>

</head>
<body>
<div class="content">
    <div class="nav">
        <span>场次选择</span>
        <div class="left"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
    </div>
    <%--日期滑动框--%>
    <div id="header" class="swiper-container-horizontal swiper-container-free-mode">
        <%--显示星期和日期--%>
        <div class="swiper-wrapper" id="allData">

        </div>
    </div>


    <div class="box">
        <div class="part2">
            <div class="item" tabindex="5001" style="overflow-y: hidden; outline: none;">
                <div class="con_left_time">时间</div>
                <ul id="timeTable">

                </ul>
            </div>
        </div>

        <div class="right" id="menu_swiper">
            <div class="swiper-wrapper right_wrap" id="venueInfo">


            </div>
        </div>
    </div>
    <div class="xz">
        <ul>
            <li>
                <h3></h3>
                <p>已满</p>

            </li>
            <li>
                <h3 class="lv"></h3>
                <p>可预订</p>
            </li>
            <li>
                <h3 class="blue"></h3>
                <p>已预订</p>

            </li>
        </ul>
    </div>
    <div class="price">
        <p id="price">价钱：¥0</p>

    </div>
    <div class="button">
        <p>提交订单</p>
    </div>

</div>


</body>
</html>