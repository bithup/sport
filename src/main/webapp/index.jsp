<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>接口操作测试</title>
</head>
<body>
<div id="register">1、注册</div>
<form action="/sportcmbs/member/register.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/register.htm
   //入参
        账号:account （不可为空）
        密码:password（不可为空）
        验证码：validationCode（不可为空）

  //出参
    </textarea>
  <input type="" name="account" value="15803882486">
  <input type="" name="password" value="123456">
  <input type="" name="validationCode" value="123456">
  <input type="" value="调用接口"/>
</form>

<div id="login">2、登录</div>
<form action="/sportcmbs/member/login.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/login.htm

   //入参

   账号:account（不可为空）
   密码:password（不可为空）
  //出参

    </textarea>
  <input type="hidden" name="account" value="15836579053">
  <input type="hidden" name="password" value="123456">
  <input type="submit" value="调用接口"/>
</form>


<div id="memberInfo">3、显示用户资料</div>
<form action="/sportcmbs/member/showMemberInfo.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/showMemberInfo.htm

   //入参

   用户id:id（不可为空）

  //出参

    </textarea>
  <input type="hidden" name="id" value="9">
  <input type="submit" value="调用接口"/>
</form>

<div id="updateMemberInfo">4、完善用户资料</div>
<form action="/sportcmbs/member/updateMemberInfo.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/updateMemberInfo.htm

   //入参
        头像：memberFile
        用户id:id（不可为空）
        昵称：nickName
        生日：birthday
        性别：sex
        体重：weight
        身高：height
        运动爱好：hobby
        签名：signature

  //出参
    </textarea>
  <input type="hidden" name="id" value="9">
  <input type="hidden" name="nickName" value="倚栏听风">
  <input type="hidden" name="birthday" value="1998-01-01">
  <input type="hidden" name="sex" value="1">
  <input type="hidden" name="heigth" value="170">
  <input type="hidden" name="weigth" value="50">
  <input type="hidden" name="hobby" value="瑜伽">
  <input type="hidden" name="signature" value="我怀念的是无话不说"></br>
  <input type="file" name="memberFile"/>
  <input type="submit" value="调用接口"/>
</form>

<div id="forgetPassword">5、忘记密码</div>
<form action="/sportcmbs/member/forgetPassword.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/forgetPassword.htm

   //入参

        用户名:account（不可为空）
        密码：password（不可为空）
        验证码：validationCode（不可为空）

  //出参

    </textarea>
  <input type="hidden" name="account" value="15803882486">
  <input type="hidden" name="password" value="123456">
  <input type="hidden" name="validationCode" value="526346">
  <input type="submit" value="调用接口"/>
</form>

<div id="updatePassword">6、修改密码</div>
<form action="/sportcmbs/member/updatePassword.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/updatePassword.htm

   //入参

        用户id:id（不可为空）
        密码：password（不可为空）
        原密码：oldPass(不可为空)

  //出参

    </textarea>
  <input type="hidden" name="id" value="7">
  <input type="hidden" name="password" value="123456">
  <input type="hidden" name="oldPass" value="123456">
  <input type="submit" value="调用接口"/>
</form>


<div id="getSubject">7、轮播图</div>
<form action="/sportcmbs/subject/getSubject.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/subject/getSubject.htm

   //入参

  //出参

          轮播图id:id,
          根路径:path,
          相对路径:relative_path,
          主题名称:subject_name,
          类型为3的时候存储url地址:subject_url,
          创建时间:create_date,
          创建时间:update_date,
          轮播图打开地址:suject_url

    </textarea>
  <input type="submit" value="调用接口"/>
</form>

<div id="getListVenue">8、约场馆</div>
<form action="/sportcmbs/childVenue/getListVenue.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenue/getListVenue.htm

   //入参
   运动类型id:kindsId(查询全部时为空)
        page
        pagesize

  //出参

        场馆id:id,
        图片路径：pictureUrl,
        图片真实路径：picRealPath,
        原价格：price,
        销售价：salesPrice,
        运动类型id:sportId,
        场馆名称： venueName,
        区域名称：zoneName,
        是否可退款：isRefund（0：不能  1：可以）

    </textarea>
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="hidden" name="kindsId" value="1001109">
  <input type="submit" value="调用接口"/>
</form>

