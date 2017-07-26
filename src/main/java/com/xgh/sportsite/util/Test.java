package com.xgh.sportsite.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/2/16.
 */
public class Test {

    public static void main(String[] args) {
    /*    String start = "1989-05-13";
        java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String stop =  sim.format(new java.util.Date());
        int zs = (Integer.parseInt(stop.substring(0,4)) - Integer.parseInt(start.substring(0,4)))-1;
        int starty = Integer.parseInt(start.substring(5, 7));
        int stopy = Integer.parseInt(stop.substring(5, 7));
        int startz = Integer.parseInt(start.substring(8, 10));
        int stopz = Integer.parseInt(stop.substring(8, 10));
        int yf = 0;
        int jz = 0;
        if(starty > stopy){
            yf = 12 - starty + stopy;
        }else{
            yf = 12 - stopy + starty;
        }
        if(startz > stopz){
            jz = (startz - stopz) % 7;
        }else{
            jz = (stopz - startz) % 7;
        }
        System.out.println(zs + "岁" + yf + "个月" + jz + "周");*/

        int age = DateUtil.dateFromToAge("1989-03-23 14:26:03");
        System.out.println("年龄。。。" + age);
    }


}
