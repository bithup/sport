<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>数据校验</title>
    <base href="<%=basePath%>"/>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
    <script src="<%=uiPath%>site/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="<%=uiPath%>site/js/toast.js"></script>
    <script type="text/javascript">
        $(function () {
            if(localStorage.getItem("cityName")==null||localStorage.getItem("cityName")==''){
            var myCity = new BMap.LocalCity();
            myCity.get(getCityByIP);
            new Toast({message: '正在获取城市信息'}).show();
            setTimeout("index()",1000);
            }else {
                index();
            }

        });
        function getCityByIP(rs) {
            var cityName = rs.name;
            localStorage.setItem("cityName",cityName);
        }
        function index() {
            location.href = "/home/index.htm?zoneName=郑州市&type_=${type_}" ;
        }

    </script>

</head>
<body>
<div></div>

</body>
</html>