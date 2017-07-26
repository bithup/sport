<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<head>
    <meta charset="UTF-8">
    <title>${name}</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%=uiPath%>site/css/index_yu.css">
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/jquery.easing.min.js"></script>
    <script src="<%=uiPath%>site/js/ort.js"></script>
    <script type="text/javascript">
        $(function(){
            // $("#sate").click(function(){
            //     $("#sate").parent("li").addClass("cur");
            //     $("#sate").parent("li").siblings().removeClass("cur");
            //     $(".pic").eq(0).show();
            //     $(".pic").eq(1).hide();
            //     $(".pic").eq(2).hide();
            // });
            // $("#trainer").click(function(){
            //     $("#trainer").parent("li").addClass("cur");
            //     $("#trainer").parent("li").siblings().removeClass("cur");
            //     $(".pic").eq(1).show();
            //     $(".pic").eq(0).hide();
            //     $(".pic").eq(2).hide();
            // });
            // $("#activity").click(function(){
            //     $("#activity").parent("li").addClass("cur");
            //     $("#activity").parent("li").siblings().removeClass("cur");
            //     $(".pic").eq(2).show();
            //     $(".pic").eq(1).hide();
            //     $(".pic").eq(0).hide();
            // });


            /*2017.0314修改tab切换*/

            $(".list>ul>li").click(function(){
                var index=$(this).index();
                $(".list>ul>li").eq(index).addClass("cur").siblings().removeClass("cur");
                $(".detail_wrap>div").eq(index).show().siblings().hide();
            })

            var num;
            $('.nav_main>li[id]').hover(function(){
                /*下拉框出现*/
                var Obj = $(this).attr('id');

                num = Obj.substring(3, Obj.length);
                $('#box'+num).slideDown(300);
                $(this).addClass("cur").siblings().removeClass("cur");
            },function(){
                /*下拉框消失*/
                $('#box'+num).hide();
            });
            $('.hidden_box').hover(function(){
                $(this).show();
            },function(){
                $(this).slideUp(200);
            });
        });

    </script>



</head>
<body>
<div class="content">
    <div class="head">
        <div class="fh"><a href="javascript:history.go(-1)"><img src="<%=uiPath%>site/images/return.png" alt="" /></a></div>
        ${name}
    </div>
    <div class="list">
        <ul class="nav_main">
            <li class="cur" id="li-1">场馆<img src="<%=uiPath%>site/images/down.png" alt=""></li>
            <li id="li-2">教练<img src="<%=uiPath%>site/images/down.png" alt=""></li>
            <li id="li-3">活动<img src="<%=uiPath%>site/images/down.png" alt=""></li>
        </ul>
        <!-- 场馆菜单 -->
        <div class="changguan_memu hidden_box" id="box1">
            <ul>
                <li><a href="./index_fw.html">运动项目</a></li>
                <li><a href="./index_yuf.html">区域范围</a></li>
                <li><a href="./index_fp.html">排序方式</a></li>
            </ul>
        </div>
        <!-- 教练菜单 -->
        <div class="jiaolian_memu hidden_box" id="box2">
            <ul>
                <li><a href="./trainer_nl.html">教练年龄</a></li>
                <li><a href="./trainer_xb.html">教练性别</a></li>
                <li><a href="./trainer_zz.html">教练资质</a></li>
            </ul>
        </div>
        <!-- 活动菜单 -->
        <div class="huodong_memu hidden_box" id="box3">
            <ul>
                <li><a href="./index_fw.html">运动项目</a></li>
                <li><a href="./index_yuf.html">区域范围</a></li>
                <li><a href="./activity_px.html">收费标准</a></li>
            </ul>
        </div>
    </div>

    <div class="detail_wrap">
        <!--详情开始-->
        <div class="pic">
            <ul>
                <li>
                    <h3><a href="./index_xq.html"><img src="<%=uiPath%>site/images/images/site_1.png" alt="" /></a></h3>
                    <div class="font">
                        <p class="b">鸿喜庄园羽毛球馆>100km</p>
                        <p>[近郊其他]</p>
                        <p class="color">¥25<a href="./index_xq.html">预约</a></p>
                    </div>
                </li>
                <li>
                    <h3><a href="./index_xq.html"><img src="<%=uiPath%>site/images/images/site_1.png" alt="" /></a></h3>
                    <div class="font">
                        <p class="b">鸿喜庄园羽毛球馆>100km</p>
                        <p>[近郊其他]</p>
                        <p class="color">¥25<a href="./index_xq.html">预约</a></p>
                    </div>
                </li>
                <li>
                    <h3><a href="./index_xq.html"><img src="<%=uiPath%>site/images/images/site_1.png" alt="" /></a></h3>
                    <div class="font">
                        <p class="b">鸿喜庄园羽毛球馆>100km</p>
                        <p>[近郊其他]</p>
                        <p class="color">¥25<a href="./index_xq.html">预约</a>
                    </div>
                </li>
            </ul>
        </div>
        <!--收藏教练-->
        <div class="pic" style="display: none">
            <ul>
                <li>
                    <h3><img src="<%=uiPath%>site/images/images/order_b.png" alt="" /></h3>
                    <div class="font">
                        <p class="b">陈姚安</p>
                        <p>羽毛球专业，具有丰富的教学经验</p>
                        <p class="color">¥25<a href="">预约</a></p>
                    </div>
                </li>
                <li>
                    <h3><img src="<%=uiPath%>site/images/images/order_b.png" alt="" /></h3>
                    <div class="font">
                        <p class="b">陈姚安</p>
                        <p>羽毛球专业，具有丰富的教学经验</p>
                        <p class="color">¥25<a href="">预约</a></p>
                    </div>
                </li>
                <li>
                    <h3><img src="<%=uiPath%>site/images/images/order_b.png" alt="" /></h3>
                    <div class="font">
                        <p class="b">陈姚安</p>
                        <p>羽毛球专业，具有丰富的教学经验</p>
                        <p class="color">¥25<a href="">预约</a>
                    </div>
                </li>
            </ul>
        </div>
        <!--收藏活动-->
        <div class="pic" style="display: none">
            <ul>
                <li>
                    <h3><img src="<%=uiPath%>site/images/images/order_a.png" alt="" /></h3>
                    <div class="font">
                        <p class="b">北京鸟巢体育馆组队馆</p>
                        <p>[北京鸟巢体育馆]</p>
                        <p class="color">¥25<a href="">预约</a></p>
                    </div>
                </li>
                <li>
                    <h3><img src="<%=uiPath%>site/images/images/order_a.png" alt="" /></h3>
                    <div class="font">
                        <p class="b">北京鸟巢体育馆组队馆</p>
                        <p>[北京鸟巢体育馆]</p>
                        <p class="color">¥25<a href="">预约</a></p>
                    </div>
                </li>
                <li>
                    <h3><img src="<%=uiPath%>site/images/images/order_a.png" alt="" /></h3>
                    <div class="font">
                        <p class="b">北京鸟巢体育馆组队馆</p>
                        <p>[北京鸟巢体育馆]</p>
                        <p class="color">¥25<a href="">预约</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>

</div>
</body>
</html>