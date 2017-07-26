<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta charset="UTF-8">
    <title>买家我的订单</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/my_order.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/dropload.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script src="<%=uiPath%>site/js/dropload.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript">
        var role='${role}';
        var memberId=${id};
        var page=1;
        var pagesize=80;
        var orderStatu=null;
        var url="/order/getListAs"+role+".htm";
        //异步请求数据
        function reqData(op) {
            if("up"==op){page++;}
            if("down"==op){page=1;}
            var result='';
            $.ajax({
                type:'POST',
                url:url,
                data:{memberId:memberId,page:page,pagesize:pagesize,orderStatus:orderStatu},
                dataType:'json',
                success:function (data) {
                    var jsonResult=eval(data);
                    //alert(jsonResult.resultMsg);
                  if(jsonResult.resultFlg==1){
                      $(jsonResult.resultData).each(function () {
                          //0:待支付、1:已支付待开始、2:待评价、3:已完成、4：交易取消
                          var ordType='';
                          var ordAmount='';

                          if(this.orderType==0){
                              ordType='场馆';
                              ordAmount="¥"+this.orderAmount;
                          }
                          if(this.orderType==1){
                              ordType='课程';
                              ordAmount="¥"+this.orderAmount;
                          }
                          if(this.orderType==2){
                              ordType='活动';
                              if(this.isFree==0){
                                  ordAmount="¥"+this.orderAmount;
                              }
                              if(this.isFree==1){
                                  ordAmount="免费";
                              }
                              if(this.isFree==2){
                                  ordAmount="AA制";
                              }
                          }
                            if(this.orderStatus==0){
                                result+="<li class='pic_a'><a href='/order/orderDetail.htm?orderId="+this.id+"'>" +
                                    "<p class='mc'>"+ordType+"（"+this.kindName+"）<span>未付款</span></p>" +
                                    "<h3><img src='"+this.headRealPath+"' ></h3>" +
                                    "<div class='font'>" +
                                    "<ul>" +
                                    "<li class='color'>"+this.goodsName+"</li>" +
                                    "<li>时间："+this.createDate+"</li>" +
                                    "<li>"+ordAmount+"</li></a>" +
                                    "<li class='fu'>" +
                                    "<p class='cancel' onclick='cancel("+this.id+");'>撤销订单</p>" +
                                    "<p class='pay_m'><a href='/pay/payWay.htm?orderAmount="+this.orderAmount+"&orderId="+this.id+"'>付款</a></p>" +
                                    "</li>" +
                                    "</ul>" +
                                    "</div>" +
                                    "</li><div class='no'></div>";
                            }
                            if(this.orderStatus==1){
                                    result+="<li class='pic_a'><a href='/order/orderDetail.htm?orderId="+this.id+"'>" +
                                        "<p class='mc'>"+ordType+"（"+this.kindName+"）<span>待开始</span></p>" +
                                        "<h3><img src='"+this.headRealPath+"' ></h3>" +
                                        "<div class='font'>" +
                                        "<ul>" +
                                        "<li class='color'>"+this.goodsName+"</li>" +
                                        "<li>时间："+this.createDate+"</li>" +
                                        "<li>"+ordAmount+"</li></a>" +
                                        "<li class='fu'>" +
                                        "<p class='refund' onclick='refund("+this.id+");'>退款</p>" +
                                        "</li>" +
                                        "</ul>" +
                                        "</div>" +
                                        "<p class='mc' style='padding-top: 0px;'>消费凭证码："+this.code+"</p>"+
                                        "</li><div class='no'></div>";
                            }
                            if(this.orderStatus==2){
                                result+="<li class='pic_a'><a href='/order/orderDetail.htm?orderId="+this.id+"'>" +
                                    "<p class='mc'>"+ordType+"（"+this.kindName+"）<span>待评价</span></p>" +
                                    "<h3><img src='"+this.headRealPath+"' ></h3>" +
                                    "<div class='font'>" +
                                    "<ul>" +
                                    "<li class='color'>"+this.goodsName+"</li>" +
                                    "<li>时间："+this.createDate+"</li>" +
                                    "<li>"+ordAmount+"</li><a>" +
                                    "<li class='fu'>" +
                                    "<p class='del' onclick='evaluation("+this.id+","+this.goodsId+")'>去评价</p>" +
                                    "</li>" +
                                    "</ul>" +
                                    "</div>" +
                                    "</li><div class='no'></div>";
                            }
                            if(this.orderStatus==3){
                                result+="<li class='pic_a'><a href='/order/orderDetail.htm?orderId="+this.id+"'>" +
                                    "<p class='mc'>"+ordType+"（"+this.kindName+"）<span>已完成</span></p>" +
                                    "<h3><img src='"+this.headRealPath+"' ></h3>" +
                                    "<div class='font'>" +
                                    "<ul>" +
                                    "<li class='color'>"+this.goodsName+"</li>" +
                                    "<li>时间："+this.createDate+"</li>" +
                                    "<li>"+ordAmount+"</li></a>" +
                                    "<li class='fu'>" +
                                    "<p class='del' onclick='cancel("+this.id+")'>删除订单</p>" +
                                    "</li>" +
                                    "</ul>" +
                                    "</div>" +
                                    "</li><div class='no'></div>";
                            }
                            if(this.orderStatus==4){
                                result+="<li class='pic_a'><a href='/order/orderDetail.htm?orderId="+this.id+"'>" +
                                    "<p class='mc'>"+ordType+"（"+this.kindName+"）<span>交易取消</span></p>" +
                                    "<h3><img src='"+this.headRealPath+"' ></h3>" +
                                    "<div class='font'>" +
                                    "<ul>" +
                                    "<li class='color'>"+this.goodsName+"</li>" +
                                    "<li>时间："+this.createDate+"</li>" +
                                    "<li>"+ordAmount+"</li></a>" +
                                    "<li class='fu'>" +
                                    "<p class='del'  onclick='cancel("+this.id+")'>删除订单</p>" +
                                    "</li>" +
                                    "</ul>" +
                                    "</div>" +
                                    "</li><div class='no'></div>";
                            }
                          $('.show').html(result);
                      });

                  }else{
                      $('.show').html("<div style='text-align: center;margin-top: 10px;'>暂无数据</div>");
                  }

                }
            });

        }
        //查询指定订单状态
        $(function () {
            $("#all").click(function(){
                $("#all").parent("li").addClass("cur");
                $("#all").parent("li").siblings().removeClass("cur");
                orderStatu=null;
                page=1;
                reqData("down");
            });
            $("#start").click(function(){
                $("#start").parent("li").addClass("cur");
                $("#start").parent("li").siblings().removeClass("cur");
                orderStatu=0;
                page=1;
                reqData("down");
            });
            $("#pay").click(function(){
                $("#pay").parent("li").addClass("cur");
                $("#pay").parent("li").siblings().removeClass("cur");
                orderStatu=1;
                page=1;
                reqData("down");
            });
            $("#evaluate").click(function(){
                $("#evaluate").parent("li").addClass("cur");
                $("#evaluate").parent("li").siblings().removeClass("cur");
                orderStatu=2;
                page=1;
                reqData("down");
            });

        });
        //第一次进入页面
        $(function(){

            reqData("down");


/*            滑动屏幕加载数据
             定义dropload对象
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
            });*/
        });
        //确认订单
        /*function confir(id) {
            $.ajax({
                url:"/order/confirmOrder.htm",
                type:"POST",
                data:{orderId:id},
                async:false,
                dataType:"json",
                success:function (data) {
                    if(data.resultFlg==1){
                        refresh();
                    } else {
                        alert("操作失败！");
                    }
                },
                error:function () {
                    alert("ajax error");
                }
            });

        }*/
        //评价
        function evaluation(orderId,goodsId) {
            location.href="/order/evaluate.htm?orderId="+orderId+"&goodsId="+goodsId;
        }

        //删除订单、撤销订单
        function cancel(id) {
            if(confirm("确认删除，撤销吗？")){
                $.ajax({
                    url: "/order/deleteOrder.htm",
                    type: "POST",
                    data: {orderId: id},
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        if (data.resultFlg == 1) {
                            refresh();
                        } else {
                            alert("操作失败！");
                        }
                    },
                    error: function () {
                        alert("ajax error");
                    }
                });
            }

        }
        //申请退款
        function refund(id) {
            if(confirm("确认退款吗？")){
                location.href="/member/refund.htm?orderId="+id;
            }
        }
        //订单操作成功刷新页面，记录状态
        function refresh() {
            if(orderStatu==null){$("#all").trigger("click");}
            if(orderStatu==0){$("#start").trigger("click");}
            if(orderStatu==1){$("#pay").trigger("click");}
            if(orderStatu==2){$("#evaluate").trigger("click");}
        }

    </script>


</head>
<body>
<div class="content">
    <div class="head"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a>我的订单</div>
    <!--导航开始-->
    <div class="list">
        <ul>
            <li class="cur"><a href="javascript:;" id="all">全部</a></li>
            <li><a href="javascript:;" id="start">待付款</a></li>
            <li><a href="javascript:;" id="pay">待开始</a></li>
            <li><a href="javascript:;" id="evaluate"> 待评价</a></li>
        </ul>
    </div>
    <!--详情开始-->
    <div class="pic">
        <ul class="show">

        </ul>
    </div>

</div>
</body>
</html>