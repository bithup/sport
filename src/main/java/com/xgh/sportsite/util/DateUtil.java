package com.xgh.sportsite.util;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public class DateUtil {

    public static int dateFromToAge(String start) {

        java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String stop = sim.format(new java.util.Date());
        int zs = (Integer.parseInt(stop.substring(0, 4)) - Integer.parseInt(start.substring(0, 4))) - 1;
        int starty = Integer.parseInt(start.substring(5, 7));
        int stopy = Integer.parseInt(stop.substring(5, 7));
        int startz = Integer.parseInt(start.substring(8, 10));
        int stopz = Integer.parseInt(stop.substring(8, 10));
        int yf = 0;
        int jz = 0;
        if (starty > stopy) {
            yf = 12 - starty + stopy;
        } else {
            yf = 12 - stopy + starty;
        }
        if (startz > stopz) {
            jz = (startz - stopz) % 7;
        } else {
            jz = (stopz - startz) % 7;
        }
        System.out.println(zs + "岁" + yf + "个月" + jz + "周");

        return zs;
    }

}
