package com.xgh.util;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/2/29.
 */
public class RegExpValidatorUtils {

    private Logger logger=Logger.getLogger(RegExpValidatorUtils.class);

    public static boolean regeMobile(String regex) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$");
        Matcher m = p.matcher(regex);
        System.out.println("m");
        boolean flg = m.matches();
        System.out.println(flg);
        return flg;
    }

    public static boolean regeIdCard(String regex) {

        Pattern p = Pattern.compile("(\\\\d{14}[0-9a-zA-Z])|(\\\\d{17}[0-9a-zA-Z])");
        Matcher m = p.matcher(regex);
        boolean flg = m.matches();
        System.out.println(flg);
        return flg;
    }






}
