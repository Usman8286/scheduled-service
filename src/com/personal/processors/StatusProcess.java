package com.personal.processors;

import com.personal.outputs.EmailDelivery;
import com.personal.outputs.OutputNode;
import com.personal.reports.Report;
import com.personal.reports.StatusReport;
import com.personal.utils.CSVWritter;
import com.personal.utils.ConfigurationLoader;
import com.personal.utils.Utility;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StatusProcess implements Processor {

	private OutputNode ouput;

	@Override
	public void process() {
		Report report = new StatusReport();
		
		try {
			String csvReport = report.generateCSVReport();
			
			String currentDate = Utility.getCurrentDateString();
			
			String attachementPath = new ConfigurationLoader().getNodeValue("mail-temp-path");
			String name = "Workflow Report-" + currentDate;
			String filename =  name + ".csv";
			
			String csvPath = Paths.get(attachementPath, filename).toString();	
			
			CSVWritter.writeCSVString(csvReport, csvPath);
			
			//TODO: review if assigning output node shouldn't be a part of process
			setOuput(new EmailDelivery());
			Map<String, Object> datamap = new HashMap<String, Object>();
			
			datamap.put("subject", name);
			datamap.put("body", name);
			datamap.put("attachment", csvPath);
			
			getOuput().generateOutput(datamap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OutputNode getOuput() {
		return ouput;
	}

	public void setOuput(OutputNode ouput) {
		this.ouput = ouput;
	}

}
