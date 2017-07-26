<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">--%>
    <meta name="viewport" content="initial-scale=1, user-scalable=0, minimal-ui" charset="UTF-8">
    <title>我有好创意</title>
    <link href="<%=uiPath%>site/css/common1.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>lib/dropload/dist/css/dropload.css" rel="stylesheet" type="text/css">
    <link href="<%=uiPath%>site/css/public-foot.css" rel="stylesheet" type="text/css">
    <script src="<%=uiPath%>site/js/jquery.min.js"></script>
    <script src="<%=uiPath%>lib/dropload/dist/js/dropload.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        //默认开启页面为0
        var page = 0;
        //每页的数目
        var pageSize = 10;

        var isSubmiting = false;
        var dropload = null;
        var reqUrl = "/sportsite/recommend/getRecommendList.htm";

        $(function () {

            $("#kind").val("1");
            $("#memberId").val(${memid});
            dropload = $('#inner').dropload({
                loadUpFn: function (me) {
                    reqData(reqUrl, "up");
                },
                loadDownFn: function (me) {

                    reqData(reqUrl, "down");
                }
            });
        });






        function h2y_save() {
            isSubmiting = true;
            var queryString ="kind="+$("#kind").val()+"&context="+$("#context").val()+"&remark="+""+"memberId"+${memid};

            $.post("/sportsite/recommend/addRecommend.htm", queryString, function (data) {
                var jsonReturn = eval("(" + data + ")");
                if (jsonReturn.resultFlag == "1") {
                    new Toast({message:jsonReturn.resultMsg}).show();
                    location.href="/sportsite/recommend/init.htm?kind=1"
                } else {
                    new Toast({message:jsonReturn.resultMsg}).show();
                }

                isSubmiting = false;
            });
        }

        /*光标聚焦*/
        function setfocus()
        {
            context.focus();
        }




        /**
         * 请求后台数据
         */
        function reqData(reqUrl, op) {

            if ("up" == op) {
                //页面增加
                page++;
            }

            //下拉刷新
            if ("down" == op) {
                page = 1;
            }

            $.ajax({
                type: 'POST',
                url: reqUrl,
                data: {kind:1,page: page, pageSize: pageSize},
                dataType: 'json',
                success: function (data) {

                    var len = 0;
                    var result = '';
                    var list = data.resultData;
                    if(list!=null){
                        len = list.length;
                    }

                    $(list).each(function () {
                        var nickname=this.nickName;
                        if(nickname==null||nickname=="")
                        {
                            nickname='匿名';}
                        result += ' <li><div class="nr1">'
                            +' <p>'+nickname+'</p>'
                            +'<i><b>'+this.createDate+'</b></i>'
                            +'</div>'
                            +'<div class="xx01">'+this.context+'</div></li>'
                    });

                    if ("up" == op) {
                        $('#inner ul').append(result);
                    } else {
                        $('#inner ul').html(result);

                    }

                    // 每次数据加载完，必须重置
                    dropload.resetload();

                    if (len < pageSize)
                        $(".dropload-down").hide();
                },
                error: function (xhr, type) {
                    new Toast({message:'Ajax error!'}).show();
                    // 即使加载出错，也得重置
                    dropload.resetload();
                }
            });
        }

    </script>
<body onload="setfocus()">
<div class="content">
    <div class="log_tit">
        <a href="javascript:void(0)" onclick="history.go(-1);" class="back"></a>
        <div class="log_tit02"><strong>我有好创意</strong></div>
    </div>
    <form name="editform" method="post" action="" id="editform">
        <div class="shuru">
            <div style="display:none">
                <input type="text" id="memberId"  name="memberId"  value="${memid}"/>
                <input type="text" id="kind"  name="kind" value=""/>
            </div>
            <div class="srk"><input type="text"  id="context" name="context" value="" placeholder="说点什么吧！"/></div>

            <div class="an"><a href="javascript:void(0)" onclick="h2y_save()">发表</a></div>
            <br style="clear: both;"/>
        </div>
    </form>
    <div id="inner">
        <ul>
            <div class="xx11"></div>
            <div id="lists" class="nr">
                <h6>最新的跟帖</h6>
            </div>
        </ul>
    </div>

</div>
</body>
</html>
