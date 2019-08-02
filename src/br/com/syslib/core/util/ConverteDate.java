package br.com.syslib.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConverteDate {

	public static String converteDateString(Date dtData){  
		   SimpleDateFormat formatBra = new SimpleDateFormat("dd-MM-yyyy");		  	     
		  return (formatBra.format(dtData)); 
		} 
		
		public static Date converteStringDate(String data) {   
	        if (data == null || data.equals(""))  
	            return null;  

	        Date date = null;  
	        try {   
	            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
	            date = (java.util.Date)formatter.parse(data);  
	            return date;
	        } catch (ParseException e) {              
	        	e.printStackTrace();
	        }  
	        return null;
		}
		
		public static String formataData(String data, String parttern) {
			String[] info = data.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat(parttern);
			
			Calendar calendarData = Calendar.getInstance();
			calendarData.set(Calendar.DAY_OF_MONTH,Integer.parseInt(info[2]));
			calendarData.set(Calendar.MONTH,Integer.parseInt(info[1]));
			calendarData.set(Calendar.YEAR,Integer.parseInt(info[0]));
			
			return sdf.format(calendarData.getTime());
		}
		
}
