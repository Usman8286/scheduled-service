package com.personal.services;

import java.util.List;

import com.personal.processors.DaysFromOrderProcess;
import com.personal.processors.Processor;
import com.personal.processors.StatusProcess;

public class SchedulerService implements Service {
	
	List<Processor> processors;
	
	public SchedulerService() {
		processors.add(new DaysFromOrderProcess());
		processors.add(new StatusProcess());
	}
	
	@Override
	public void start(){
		for(Processor processor : processors){
			try {
				processor.process();				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void stop(){
		
	}

}
