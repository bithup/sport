package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.ShortMessage;
import com.xgh.sportsite.services.IShortMessageService;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.sportsite.util.ValidationCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21.
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/sportsite/")
public class ShortMessageController extends BaseController{

    private Logger logger = Logger.getLogger(MemberUserController.class);

    @Autowired
    protected IShortMessageService shortMessageService;


    @RequestMapping(value="shortMessage")
    public void shortMessage(){
        Map<String,Object> resultMap = new HashMap<String, Object>();

        String telPhone = request.getParameter("telPhone");
       // String validationCode = request.getParameter("validationCode");
        String validationCode = ValidationCode.getSecurityCode(6,3);


        String sendUrl = ConstantUtil.SEND_URL;
        System.out.println(sendUrl);
        String sendUrl1 = sendUrl.replace("{用户名}",URLEncoder.encode(ConstantUtil.SMS_ACCOUNT));
        String sendUrl2 = sendUrl1.replace("{短信密码}",URLEncoder.encode(ConstantUtil.SMS_PASSWORD));
        String sendUrl3 = sendUrl2.replace("{短信号码}",telPhone);
        String sendUrl4 = sendUrl3.replace("{短信内容}",validationCode);

        String sTemp1=sendUrl4.substring(0,sendUrl4.lastIndexOf("&")+5);
        String sTemp2=sendUrl4.substring(sendUrl4.lastIndexOf("&")+5);

        try {
            sTemp2=URLEncoder.encode(sTemp2,"gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendUrl4=sTemp1+sTemp2;

        InputStream in = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(sendUrl4);
            in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                sb.append(inputLine);
            }
            System.out.println("短信批次号=" + sb.toString());
            /*return sb.toString();*/
            String[] result = sb.toString().split("&");
            String num = result[0].substring(result[0].lastIndexOf("=") + 1, result[0].length());
            String errId = result[4].substring(result[4].lastIndexOf("=") + 1, result[4].length());

            ShortMessage smsRecord = new ShortMessage();
            smsRecord.setPhoneNum(telPhone);
            smsRecord.setContent(validationCode);
            smsRecord.setSendTime(new Date());
            if (!"0".equals(num)) {
                resultMap = getResultMap("1", "成功发送");
                smsRecord.setSatatus(1);
            } else {

                resultMap = getResultMap("0", "发送失败");
                smsRecord.setSatatus(Integer.parseInt(errId));
            }
            shortMessageService.insert(smsRecord);
            request.getSession().setAttribute("validationCode",smsRecord.getContent());
            request.getSession().setMaxInactiveInterval(30*60);
            String sessionId = request.getSession().getId();
            System.out.println(sessionId);
            logger.info("==================="+telPhone+"=====================验证码为"+validationCode);
            outJson(resultMap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }
        }
    }

}
