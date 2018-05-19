package com.personal.testing;

import com.personal.processors.Processor;
import com.personal.processors.StatusProcess;

public class ProcessTest {

	public static void main(String[] args){

		Processor process = new StatusProcess();
		process.process();
		
	}
}
