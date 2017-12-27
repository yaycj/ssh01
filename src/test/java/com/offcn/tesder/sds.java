package com.offcn.tesder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class sds {
	
	public static void main(String[] args) {
		Date d =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar c =Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DATE, c.get(Calendar.DATE)-1);
		

		String starttime=sdf.format(c.getTime());
		
		  String endtime= sdf.format(d);
		  System.out.println(starttime);
		  System.out.println(endtime);
	}

}