<div id="getListVenueSearch">9、约场馆搜索</div>
<form action="/sportcmbs/childVenue/getListVenueSearch.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenue/getListVenueSearch.htm

   //入参
   场馆名称:venueName
        page
        pagesize

  //出参
        场馆id:id,
        图片路径：pictureUrl,
        图片真实路径：picRealPath,
        原价格：price,
        销售价：salesPrice,
        运动类型id:sportId,
        场馆名称： venueName,
        区域名称：zoneName,
        是否可退款：isRefund（0：不能  1：可以）

    </textarea>
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="hidden" name="venueName" value="象过河">
  <input type="submit" value="调用接口"/>
</form>

<div id="venueDetail">10、场馆详情</div>
<form action="/sportcmbs/childVenue/venueDetail.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenue/venueDetail.htm

   //入参
        场馆id:id（不可为空）

  //出参

        场馆id:id,
			场馆名称：venueName,
			运动类型id：kindsId,
			父场馆id：parentId,
			头像路径：pictureUrl,
			头像绝对路径：picRealPath,
			价格：price,
			销售价格：salesPrice,
			提示：tips,
			是否可退款：isRefund,
			提前申请退款时间： refundDeadline,
			营业时间： businessTime,
			公交信息： busInfo,
			地铁信息： subwayInfo,
			场馆设施：hardware,
			服务信息：serviceInfo,
			地址：address,
			联系电话：telephone,
			运动类型名称： kindsName

    </textarea>
  <input type="hidden" name="id" value="8">
  <input type="submit" value="调用接口"/>
</form>


<div id="getVenueCutting">11、场次列表</div>
<form action="/sportcmbs/childVenueCutting/getVenueCutting.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenueCutting/getVenueCutting.htm

   //入参
        场馆id:id（不可为空）
        选择的日期：selectDate（格式：yyyy-MM-dd）（不可为空）

  //出参

    </textarea>
  <input type="hidden" name="id" value="7">
  <input type="hidden" name="selectDate" value="2016-12-10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getVenueAmount">12、选场次算价格</div>
<form action="/sportcmbs/childVenueCutting/getVenueAmount.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenueCutting/getVenueAmount.htm

   //入参
        选择的场次:cuttings（格式：1号场馆id：14，15,16,17,18；2号场馆id:9,10,11）（不可为空）


  //出参
        总价：amount

    </textarea>
  <input type="hidden" name="cuttings" value="6:14，15,16,17,18;7:9,10,11">
  <input type="submit" value="调用接口"/>
</form>


<div id="createVenueOrder">13、生成场馆订单</div>
<form action="/sportcmbs/order/createVenueOrder.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/createVenueOrder.htm

   //入参
        用户id:memberId（不可为空）
        选择的场次:cuttings（格式：1号场馆id：14，15,16,17,18；2号场馆id:9,10,11）（不可为空）
        选择的日期：selectDate（格式：yyyy-MM-dd）（不可为空）
        联系人：contact（不可为空）
        联系电话：telephone（不可为空）


  //出参

    </textarea>
  <input type="hidden" name="cuttings" value="2:14，15,16,17,18">
  <input type="hidden" name="memberId" value="6">
  <input type="hidden" name="selectDate" value="2016-12-12">
  <input type="hidden" name="contact" value="白绍星">
  <input type="hidden" name="telephone" value="15803882486">
  <input type="submit" value="调用接口"/>
</form>

<div id="getRecommendVenue">14、首页热门场馆</div>
<form action="/sportcmbs/childVenue/getRecommendVenue.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenue/getRecommendVenue.htm

   //入参
        区域名称:zoneName(默认郑州市)

  //出参
        场馆id:id,
			头像路径：pictureUrl,
			头像绝对路径： picRealPath,
			单价：price,
			销售价：salesPrice,
			运动类型id: sportId,
			场馆名称： venueName,
			区域名称：`name`,
			是否可退款：isRefund（0：不能  1：可以）

    </textarea>
  <input type="hidden" name="zoneName" value="郑州市">
  <input type="submit" value="调用接口"/>
</form>


<div id="getRecommendCoach">15、首页热门教练</div>
<form action="/sportcmbs/coach/getRecommendCoach.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/coach/getRecommendCoach.htm

   //入参
        区域名称:zoneName(默认郑州市)

  //出参
        教练id：id,
        教练姓名：coachName,
        性别：sex,
        生日：birthday,
        年龄：age,
        电话：telPhone,
        简介：intro,
        营业时间：businessTime,
        头像路径：headPath,
        头像绝对：headRealPath,
        区域名称：zoneName,
        运动类型名称：kindsName

    </textarea>
  <input type="hidden" name="zoneName" value="郑州市">
  <input type="submit" value="调用接口"/>
