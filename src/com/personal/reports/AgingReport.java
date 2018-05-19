package com.personal.reports;

import com.personal.dataaccess.DaysFromOrderDA;

public class AgingReport implements Report {
	
	@Override
	public String generateCSVReport() {
		String header = "Aging Report - " +  new java.util.Date() 
				+ " \n Job ID, Job Date, Since, Status \n";
		
		String csvString = null;
		try{
			
			DaysFromOrderDA reportDA = new DaysFromOrderDA();
			csvString = reportDA.getDaysFromOrderReport();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return header + csvString;
	}

}
