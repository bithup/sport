<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>场馆搜索</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/other_yu.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script src="<%=uiPath%>site/js/dropload.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript">
        var page=1;
        var pagesize=8;
        var indexCondition=${indexCondition};

        $(function(){

            reqData("down");

            //滑动屏幕加载数据
            // 定义dropload对象
            $('.pic').dropload({
                scrollArea : window,
                domUp : {
                    domClass   : 'dropload-up',
                    domRefresh : '<div class="dropload-refresh">刷新</div>',
                    domUpdate  : '<div class="dropload-update">更新</div>',
                    domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
                },
                domDown : {
                    domClass   : 'dropload-down',
                    domRefresh : '<div class="dropload-refresh">加载</div>',
                    domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
                    domNoData  : '<div class="dropload-noData">暂无数据</div>'
                },
                //下拉刷新
                loadUpFn : function(me){

                    setTimeout(function(){
                        reqData("down");
                        // 每次数据加载完，必须重置
                        me.resetload();
                        // 重置页数，重新获取loadDownFn的数据
                        page=1;
                        // 解锁loadDownFn里锁定的情况
                        me.unlock();
                        me.noData(false);
                    },10);
                },
                //上拉加载
                loadDownFn : function(me){

                    setTimeout(function(){
                        reqData("up");
                        // 每次数据加载完，必须重置
                        me.resetload();
                        // 重置页数，重新获取loadDownFn的数据
                        //page = 0;
                        // 解锁loadDownFn里锁定的情况
                        me.unlock();
                        me.noData(false);
                    },10);
                },
                threshold : 50
            });

        });

        //异步请求数据
        function reqData(op) {
            if("up"==op){page++;}
            if("down"==op){page=1;}
            var result='';
            $.ajax({
                type:'POST',
                url:"/index/getIndexSearch.htm",
                data:{page:page,pagesize:pagesize,indexCondition:indexCondition},
                dataType:'json',
                success:function (data) {
                    var jsonResult=eval(data);

                    if(jsonResult.resultFlg==1){
                        $(jsonResult.resultData).each(function () {

                            result+="<li class='pic_a'><p class='mc'>订单编号：<b>"+"thisordernumber"+"</b><span>" +
                                "订单状态" +
                                "</span></p>"+
                                "<div class='pic_wrap'><h3><a href=''><img src='“”“"+this.headRealPath+"'></a></h3>"+
                                "<div class='font'><ul><li class='color'>"+this.orderName+"</li>"+
                                "<li>时间：<b>"+"thistime"+"</b></li>"+
                                "<li>账号：<b>"+"thisaccount"+"</b></li>"+
                                "<li>电话：<b>"+this.telephone+"</b></li>"+
                                "</ul></div><p class='pic_mon'>总金额：<b>￥"+this.orderAmount+"</b></p></li><div class='no'></div>";
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

    </script>
</head>
<body>
<div class="content">
    <div class="head">
        <a href="javascript:history.go(-1)" class="return"><img src="<%=uiPath%>site/images/return.png" alt="" /></a>场馆</div>
    <div class="pic">
        <ul class="show">
            <li>
                <h3><img src="<%=uiPath%>site/images/images/site_1.png" alt="" /></h3>
                <div class="font">
                    <p class="b">鸿喜庄园羽毛球馆>100km</p>
                    <p>[近郊其他]</p>
                    <p class="color">¥25<a href="">预约</a></p>
                </div>
            </li>
        </ul>
    </div>

</div>
</body>
</html>