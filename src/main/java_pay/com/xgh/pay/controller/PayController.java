package com.xgh.pay.controller;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.Order;
import com.xgh.sportsite.services.IOrderService;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.pay.util.DateTimeUtil;
import com.xgh.pay.util.EPay;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import com.xgh.util.JSONUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 *
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/pay/")
public class PayController extends BaseController {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(PayController.class);


    @Autowired
    protected IOrderService orderService;


    @RequestMapping(value = "xyPay")
    public void init() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String type = request.getParameter("redirect_type");
        response.setCharacterEncoding("utf-8");

        if ("ep_auth".equals(type)) {
            // Ex.1-1 快捷支付认证跳转
            try {
                PrintWriter out = response.getWriter();
/*              String trac_no = "TN" + DateTimeUtil.getDateTime();
                String acct_type = "0";
                String bank_no = "105100000017";
                String card_no = "6222809876543219810";
                String order_desc = "10";*/
                String trac_no = DateTimeUtil.getDateTime();
                logger.info("trac_no.........." + trac_no);
              /*String trac_no = request.getParameter("trac_no");*/
                String bank_no = "";
                String acct_type = request.getParameter("acct_type");//卡类型
                String card_no = request.getParameter("card_no");//卡号
                String user_name = request.getParameter("user_name");//持卡人姓名
                String memberId = request.getParameter("member_id");//会员id
                trac_no = memberId + "T" + trac_no;//memberId号和时间字符串拼接成交易号

                // 【重要】出于安全考虑，在调用函数前，需要对上面的参数进行防护过滤等操作
                out.println(EPay.epAuth(trac_no, acct_type, bank_no, card_no, user_name));

            } catch (Exception e) {
                logger.info("快捷支付认证异常处理");
            }
        } else if ("ep_authpay".equals(type)) {        // Ex.1-9 无绑定账户的快捷支付接口
            try {
                PrintWriter out = response.getWriter();
                String order_no = request.getParameter("order_no");
              /*String order_no = "SDK" + DateTimeUtil.getDateTime();*/
//              String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
//              String order_amount = new String(request.getParameter("order_amount").getBytes("ISO-8859-1"), "UTF-8");
                String order_amount = request.getParameter("order_amount");
                if ("".equals(order_amount)) {
                    resultMap = getResultMap("1", "金额不能为空", order_amount);
                    outJson(resultMap);
                }
                /*String order_title = new String(request.getParameter("order_title").getBytes("ISO-8859-1"), "UTF-8");*/
                String order_title = request.getParameter("order_title");
                if ("".equals(order_title)) {
                    resultMap = getResultMap("2", "订单标题不能为空", order_title);
                    outJson(resultMap);
                }
                String order_desc = request.getParameter("order_desc");
                if ("".equals(order_desc)) {
                    resultMap = getResultMap("3", "订单详情不能为空", order_desc);
                    outJson(resultMap);
                }
                String remote_ip = request.getRemoteAddr();
                System.out.println("remote_ip.............." + remote_ip);
                // 【重要】出于安全考虑，在调用函数前，需要对上面的参数进行防护过滤等操作
                out.println(EPay.epAuthPay(order_no, order_amount, order_title, order_desc, remote_ip));
            } catch (Exception e) {
                logger.info("无绑定账户快捷支付异常处理");
            }
        } else if ("gp_pay1".equals(type)) {            // Ex.2-1 网关支付跳转1
            try {
                PrintWriter out = response.getWriter();
//              String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                String order_no = "SDK" + DateTimeUtil.getDateTime();
                String order_amount = new String(request.getParameter("order_amount").getBytes("ISO-8859-1"), "UTF-8");
                if ("".equals(order_amount)) {
                    resultMap = getResultMap("1", "金额不能为空", order_amount);
                    outJson(resultMap);
                }
                String order_title = request.getParameter("order_title");
                if ("".equals(order_title)) {
                    resultMap = getResultMap("2", "订单标题不能为空", order_title);
                    outJson(resultMap);
                }
                String order_desc = request.getParameter("order_desc");
                if ("".equals(order_desc)) {
                    resultMap = getResultMap("3", "订单详情不能为空", order_desc);
                    outJson(resultMap);
                }
                String remote_ip = request.getRemoteAddr();
                // 【重要】出于安全考虑，在调用函数前，需要对上面的参数进行防护过滤等操作
                out.println(EPay.gpPay(order_no, order_amount, order_title, order_desc, remote_ip));
            } catch (Exception e) {

                logger.info("网关支付异常处理");
            }

        } else if ("ep_authquery".equals(type)) {

            try {
                PrintWriter out = response.getWriter();
                String trac_no = new String(request.getParameter("trac_no").getBytes("ISO-8859-1"), "UTF-8");
                out.println(EPay.epAuthQuery(trac_no));
            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("ep_authsyncwithsms".equals(type)) {

            try {
                PrintWriter out = response.getWriter();
                String trac_no = new String(request.getParameter("trac_no").getBytes("ISO-8859-1"), "UTF-8");
                String acct_type = new String(request.getParameter("acct_type").getBytes("ISO-8859-1"), "UTF-8");
                String bank_no = new String(request.getParameter("bank_no").getBytes("ISO-8859-1"), "UTF-8");
                String card_no = new String(request.getParameter("card_no").getBytes("ISO-8859-1"), "UTF-8");
                String user_name = request.getParameter("user_name");
                String cert_no = new String(request.getParameter("cert_no").getBytes("ISO-8859-1"), "UTF-8");
                String card_phone = new String(request.getParameter("card_phone").getBytes("ISO-8859-1"), "UTF-8");
                String expireDate = new String(request.getParameter("expireDate").getBytes("ISO-8859-1"), "UTF-8");
                String cvn = new String(request.getParameter("cvn").getBytes("ISO-8859-1"), "UTF-8");

                out.println(EPay.epAuthSyncWithSms(trac_no, acct_type, bank_no, card_no, user_name, cert_no, card_phone, expireDate, cvn));
            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("ep_authchecksms".equals(type)) {
            try {
                PrintWriter out = response.getWriter();
                String trac_no = new String(request.getParameter("trac_no").getBytes("ISO-8859-1"), "UTF-8");
                String sms_code = new String(request.getParameter("sms_code").getBytes("ISO-8859-1"), "UTF-8");
                out.println(EPay.epAuthCheckSms(trac_no, sms_code));
            } catch (Exception e) {
                logger.info("异常处理");
            }
            //先绑定再进行快捷支付
        } else if ("ep_pay".equals(type)) {

            try {
                PrintWriter out = response.getWriter();
      /*        String order_no = "SDK" + DateTimeUtil.getDateTime();
                String order_amount = "1234.56";
                String order_title = "话费充值";
                String order_desc = "中国移动全国手机号码均可充值";
                String card_no = "6222809876543219810";
                */
                String order_no = request.getParameter("order_no");
                System.out.println(order_no);
                String order_amount = request.getParameter("order_amount");
                String order_title = request.getParameter("order_title");
                String card_no = request.getParameter("card_no");
                String order_desc = request.getParameter("order_desc");
                String pay = EPay.epPay(order_no, order_amount, order_title, order_desc, card_no);
/*              out.println(EPay.epPay(order_no, order_amount, order_title, order_desc, card_no));
                String pay = EPay.epPay(order_no, order_amount, order_title, order_desc, card_no);
                System.out.println(pay);*/
                Map<String, Object> map_ = JSONUtil.getMap(pay);//
/*              String signType = map.get("sign_type") + "";
                System.out.println(signType);*/
                String transStatus = map_.get("trans_status") + "";//交易成功
                if (!"".equals(transStatus) && "1".equals(transStatus)) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    order_no = map_.get("order_no") + "";
                    map.put("orderNo", order_no);
                    Map<String, Object> orderMap = orderService.getIdByOrderNo(map);
                    String orderId = orderMap.get("orderId") + "";
                    map.put("orderId", orderId);
                    Order order = orderService.get(Long.parseLong(orderId));
                    order.setOrderStatus(1);//已支付进行中
                    order.setUpdateDate(new Date());
                    order.setPayTime(new Date());//付款时间
                    order.setTradeNo(order_no);//交易号
                    int flag = orderService.update(order);
                    if (flag > 0) {
                        logger.info("订单状态修改成功.........");
                    } else {
                        logger.info("订单状态修改失败..........");
                    }
                    resultMap = getResultMap("1", "快捷支付成功!");
                    outJson(resultMap);

                } else {
                    String errorCode = map_.get("errcode") + "";//错误代码
                    String errmsg = map_.get("errmsg") + "";//错误消息
                    resultMap = getResultMap(errorCode, errmsg);
                    outJson(resultMap);
                }


            } catch (Exception e) {
                logger.info("异常处理");
            }
        } else if ("ep_refund".equals(type)) {

            try {
                PrintWriter out = response.getWriter();
                String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                String order_date = new String(request.getParameter("order_date").getBytes("ISO-8859-1"), "UTF-8");
                String order_amount = new String(request.getParameter("order_amount").getBytes("ISO-8859-1"), "UTF-8");

                out.println(EPay.epRefund(order_no, order_date, order_amount));
            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("ep_query".equals(type)) {

            //order_date格式20140825
            try {
                PrintWriter out = response.getWriter();
                String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                String order_date = new String(request.getParameter("order_date").getBytes("ISO-8859-1"), "UTF-8");

                out.println(EPay.epQuery(order_no, order_date));
            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("ep_refundquery".equals(type)) {
            try {
                PrintWriter out = response.getWriter();
                String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                String order_date = new String(request.getParameter("order_date").getBytes("ISO-8859-1"), "UTF-8");

                out.println(EPay.epRefundQuery(order_no, order_date));

            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("ep_authcancel".equals(type)) {

            try {
                PrintWriter out = response.getWriter();
                String card_no = new String(request.getParameter("card_no").getBytes("ISO-8859-1"), "UTF-8");
                out.println(EPay.epAuthCancel(card_no));

            } catch (Exception e) {
                logger.info("异常处理");
            }


        } else if ("gp_refund".equals(type)) {
            try {
                PrintWriter out = response.getWriter();
                String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                String order_date = new String(request.getParameter("order_date").getBytes("ISO-8859-1"), "UTF-8");
                String order_amount = new String(request.getParameter("order_amount").getBytes("ISO-8859-1"), "UTF-8");

                out.println(EPay.gpRefund(order_no, order_date, order_amount));
            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("gp_query".equals(type)) {
            try {
                PrintWriter out = response.getWriter();
                String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                String order_date = new String(request.getParameter("order_date").getBytes("ISO-8859-1"), "UTF-8");
                out.println(EPay.gpQuery(order_no, order_date));
            } catch (Exception e) {
                logger.info("异常处理");
            }


        } else if ("gp_refundquery".equals(type)) {

            try {
                PrintWriter out = response.getWriter();
                String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                String order_date = new String(request.getParameter("order_date").getBytes("ISO-8859-1"), "UTF-8");
                out.println(EPay.gpRefundQuery(order_no, order_date));
            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("pyPay".equals(type)) {
            try {
                PrintWriter out = response.getWriter();
                String order_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
                out.println(order_no);
            } catch (Exception e) {
                logger.info("异常处理");
            }

        } else if ("ep_pyBatchPay".equals(type)) {
            //
            try {
                PrintWriter out = response.getWriter();
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator fileNames = multipartRequest.getFileNames();//对上传的txt文档进行解析

                for (int i = 0; fileNames.hasNext(); ++i) {
                    String name = (String) fileNames.next();
                    MultipartFile myfile = multipartRequest.getFile(name);
                    if (myfile.isEmpty()) {
                        logger.info("文件未上传");
                    } else {
                        String OriginalFileName = myfile.getOriginalFilename();
                        String saveName = DateUtil.getSystemTime().getTime() + "" + i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                        String realPath = ConstantUtil.TXT_PATH;
                        File filePath = new File(realPath);
                        if (!filePath.exists()) {
                            filePath.mkdirs();
                        }
                        try {
                            FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                        } catch (IOException var18) {
                            var18.printStackTrace();
                            logger.error(var18.getMessage(), var18);
                        }
                        System.out.println("222222");
                        StringBuilder sb = new StringBuilder();
                        String s = "";
                       /*BufferedReader br = new BufferedReader(new FileReader(file));*/
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(realPath + saveName), "GBK"));
                        while ((s = br.readLine()) != null) {
                            sb.append(s + "\n");
                        }
                        br.close();
                        String str = sb.toString();
                        //str是你想要的东西
                        System.out.println(str);
                        out.print(EPay.pyBatchPay(saveName, str));
                        System.out.println("007");
                    }
                }
            } catch (Exception e) {
                logger.info("异常处理");
            }
        }
    }

    /*
    * 选择支付方式
    * */
    @RequestMapping(value="payWay")
    public ModelAndView payWay(){
        ModelAndView view=new ModelAndView();
        view.setViewName("pay/payWay");
        String orderAmount=request.getParameter("orderAmount");
        String orderId=request.getParameter("orderId");
        view.addObject("orderAmount",orderAmount);
        view.addObject("orderId",orderId);
        return view;
    }
}