</form>


<div id="getIndexSearch">16、首页搜索</div>
<form action="/sportcmbs/index/getIndexSearch.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/index/getIndexSearch.htm

   //入参
        查询条件：indexCondition
        page
        pagesize

  //出参

    </textarea>
  <input type="hidden" name="indexCondition" value="台球">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getIndexKinds">17、首页运动类型</div>
<form action="/sportcmbs/kinds/getIndexKinds.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/kinds/getIndexKinds.htm

   //入参

  //出参
        运动id： id;
        父Id： parentId;
        行业id： instId;
        行业编号：instCode;
        操作人员的Id：userId;
        种类编码： code;
        父编码： parentCode;
        前置code，从后向前递推处理：preCode;
        分类名称： name;
        拼音码： spellName;
        拼音首字母： spellFirstName;
        等级： level;
        运动项图片路径： sportUrl;
        运动项图片绝对路径：sportRealUrl;
        创建时间： createDate;
        更新时间： updateDate;
        排序： ord;
        0废弃，1使用：status;
        备注： remark;
        区域编码： zoneCode;


    </textarea>

  <input type="submit" value="调用接口"/>
</form>


<div id="getIndexOtherKinds">18、首页运动类型其他</div>
<form action="/sportcmbs/kinds/getIndexOtherKinds.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/kinds/getIndexOtherKinds.htm

   //入参


  //出参
            类型id：id,
			父id:parent_id,
			前置code:pre_code,
			运动类型名称：`name`,
			等级：`level`,
			运动项图片路径：sport_url,
			运动项图片绝对路径：sport_real_url

    </textarea>

  <input type="submit" value="调用接口"/>
</form>

<div id="getVenueByZone">19、根据区域查场馆</div>
<form action="/sportcmbs/childVenue/getVenueByZone.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenue/getVenueByZone.htm

   //入参
        运动类型Id：kindsId
        区域Id：zoneId
        page
        pagesize


  //出参

    </textarea>
  <%--<input type="hidden" name="kindsId" value="1001109">--%>
  <input type="hidden" name="zoneId" value="201891">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">

  <input type="submit" value="调用接口"/>
</form>

<div id="getVenueByPrice">20、场馆按价格排序</div>
<form action="/sportcmbs/childVenue/getVenueByPrice.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenue/getVenueByPrice.htm

   //入参

         运动类型Id：kindsId
        page
        pagesize


  //出参

    </textarea>
  <input type="hidden" name="kindsId" value="1001109">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="getVenueBySize">21、场馆按大小排序</div>
<form action="/sportcmbs/childVenue/getVenueBySize.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/childVenue/getVenueBySize.htm

   //入参

         运动类型Id：kindsId
        page
        pagesize


  //出参

    </textarea>
  <input type="hidden" name="kindsId" value="1001109">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="getCoachByAge">22、教练按年龄排序</div>
<form action="/sportcmbs/coach/getCoachByAge.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/coach/getCoachByAge.htm

   //入参

         运动类型Id：kindsId
        page
        pagesize


  //出参

    </textarea>
  <input type="hidden" name="kindsId" value="1001109">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="getCoachBySex">23、教练按性别查询</div>
<form action="/sportcmbs/coach/getCoachBySex.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/coach/getCoachBySex.htm

   //入参

         运动类型Id：kindsId
        性别：sex
        page
        pagesize


  //出参

    </textarea>
  <input type="hidden" name="kindsId" value="1001113">
  <input type="hidden" name="sex" value="1">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>



<div id="getActivityByZoneId">24、活动按区域查询</div>
<form action="/sportcmbs/activity/getActivityByZoneId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/activity/getActivityByZoneId.htm

   //入参

         运动类型Id：kindsId
        区域id：zoneId
        page
        pagesize


  //出参

    </textarea>
  <input type="hidden" name="kindsId" value="1001113">
  <input type="hidden" name="zoneId" value="201891">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pageSize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="getActivityByIsFree">25、活动按是否收费查询</div>
