package com.personal.processors;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.personal.outputs.EmailDelivery;
import com.personal.outputs.OutputNode;
import com.personal.reports.AgingReport;
import com.personal.utils.CSVWritter;
import com.personal.utils.ConfigurationLoader;
import com.personal.utils.Utility;

public class DaysFromOrderProcess implements Processor{
	
	private OutputNode ouput;
	
	public DaysFromOrderProcess() {
		
	}

	@Override
	public void process() {
		AgingReport report = new AgingReport();
		
		try {
			String csvReport = report.generateCSVReport();

			String currentDate = Utility.getCurrentDateString();
			
			String attachementPath = new ConfigurationLoader().getNodeValue("preprod-mail-temp-path");
			String name = "Order Days - " + currentDate;
			String filename = name + ".csv";
			
			String csvPath = Paths.get(attachementPath, filename).toString();			
			
			CSVWritter.writeCSVString(csvReport, csvPath);
			
			//TODO: review if assigning output node shouldn't be a part of process
//			setOuput(new EmailDelivery(new ConfigurationLoader()));
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
