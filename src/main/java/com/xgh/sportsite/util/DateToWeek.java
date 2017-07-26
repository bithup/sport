package com.xgh.sportsite.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateToWeek {

	 public String dayForWeek(String pTime) throws Exception {
		 String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar c = Calendar.getInstance();
		  c.setTime(format.parse(pTime));
		  int dayForWeek =c.get(Calendar.DAY_OF_WEEK) - 1;
		  if(dayForWeek<0){
			  dayForWeek = 0;
		  }
		  return weekDays[dayForWeek];
		 }
	
	/*public static void main(String[] args) {
		
		String dateTime="2016-10-16";
		try {
		String week=dayForWeek(dateTime);
		System.out.println(week);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
}
