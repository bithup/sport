package com.xgh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;




/**
 * 项目名称：h2yorsos  
 * 类名称：OrderUtil  
 * 类描述：  订单工具类
 * 创建人：侯飞龙  
 * 创建时间：2015年5月19日 上午10:22:37  
 * 修改人：侯飞龙
 * 修改时间：2015年5月19日 上午10:22:37  
 * 修改备注：  
 * @version
 */
public class OrderUtil {
	
	public final static int orderPushMaxNum = 10;
	
	
	/**
	 * 生成订单编号
	 * @return
	 */
	public static String getOrderNo(){
		String orderNo="";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Random random = new Random();
		String randomCode = "";
		orderNo=df.format(new Date());
		for ( int i = 0; i < 6; i++ ){
			randomCode += Integer.toString(random.nextInt(9));
		}
		orderNo+=randomCode;
		return orderNo;
	}
	

}
