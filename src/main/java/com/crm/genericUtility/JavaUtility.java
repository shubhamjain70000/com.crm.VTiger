package com.crm.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandom()
	{
		Random r=new Random();
		int random = r.nextInt(1000);
		return random;
	}
	public String getSystemDate()
	{
		Date dt=new Date();
		String date = dt.toString();
		return date;
	}
	public String getSystemDateAndTimeInFormat()
	{
		SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date systemdate=new Date();
		String getDateandTime = dateformat.format(systemdate);
		System.out.println(getDateandTime);
		return getDateandTime.replaceAll(":","-");
	}

}
