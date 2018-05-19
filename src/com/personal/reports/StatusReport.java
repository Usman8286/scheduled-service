package com.personal.reports;

import com.personal.dataaccess.StatusReportDA;

public class StatusReport implements Report {

	@Override
	public String generateCSVReport() {
		String header = "Order Status Report - " +  new java.util.Date() + " \n"
				+ "CUSTOMER_NAME, ORDER_ID, ORDER_STATUS \n";
		
		String csvString = null;
		try{
			
			StatusReportDA reportDA = new StatusReportDA();
			csvString = reportDA.getWorkflowReport();

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return header + csvString;
	}

}