<form action="/sportcmbs/activity/getActivityByIsFree.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/activity/getActivityByIsFree.htm

   //入参

         运动类型Id：kindsId
       是否收费：isFree
        page
        pagesize


  //出参

    </textarea>
  <input type="hidden" name="kindsId" value="1001113">
  <input type="hidden" name="isFree" value="1">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pageSize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="getListAsShop">26、卖家版我的订单</div>
<form action="/sportcmbs/order/getListAsShop.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/getListAsShop.htm

   //入参

        用户Id：memberId
        订单状态：orderStatus
        page
        pagesize


  //出参
        订单id:id,
        用户Id: memberId,
        订单编号：orderNo,
        联系人：contact,
        联系电话：telephone,
        类型：orderType（0：场馆  1：教练  2：活动）,
        商品Id: goodsId,
        商品名称： goodsName,
        订单总金额：orderAmount,
        交易号： tradeNo,
        支付时间： payTime,
        订单状态： orderStatus（0：待支付  1：已支付  2：待评价  3：已完成）,
        创建时间： createDate,
        修改时间： updateDate

    </textarea>
  <input type="hidden" name="memberId" value="9">
  <input type="hidden" name="orderStatus" value="1">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="getListAsMember">27、买家版我的订单</div>
<form action="/sportcmbs/order/getListAsMember.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/getListAsMember.htm

   //入参

        用户Id：memberId（不可为空）
        订单状态：orderStatus
        page
        pagesize


  //出参

        订单id:id,
        用户Id: memberId,
        订单编号：orderNo,
        联系人：contact,
        联系电话：telephone,
        类型：orderType（0：场馆  1：教练  2：活动）,
        商品Id: goodsId,
        商品名称： goodsName,
        订单总金额：orderAmount,
        交易号： tradeNo,
        支付时间： payTime,
        订单状态： orderStatus（0：待支付  1：已支付  2：待评价  3：已完成）,
        创建时间： createDate,
        修改时间： updateDate

    </textarea>
  <input type="hidden" name="memberId" value="6">
  <input type="hidden" name="orderStatus" value="0">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="getOrderDetail">28、订单详情</div>
<form action="/sportcmbs/order/getOrderDetail.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/getOrderDetail.htm

   //入参

       订单Id：orderId（不可为空）

  //出参
        订单id:id,
        用户Id: memberId,
        订单编号：orderNo,
        联系人：contact,
        联系电话：telephone,
        类型：orderType（0：场馆  1：教练  2：活动）,
        商品Id: goodsId,
        商品名称： goodsName,
        订单总金额：orderAmount,
        开始时间：beginDate,
        结束时间： endDate,
        支付方式：payAccount（0：支付宝  1：微信  2：银行卡）,
        交易号： tradeNo,
        支付时间： payTime,
        是否已退款：isRefund（0：未退款  1：已退款  2：申请退款中）,
        订单状态： orderStatus（0：待支付  1：已支付  2：待评价  3：已完成）,
        创建时间： createDate,
        修改时间： updateDate

    </textarea>
  <input type="hidden" name="orderId" value="10000007">
  <input type="submit" value="调用接口"/>
</form>


<div id="deleteOrder">29、删除订单、撤销订单</div>
<form action="/sportcmbs/order/deleteOrder.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/deleteOrder.htm

   //入参

       订单Id：orderId（不可为空）


  //出参

    </textarea>
  <input type="hidden" name="orderId" value="10000001">
  <input type="submit" value="调用接口"/>
</form>


<div id="confirmOrder">30、确认订单（使用完后）</div>
<form action="/sportcmbs/order/confirmOrder.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/confirmOrder.htm

   //入参

       订单Id：orderId（不可为空）


  //出参

    </textarea>
  <input type="hidden" name="orderId" value="10000001">
  <input type="submit" value="调用接口"/>
</form>


<div id="refundMoney">31、申请退款</div>
<form action="/sportcmbs/order/refundMoney.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/refundMoney.htm

   //入参

       订单Id：orderId（不可为空）


  //出参

    </textarea>
  <input type="hidden" name="orderId" value="10000002">
  <input type="submit" value="调用接口"/>
</form>


<div id="remark">32、评价</div>
<form action="/sportcmbs/order/remark.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/order/remark.htm

   //入参

        商品Id：dataId（不可为空）
        订单Id:orderId（不可为空）
        评价：kind（0：好评  1：中评  2：差评，不可为空）


  //出参

    </textarea>
  <input type="hidden" name="dataId" value="6">
  <input type="hidden" name="orderId" value="10000004">
  <input type="hidden" name="kind" value="2">
  <input type="submit" value="调用接口"/>
