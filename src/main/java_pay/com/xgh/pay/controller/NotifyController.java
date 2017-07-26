package com.xgh.pay.controller;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.*;
import com.xgh.sportsite.services.*;
import com.xgh.pay.util.Signature;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付成功，失败进行回调
 * Created by CQ on 2016/10/14.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "add/notify/")
public class NotifyController extends BaseController {


    private static Logger logger = Logger.getLogger(NotifyController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    @Autowired
    protected IOrderService orderService;

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IBankNoService bankNoService;

    @RequestMapping(value = "xyNotify")
    public void init() {

        logger.info("支付回调后台成功......");
        String method = request.getMethod();
        Map<String, String> params = new HashMap<String, String>();
        Map<?, ?> reqParams = request.getParameterMap();
        Iterator<?> iter = reqParams.keySet().iterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            String[] values = (String[]) reqParams.get(name);
            if ("get".equalsIgnoreCase(method))
                try {
                    params.put(name, new String(values[0].getBytes("ISO-8859-1"), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            else
                params.put(name, values[0]);
        }

        if (Signature.verifyMAC(params)) {                //验签成功

            if ("get".equalsIgnoreCase(method)) {            //前台通知
                //商户可以在这边进行 [前台] 回调通知的业务逻辑处理
                //注意：后台通知和前台通知有可能同时到来，注意 [需要防止重复处理]
                //前台跳转回来的通知，需要显示内容，如支付成功等
                if ("NOTIFY_ACQUIRE_SUCCESS".equalsIgnoreCase(params.get("event"))) {        //支付成功通知
                    logger.info("支付成功前端页面跳转......");
                    String order_no = params.get("order_no");
                    //String ... = ...
                    //商户可以从params中获取通知中的数据
                    //然后进行支付成功后的业务逻辑处理
                    System.out.println("订单" + order_no + "支付成功");
                    logger.info("支付回调后台成功......");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("orderNo", order_no);
                    Map<String, Object> orderMap = orderService.getIdByOrderNo(map);
                    String orderId = orderMap.get("orderId") + "";
                    map.put("orderId", orderId);
                    Order order = orderService.get(Long.parseLong(orderId));
                    order.setOrderStatus(1);//1已支付进行中
                    order.setUpdateDate(new Date());
                    order.setPayTime(new Date());//付款时间
                    int flag = orderService.update(order);
                    if (flag > 0) {
                        logger.info("订单状态修改成功.........");
                    } else {
                        logger.info("订单状态修改失败..........");
                    }

                    //支付成功业务逻辑处理
                } else if ("NOTIFY_ACQUIRE_FAIL".equalsIgnoreCase(params.get("event"))) {    //支付失败通知

                    //支付失败业务逻辑处理

                } else if ("NOTIFY_REFUND_SUCCESS".equalsIgnoreCase(params.get("event"))) {    //退款成功通知

                    //退款成功业务逻辑处理

                } else if ("NOTIFY_AUTH_SUCCESS".equalsIgnoreCase(params.get("event"))) {    //快捷支付认证成功通知

                    //认证成功业务逻辑处理

                }

            } else if ("post".equalsIgnoreCase(method)) {    //后台通知

                //商户可以在这边进行 [后台] 回调通知的业务逻辑处理
                //注意：后台通知和前台通知有可能同时到来，注意 [需要防止重复处理]
                if ("NOTIFY_ACQUIRE_SUCCESS".equalsIgnoreCase(params.get("event"))) {        //支付成功通知


                } else if ("NOTIFY_ACQUIRE_FAIL".equalsIgnoreCase(params.get("event"))) {    //支付失败通知

                    //支付失败业务逻辑处理

                } else if ("NOTIFY_REFUND_SUCCESS".equalsIgnoreCase(params.get("event"))) {    //退款成功通知

                    //退款成功业务逻辑处理

                } else if ("NOTIFY_AUTH_SUCCESS".equalsIgnoreCase(params.get("event"))) {    //快捷支付认证成功通知

                    logger.info("进入认证回调页面.............");
                    //认证成功业务逻辑处理
                    String trac_no = params.get("trac_no");//交易号
                    logger.info("trac_no........." + trac_no);
                    String card_no = params.get("card_no");//银行卡号
                    logger.info("card_no........." + card_no);
                    String user_name = params.get("user_name");//绑卡人姓名
                    logger.info("user_name........." + user_name);
                    String cardPhone = params.get("card_phone");//银行卡绑定电话
                    String certNo = params.get("cert_no");//身份证号码
                    System.out.println("身份证号.........." + certNo);
                    logger.info("身份证号.........." + certNo);
                    logger.info("cardPhone........." + cardPhone);
                    int a = trac_no.indexOf("T");
                    logger.info("a........" + a);
                    String memberId = trac_no.substring(0, a);
                    logger.info("memberId......" + memberId);
                    BankNo bankNo = new BankNo();
                    bankNo.setCardNo(card_no);//银行卡号
                    bankNo.setData1(user_name);
                    bankNo.setData2(cardPhone);
                    bankNo.setData3(certNo);//身份证号码
                    bankNo.setMemberId(Long.parseLong(memberId));
                    int flg = bankNoService.save(request, bankNo);
                    if (flg > 0) {
                        logger.info("认证成功..............");
                    }
                }
            }

        } else {                                    //验签失败
            System.out.println("验签失败");
            //不应当进行业务逻辑处理，即把该通知当无效的处理
            //商户可以在此记录日志等

        }
    }
}
