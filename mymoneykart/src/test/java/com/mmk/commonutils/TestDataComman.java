package com.mmk.commonutils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataComman 
{
	// To get tomorrow date and convert in format yyyy-mm-dd
	static Date date = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
	static String jDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	
	public static String baseURL = "http://www.mymoneykart.com/mmkweb/";
	
	// User credential Details
	public static String username = "8866691628";
	public static String password = "123456";
	public static String usersname = "Jainish Kapadia";
	// Feedback Form 
	
	public static String fName = "dummy User";
	public static String fEmail = "narendra@yopmail.com";
	public static String fMobile = "9422307801";
	public static String fType = "Career";
	public static String fMessage = "Hello Admin";
	
	// Bus Searching
	
	public static String sourceCity = "Bangalore";
	public static String destinationCity = "Hyderabad";
	public static String journecyDate = jDate;
	
	
	//Passenger Details
	
	public static String pFirstName = "Narendra";
	public static String pLastName = "Kishore";
	public static String pGender = "Male";
	public static String pAge = "25";
	public static String pID = "Passport";
	public static String pIDNumber = "PAS55454AS510";
			
	public static String friendName[]= {"One", "Two" , "Three"};
	public static String friendNumber[]= {"9422300010", "9422300011" , "9422300012"};
	
}
