package pom.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataComman 
{
	// To get tommorow date and convert in format yyyy-mm-dd
	static Date date = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
	static String jDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	
	public static String baseURL = "http://www.mymoneykart.com/mmkweb/";
	
	// User credential Details
	public static String username = "9422307801";
	public static String password = "123456";
	
	// Feedback Form 
	
	public static String fName = "dummy User";
	public static String fEmail = "narendra@yopmail.com";
	public static String fMobile = "9422307801";
	public static String fType = "Career";
	public static String fMessage = "Hello Admin";
	
	// Bus Searching
	
	public static String sourceCity = "Chennai";
	public static String destinationCity = "Hydesrabad";
	public static String journecyDate = jDate;
	
}
