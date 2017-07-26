package com.xgh.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    /**
     * 对象转换为字符串，避免"null"的出现
     *
     * @param obj
     * @return
     */
    public static String objectToString(Object obj) {
        return obj == null ? null : obj + "";
    }

    /**
     * 判断字符串 是否 为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        if (null == str || "null".equals(str) || "".equals(str))
            return true;
        else {
            return false;
        }
    }
    /**
     * 截取中文+英文的长度
     * text要进行截取的文本
     * @param
     * @param_length截取的长度
     *            （此长度为中文字符的长度）
     * @param_endWith截取后附加的文字
     * @return
     */
    public static String subString(String text, int length, String endWith) {
        int textLength = text.length();
        int byteLength = 0;
        StringBuffer returnStr = new StringBuffer();
        for (int i = 0; i < textLength && byteLength < length * 2; i++) {
            String str_i = text.substring(i, i + 1);
            if (str_i.getBytes().length == 1) {// 英文
                byteLength++;
            } else {// 中文
                byteLength += 2;
            }
            returnStr.append(str_i);
        }
        try {
            if (byteLength < text.getBytes("GBK").length) {// getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3
                returnStr.append(endWith);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnStr.toString();
    }





    public static String dateValue(int flag) {
        String str = new String();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("MM");
        SimpleDateFormat format3 = new SimpleDateFormat("dd");
        if (flag == 1) {
            str = format1.format(new Date());
        } else if (flag == 2) {
            str = format2.format(new Date());
        } else if (flag == 3) {
            str = format3.format(new Date());
        }
        return str;
    }

    public static String formatDate(String str) {
        String s = new String();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date date = format.parse(str);
            s = format2.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 日期转换为字符串
     *
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
        Date date = null;
        Format f = null;
        try {
            f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			/*
			 * date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str); //
			 * 下面将字符串转换为日期格式后显示的格式是2009-09-15
			 * DateFormat.getDateInstance().format(date); //
			 * 如果想换一种别的格式，可以用下面的办法，得到任何的日期格式都可以 // 输出的结果为2009/09/15 17:20:12
			 * System.out.println(new
			 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date)); //
			 * 输出的结果为2009-09-15 17:20:12 System.out.println(new
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)); //
			 * SimpleDateFormat sdf2 = new //
			 * SimpleDateFormat("yyyy年MM月dd日");输出的结果为2009年09月15日 17:20:12
			 * System.out.println(new
			 * SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(date));
			 */
            date = (Date) f.parseObject(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String chengFa(String a, String b) {
        BigDecimal a1 = new BigDecimal(a.trim());
        BigDecimal b1 = new BigDecimal(b.trim());
        BigDecimal c = a1.multiply(b1);
        String value = String.valueOf(c);
        return value;
    }


    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0;
    }

    /**
     * 获取用户的ip
     */
    public static String ipUtil(HttpServletRequest request) {
        // 获取用户的IP地址，作为防钓鱼的一种方法
        String clientIp = request.getHeader("x-forwarded-for");
        if ((clientIp == null) || (clientIp.length() == 0)
                || ("unknown".equalsIgnoreCase(clientIp))) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if ((clientIp == null) || (clientIp.length() == 0)
                || ("unknown".equalsIgnoreCase(clientIp))) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((clientIp == null) || (clientIp.length() == 0)
                || ("unknown".equalsIgnoreCase(clientIp))) {
            clientIp = request.getRemoteAddr();
        }
        String ipAddress = clientIp;
        return ipAddress;
    }

    /**
     * 生成随机码
     *
     * @return
     */
    public static String getPswd(int length, boolean bl) {
        StringBuffer buf = new StringBuffer();
        if (bl) {
            buf.append("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
            buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,");
        }
        buf.append("1,2,3,4,5,6,7,8,9,0");
        String[] arr = buf.toString().split(",");
        StringBuffer b = new StringBuffer();
        java.util.Random r;
        int k;
        for (int i = 0; i < length; i++) {
            r = new java.util.Random();
            k = r.nextInt();
            b.append(String.valueOf(arr[Math.abs(k % arr.length)]));
        }
        return b.toString();
    }


    /**
     * 判断字符串是空
     * @param str
     * @return
     */
    public static boolean notEmpty(String str) {
        if(str!=null && !"".equals(str)){
            return  true;
        }
        return  false;
    }
    /**
     * 判断是否包含中文
     * @param str 要检查的字符串
     * @return true 包含 false 不包含
     */
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
