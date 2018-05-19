package com.personal.dataaccess;

import java.sql.ResultSet;

import com.personal.utils.Utility;

public class DaysFromOrderDA extends DataAccess {

	public DaysFromOrderDA() throws Exception {
		super();
	}
	
	public String getDaysFromOrderReport() throws Exception{
		final String query = "SELECT DISTINCT JOBID, to_char(ORDER_DATE, 'MM/DD/YYYY') , CEIL(SYSDATE - ORDER_DATE) as DAYS, DOC_STATE FROM CUSTOMER_DETAILS";
		
		ResultSet rs = executeQuery(query);		
		return Utility.resultSetToCSV(rs);
	}
}
