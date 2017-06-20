package com.mmk.commonutils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BusSiteTestData 
{
		static Date date = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
		static String jDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		public static String busSiteURL = "http://bus.mymoneykart.com/";
		public static String preferredCity = "Surat";
	
	
		// Bus Searching
	
		public static String sourceCity = "Bangalore";
		public static String destinationCity = "Hyderabad";
		public static String journecyDate = jDate;
}
