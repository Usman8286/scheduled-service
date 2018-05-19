package com.personal.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	public static String resultSetToCSV(ResultSet rs) throws Exception{
		StringBuilder sb = new StringBuilder();
		
		ResultSetMetaData meta = rs.getMetaData();
		int noOfColumns = meta.getColumnCount();
		
		while(rs.next()){
			for(int i = 1; i <= noOfColumns; i++){
				String value = rs.getString(i);
				if(rs.wasNull()) value = "";
				
				sb.append(value);
				if(i != noOfColumns) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static String getCurrentDateString(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	public static String getCurrentFormattedDateString(String dateFormat){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}
}
