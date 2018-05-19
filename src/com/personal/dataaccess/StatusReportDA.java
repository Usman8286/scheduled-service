package com.personal.dataaccess;

import java.sql.ResultSet;

import com.personal.utils.Utility;

public class StatusReportDA extends DataAccess {

	public StatusReportDA() throws Exception {
		super();
	}

	public String getWorkflowReport() throws Exception {
		final String query = "SELECT CUSTOMER_NAME, ORDER_ID, ORDER_STATUS FROM CUSTOMER_DETAILS ";

		ResultSet rs = executeQuery(query);

		// HACK: to calculate total records
		String csv = Utility.resultSetToCSV(rs);
		String lines[] = csv.split("\\r?\\n");
		csv += "\nTotal Documents: " + lines.length;

		return csv;
	}
}
