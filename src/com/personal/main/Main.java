package com.personal.main;

import java.util.ArrayList;
import java.util.List;

import com.personal.services.SchedulerService;
import com.personal.services.Service;

public class Main {

	public static void main(String[] args) throws Exception {
		
		List<Service> services = new ArrayList<Service>();
		
		services.add(new SchedulerService());
		
		for(Service service : services) {
			service.start();
		}		
	}

}
