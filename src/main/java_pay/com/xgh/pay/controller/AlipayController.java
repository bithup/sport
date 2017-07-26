package com.xgh.pay.controller;

import alipay.config.AlipayConfig;
import alipay.util.AlipayCore;
import alipay.util.AlipayNotify;
import alipay.util.AlipaySubmit;
import alipayApp.config.AlipayConfigApp;
import alipayApp.util.AlipayCoreApp;
import alipayApp.util.AlipayNotifyApp;
import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.Order;
import com.xgh.sportsite.services.IOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Administrator on 2016/10/17.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/alipay/")
public class AlipayController extends BaseController {

    private Logger logger = Logger.getLogger(AlipayController.class);

    @Autowired
    protected IOrderService orderService;

    /*
    * 支付宝网页支付
    * */
    @RequestMapping("alipayMoney")
    public ModelAndView toAlipayMoney() {
        ModelAndView view = new ModelAndView();

        long id = Long.parseLong(request.getParameter("orderId"));
        Order order = orderService.get(id);
        logger.info("order........." + order);

        String goodsAmount = order.getOrderAmount() + "";

        String subject = "运动放上去";
        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");//手机网页支付
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        sParaTemp.put("show_url", "www.fangshangqu.com");
        sParaTemp.put("out_trade_no", order.getOrderNo());
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", goodsAmount);

        //sParaTemp.put("body", body);
        //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
        //如sParaTemp.put("参数名","参数值");

        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        logger.info("sHtmlText........." + sHtmlText);
        // out.println(sHtmlText);
        view.setViewName("pay/alipay");
        view.addObject("sHtmlText", sHtmlText);
        return view;

    }

    /**
     * 支付宝同步通知网页版(页面跳转.......)
     */
    @RequestMapping("paymentCallBack")
    public void paymentCallBack() {
//        PrintWriter out = null;
//        response.setContentType("text/html;charset=utf-8");
//        try {
//            out=response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
                logger.info(name+"---------"+valueStr);
            }
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            logger.info("trade_status:"+trade_status);
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

            //计算得出通知验证结果
            boolean verify_result = AlipayNotify.verify(params);
            logger.info("verify_result:"+verify_result);
            if (verify_result) {//验证成功
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    AlipayCore.logResult("return_url success!");
                }
                //该页面可做页面美工编辑
                //out("<");
                logger.info("接收支付宝同步通知验证成功");
            } else {
                //该页面可做页面美工编辑
                //out("验证失败");
                logger.info("接收支付宝同步通知验证失败");
            }

        } catch (Exception e) {
            logger.info("接收支付宝同步通知出错：" + e);
            e.printStackTrace();
        }
        try {
            response.sendRedirect("http://yundong.fangshangqu.com/home/baseInfo.htm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝异步通知网页版
     */
    @RequestMapping("paymentNotifyCallBack")
    public void paymentNotifyCallBack() {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号

            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号

            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

            if (AlipayNotify.verify(params)) {//验证成功
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    logger.info("支付宝web回调后台成功......"+trade_no);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("orderNo", out_trade_no);
                    Map<String, Object> orderMap = orderService.getIdByOrderNo(map);
                    String orderId = orderMap.get("orderId") + "";
                    map.put("orderId", orderId);
                    Order order = orderService.get(Long.parseLong(orderId));
                    if (order.getOrderStatus() == 0) {
                        order.setOrderStatus(1);//1已支付进行中
                        int random = (int) ((Math.random()*9+1)*100000);
                        order.setData3(String.valueOf(random));
                    }
                    order.setUpdateDate(new Date());
                    order.setTradeNo(trade_no);//交易号
                    order.setPayTime(new Date());//付款时间
                    int flag = orderService.update(order);
                    if (flag > 0) {
                        logger.info("订单状态修改成功.........");
                    } else {
                        logger.info("订单状态修改失败..........");
                    }
                    AlipayCore.logResult("notify_url success!");

                    out.print("success");
                }

            } else {//验证失败
                out.print("fail");
            }
        } catch (Exception e) {
            logger.info("接收支付宝异步通知出错：" + e);
            e.printStackTrace();
        }
    }


    /**
     * App支付宝付款接口
     */
    @RequestMapping("alipayMoneyApp")
    public void alipayMoneyApp() {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        long id = Long.parseLong(request.getParameter("orderId"));

        Order order = orderService.get(id);
        if (order == null) {
            resultMap.put("1", "订单不能为空");
        } else {
            String[] parameters = {
                    "partner=\"" + AlipayConfigApp.partner + "\"",
                    "seller_id=\"" + AlipayConfigApp.partner + "\"",
                    "out_trade_no=\"" + order.getOrderNo() + "\"",
                    "subject=\"运动放上去\"",
                    "body=\"运动放上去\"",
                    "total_fee=\"" + String.valueOf(order.getOrderAmount()) + "\"",
                    "notify_url=\"http://sportsite.fangshangqu.com/sportsite/alipay/notifyCallBackApp.htm\"",
                    "service=\"mobile.securitypay.pay\"",
                    "payment_type=\"1\"",
                    "input_charset=\"utf-8\""
            };
            String result = AlipayCoreApp.signAllString(parameters);
            resultMap.put("data1", result);
        }
        outJson(resultMap);

    }

    /**
     * App支付宝付款接口异步通知
     */
    @RequestMapping("notifyCallBackApp")
    public void notifyCallBackApp() {
        PrintWriter out = null;
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

        try {

            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //异步通知ID
            String notify_id = request.getParameter("notify_id");

            //sign
            String sign = request.getParameter("sign");
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

            if (notify_id != "" && notify_id != null) {////判断接受的post通知中有无notify_id，如果有则是异步通知。
                if (AlipayNotifyApp.verifyResponse(notify_id).equals("true"))//判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
                {
                    if (AlipayNotifyApp.getSignVeryfy(params, sign))//使用支付宝公钥验签
                    {
                        if (trade_status.equals("TRADE_SUCCESS")) {
                            logger.info("支付宝成功......");
                            //String ... = ...
                            //商户可以从params中获取通知中的数据
                            //然后进行支付成功后的业务逻辑处理
                            System.out.println("订单" + out_trade_no + "支付成功");
                            logger.info("支付回调后台成功......");
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("orderNo", out_trade_no);
                            Map<String, Object> orderMap = orderService.getIdByOrderNo(map);
                            String orderId = orderMap.get("orderId") + "";
                            map.put("orderId", orderId);
                            Order order = orderService.get(Long.parseLong(orderId));
                            if (order.getOrderStatus() == 0) {
                                order.setOrderStatus(1);//1已支付进行中
                            }
                            order.setUpdateDate(new Date());
                            order.setTradeNo(trade_no);//交易号
                            order.setPayTime(new Date());//付款时间
                            int flag = orderService.update(order);
                            if (flag > 0) {
                                logger.info("订单状态修改成功.........");
                            } else {
                                logger.info("订单状态修改失败..........");
                            }
                            //调试打印log
                            AlipayCoreApp.logResult("notify_url success!", "notify_url");
                        }
                    } else//验证签名失败
                    {
                        out.print("sign fail");
                    }
                } else//验证是否来自支付宝的通知失败
                {
                    out.print("response fail");
                }
            } else {
                out.print("no notify message");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