</form>


<div id="getPurseBalance">33、钱包余额</div>
<form action="/sportcmbs/member/getPurseBalance.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/getPurseBalance.htm

   //入参

       用户Id:memberId（不可为空）

  //出参

    </textarea>
  <input type="hidden" name="memberId" value="9">
  <input type="submit" value="调用接口"/>
</form>


<div id="setPayPassword">34、设置提现密码</div>
<form action="/sportcmbs/member/setPayPassword.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/member/setPayPassword.htm

   //入参

        用户Id:memberId（不可为空）
        登录密码：password（不可为空）
        提现密码：payPassword（不可为空）
        确认密码：checkPassword（不可为空）

  //出参

    </textarea>
  <input type="hidden" name="memberId" value="9">
  <input type="hidden" name="password" value="123456">
  <input type="hidden" name="payPassword" value="123456">
  <input type="hidden" name="checkPassword" value="123456">
  <input type="submit" value="调用接口"/>
</form>


<div id="getWithdrawals">35、申请提现</div>
<form action="/sportcmbs/withdrawals/getWithdrawals.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/withdrawals/getWithdrawals.htm

   //入参
    用户id:memberId（不可为空）
    提现到账户:drawAccount（不可为空）
    姓名：name（不可为空）
    手机号：mobile（不可为空）
    身份证号：idCard（不可为空）
    提现密码:payPassword（不可为空）

  //出参

    </textarea>
  <input type="hidden" name="memberId" value="9">
  <input type="hidden" name="drawAccount" value="15803882486">
  <input type="hidden" name="name" value="王小二">
  <input type="hidden" name="mobile" value="15803882486">
  <input type="hidden" name="idCard" value="410123199002285678">
  <input type="hidden" name="payPassword" value="123456">
  <input type="submit" value="调用接口"/>
</form>


<div id="getWithdrawalsList">36、申请提现记录</div>
<form action="/sportcmbs/withdrawals/getWithdrawalsList.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/withdrawals/getWithdrawalsList.htm

   //入参

       用户Id:memberId
        page
        pagesize

  //出参

    </textarea>
  <input type="hidden" name="memberId" value="9">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pagesize" value="10">
  <input type="submit" value="调用接口"/>
</form>


<div id="cancelWithdrawals">37、撤销提现申请/删除提现记录</div>
<form action="/sportcmbs/withdrawals/cancelWithdrawals.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/withdrawals/cancelWithdrawals.htm

   //入参

       提现Id:withdrawalsId（不可为空）


  //出参

    </textarea>
  <input type="hidden" name="withdrawalsId" value="1">
  <input type="submit" value="调用接口"/>
</form>


<div id="shortMessage">38、短信接口</div>
<form action="/sportcmbs/shortMessage/shortMessage.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:80/sportcmbs/shortMessage/shortMessage.htm
   //入参
        电话号：telPhone(不可为空)
  //出参

    </textarea>
  <input type="hidden" name="telPhone" value="15803882486">
  <input type="submit" value="调用接口"/>
</form>


<div id="addRecommend">39、我有好创意,我想挑毛病,我要吐槽一下
</div>
<form action="/sportcmbs/recommend/addRecommend.htm" enctype="multipart/form-data" method="POST">
<textarea rows="3" cols="20" style="width: 700px;height: 200px">
     接口地址：http://192.168.3.99:80/sportcmbs/recommend/addRecommend.htm
     入参:
     (memberid ;//提交建议,吐槽者id not null)
     (kind;//类型  1.创意 2.挑毛病 3.吐槽 not null)
     (context;//'内容' not null)
     (remark;//'回复' 可以传空)
     出参:
</textarea>
  <input type="hidden" name="context" value="我的反馈内容"/>
  <input type="hidden" name="kind" value="1"/>
  <input type="hidden" name="remark" value="回复内容"/>
  <input type="hidden" name="memberId" value="9"/>
  <input type="submit" value="挑毛病&吐槽&建议添加"/>
</form>

<div id="getRecommendList">40、我有好创意,我想挑毛病,我要吐槽一下列表展示</div>
<form action="/sportcmbs/recommend/getRecommendList.htm" enctype="multipart/form-data">
<textarea rows="3" cols="20" style="width: 700px;height: 200px">
     接口地址：http://192.168.3.99:80/sportcmbs/recommend/getRecommendList.htm
     入参:
     page:
     pageSize:
     kind:
     1.创意 2.挑毛病 3.吐槽
     出参:
     (memberid ;//提交建议,吐槽者)
     (kind;//类型  1.创意 2.挑毛病 3.吐槽)
     (context;//'内容')
     (remark;//'回复')

</textarea>
  <input type="hidden" name="page" value="1"/>
  <input type="hidden" name="pageSize" value="10"/>
  <input type="hidden" name="kind" value="1"/>
  <input type="submit" value="挑毛病&吐槽&建议列表接口"/>
</form>

<div id="coachDetailInfo">41、教练详情</div>
<form action="/sportcmbs/coach/getCoachDetailInfo.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/coach/getCoachDetailInfo.htm
   //入参
        教练id:coachId
   //出参
        教练id：id;
        教练名称： name;
        性别,1为男2为女：sex;
        生日： birthday;
        身高： height;
        体重： weight;
        电话：telPhone;
        教学经历：teachingCareer;
        常驻场馆名称（包含地址和场馆名）：venueName;
        运动类型id： sportId;
        教练简介： intro;
        可教时间，相当于营业时间： businessTime;
        身份证正面照路径： idCardFront;
        身份证背面照路径：idCardBack;
        是否认证（0：未认证  1：已认证）： isTrue;
        头像路径： headPath;
        头像绝对路径：headRealPath;
        是否通过审核（0：未审核  1：通过  2：未通过）：isCheck;
        是否是热门教练（0：不是  1：是）：isRecommend;
        状态（-1：已删除  0：正常  1：未删除）：status;
        创建时间： createDate;
        修改时间：updateDate;
    </textarea>
  <input type="hidden" name="coachId" value="100000030">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId">42、根据教练id查询课程列表</div>
<form action="/sportcmbs/coach/getCourseListByMemId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/coach/getCourseListByMemId.htm
   //入参
        教练id:coachId
   //出参

        课程id：id;
        教练id：coachId;
        课程类型（1：月份  2：季份  3：年份）：courseType;
        课程名称：courseName;
        课程介绍：introduct;
        上课时间：courseTime;
        单价：price;
        优惠价：salesPrice;
        开始时间： startDate;
        结束时间：endDate;
        主图存储路径：picPath;
        主图存储绝对路径：picRealPath;
        状态（-1：已删除  0：可预约  1：过期）：status;
        创建时间：createDate;
        修改时间： updateDate;

    </textarea>
  <input type="hidden" name="coachId" value="100000023">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId2">43、根据运动类型id分页查询教练列表</div>
<form action="/sportcmbs/coach/getCoachListBySportId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/coach/getCoachListBySportId.htm
   //入参
        运动类型id:sportId
        page:1
        pageSize:10
   //出参
         教练id：id,
        教练姓名：coachName,
        性别：sex,
        生日：birthday,
        年龄：age,
        电话：telPhone,
        简介：intro,
        营业时间：businessTime,
        头像路径：headPath,
        头像绝对：headRealPath,
        区域名称：zoneName,
        运动类型名称：kindsName

    </textarea>
  <input type="hidden" name="sportId" value="1001113">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pageSize" value="10">
  <input type="submit" value="调用接口"/>
</form>
<div id="getCourseListByMemId3">44、查询教练</div>
<form action="/sportcmbs/coach/getCoachIndexResearch.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/coach/getCoachIndexResearch.htm
   //入参
        coachName:张三

   //出参
    </textarea>
  <input type="hidden" name="coachName" value="张三">

  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId9">45、查询活动详情</div>
<form action="/sportcmbs/activity/getActivityDetail.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/getActivityDetail.htm
   //入参
        ActivityId:10
   //出参
    </textarea>
  <input type="hidden" name="ActivityId" value="10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId10">46、根据运动类型查询活动列表</div>
<form action="/sportcmbs/activity/getActivityBySportId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/getActivityBySportId.htm
   //入参
        sportId:1001108
        page:
        pageSize:
   //出参

            活动id:id,
            活动名称：activityName,
            活动价格：activityPrice
            活动地址：activityAddress,
            头像路径： activityPath,
            头像绝对路径：activityRealPath,
            活动类型：activityType（0：个人  1：团体）,
            是否免费：isFree（0：收费  1：免费  2：AA制）,
            运动类型id:sportId,
            区域名称：zoneName,
            运动类型名称： sportName

    </textarea>
  <input type="hidden" name="sportId" value="1001108">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pageSize" value="10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId11">47、创建活动</div>
<form action="/sportcmbs/activity/addActivity.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/addActivity.htm
   //入参
        sportId:1001108//运动id
        activityName:公益活动//活动名称
        activityType:0//运动类型0:个人，1:团体两种类型
        activityAddress:运动地点//
        userId:1000000//创建人id
        activityContacts:陈浩南//联系人姓名
        contactsPhone:15836181578//联系人电话
        activityIntroduce:活动介绍//
        startDate:2016-12-01 10:00:00//活动开始时间
        endDate:2016-12-01 18:00:00//活动结束时间
        enrollDate:2016-12-02 18:00:00//报名截止时间
   //出参
    </textarea>
  <%--    <input type="hidden" name="id" value="1000000001">--%>
  <input type="hidden" name="sportId" value="1001108">
  <input type="hidden" name="activityName" value="足球活动">
  <input type="hidden" name="activityType" value="0">
  <input type="hidden" name="activityAddress" value="国家大学科技园东区13号楼前草坪">
  <input type="hidden" name="userId" value="1">
  <input type="hidden" name="activityContacts" value="陈浩南">
  <input type="hidden" name="contactsPhone" value="13903745530">
  <input type="hidden" name="activityIntroduce" value="本活动为公益活动">
  <input type="hidden" name="startDate" value="2016-12-01 10:00:00">
  <input type="hidden" name="endDate" value="2016-12-01 18:00:00">
  <input type="hidden" name="enrollDate" value="2016-12-02 18:00:00">
  <input type="file" name="shopFile">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId12">48、根据活动名称查询</div>
<form action="/sportcmbs/activity/getActivityIndexResearch.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/getActivityIndexResearch.htm
   //入参
        indexCondition:足球//
   //出参
    </textarea>
  <input type="hidden" name="indexCondition" value="足球">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pageSize" value="10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId13">49、查询热门活动</div>
<form action="/sportcmbs/activity/getRecommendActivity.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/getRecommendActivity.htm
   //入参
        区域名称 :zoneName
   //出参
            活动id:id,
            活动名称：activityName,
            活动价格：activityPrice
            活动地址：activityAddress,
            头像路径： activityPath,
            头像绝对路径：activityRealPath,
            活动类型：activityType（0：个人  1：团体）,
            是否免费：isFree（0：收费  1：免费  2：AA制）,
            运动类型id:sportId,
            区域名称：zoneName,
            运动类型名称： sportName
    </textarea>
  <input type="hidden" name="zoneName" value="郑州市">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId14">50、添加/取消收藏</div>
<form action="/sportcmbs/house/addToHouse.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/house/addToHouse.htm
   //入参
        memberId:10//会员id
        type:1//收藏类型(0：场馆  1：教练  2:活动)
        dataId:10//商品id
   //出参
        flg：0失败；1收藏成功；2取消收藏成功
    </textarea>
  <input type="hidden" name="memberId" value="27">
  <input type="hidden" name="type" value="0">
  <input type="hidden" name="dataId" value="3">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId15">51、取消收藏</div>
<form action="/sportcmbs/house/cancelToHouse.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/house/cancelToHouse.htm
   //入参
        houseId:100000003//收藏id
   //出参
    </textarea>
  <input type="hidden" name="houseId" value="100000003">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId16">52、收藏列表展示</div>
<form action="/sportcmbs/house/getHouseList.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/house/getHouseList.htm
   //入参
        memberId:10//会员id
        type:1
        page:1
        pageSize:10
   //出参
    </textarea>
  <input type="hidden" name="memberId" value="10">
  <input type="hidden" name="type" value="1">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pageSize" value="10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId17">53、活动生成订单</div>
<form action="/sportcmbs/activity/createActiveOrder.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/createActiveOrder.htm
   //入参
        memberId:10//会员id(买家id)
        contact:陈强
        telephone:18639716815
        goodsId:10//购买的该活动id
        orderAmount:100.00//活动价格
   //出参
    </textarea>
  <input type="hidden" name="memberId" value="10">
  <input type="hidden" name="contact" value="陈强">
  <input type="hidden" name="telephone" value="18639716815">
  <input type="hidden" name="goodsId" value="1000000014">
  <input type="hidden" name="orderAmount" value="100.00">
  <input type="submit" value="调用接口"/>
</form>
<div id="getCourseListByMemId18">54、教练生成订单</div>
<form action="/sportcmbs/coach/createCoachOrder.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/coach/createCoachOrder.htm
   //入参

        memberId:10//会员id(买家id)
        contact:陈强
        telephone:18639716815
        goodsId:10//购买的该教练课程的id
        orderAmount:100.00//教练课程价格

   //出参
    </textarea>
  <input type="hidden" name="memberId" value="10">
  <input type="hidden" name="contact" value="陈强">
  <input type="hidden" name="telephone" value="18639716815">
  <input type="hidden" name="goodsId" value="100000044">
  <input type="hidden" name="orderAmount" value="100.00">
  <input type="submit" value="调用接口"/>
</form>
<div id="getCourseListByMemId19">55、会员发布的活动列表</div>
<form action="/sportcmbs/activity/getPublishActivity.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/getPublishActivity.htm
   //入参
        memberId:10//会员id(买家id)
   //出参
    </textarea>
  <input type="hidden" name="memberId" value="10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId20">56、已经报名的活动列表</div>
<form action="/sportcmbs/activity/getActivitySignDetail.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/getActivitySignDetail.htm
   //入参
        memberId:10//会员id(活动发布人Id)
        goodsId:活动id
   //出参
    </textarea>
  <input type="hidden" name="memberId" value="100000028">
  <input type="hidden" name="goodsId" value="1000000014">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId21">57、用户取消已经发布的活动</div>
<form action="/sportcmbs/activity/cancelActivity.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/activity/cancelActivity.htm
   //入参
        goodsId:活动id
   //出参
    </textarea>
  <input type="hidden" name="goodsId" value="1000000014">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId22">58、测试批量更新</div>
<form action="/sportcmbs/test/addBatchHouse.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/test/addBatchHouse.htm
   //入参
        goodsId:活动id
   //出参
    </textarea>
  <input type="hidden" name="memberId" value="10">
  <input type="hidden" name="type" value="1">
  <input type="hidden" name="page" value="1">
  <input type="hidden" name="pageSize" value="10">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId23">59、批量删除活动收藏列表</div>
<form action="/sportcmbs/house/deleteHouseActive.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/house/deleteHouseActive.htm
   //入参
        houseId:活动id
   //出参
    </textarea>
  <input type="hidden" name="houseId" value="100000007,100000008,100000009">
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId24">60、显示会员绑定的银行卡列表</div>
<form action="/sportcmbs/bankNo/getBankNoByMemId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/bankNo/getBankNoByMemId.htm
   //入参
        memberId:会员id
   //出参
    </textarea>
  <input type="text" name="memberId" value="1" width="32" maxlength="32"/>
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId25">61、显示会员绑定的银行卡列表</div>
<form action="/sportcmbs/bankNo/getBankNoByMemId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/bankNo/getBankNoByMemId.htm
   //入参
        memberId:会员id
   //出参
    </textarea>
  <input type="text" name="memberId" value="1" width="32" maxlength="32"/>
  <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId26">62、显示会员绑定的银行卡列表</div>
<form action="/sportcmbs/bankNo/getBankNoByMemId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.23:8080/sportcmbs/bankNo/getBankNoByMemId.htm
   //入参
        memberId:会员id
   //出参
    </textarea>
  <input type="text" name="memberId" value="1" width="32" maxlength="32"/>
  <input type="submit" value="调用接口"/>
</form>



<div id="getHeadKinds">63、约场馆、约教练、约活动头部类型</div>
<form action="/sportcmbs/kinds/getHeadKinds.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8090/sportcmbs/kinds/getHeadKinds.htm
   //入参

   //出参
        类型id:id
        类型名称：name
    </textarea>
  <input type="submit" value="调用接口"/>
</form>

<div id="isHouse">64、是否收藏</div>
<form action="/sportcmbs/house/isHouse.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8090/sportcmbs/kinds/getHeadKinds.htm
   //入参
        memberId:10//会员id
        type:1//收藏类型(0：场馆  1：教练  2:活动)
        dataId:10//商品id
   //出参
        flg:0、系统数据错误；1、收藏；2、未收藏
    </textarea>
  <input type="hidden" name="memberId" value="27">
  <input type="hidden" name="type" value="0">
  <input type="hidden" name="dataId" value="3">
  <input type="submit" value="调用接口"/>
</form>
</body>
</html>
