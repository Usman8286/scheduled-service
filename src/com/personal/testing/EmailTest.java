package com.personal.testing;

import com.personal.outputs.EmailDelivery;

public class EmailTest {

	public static void main(String[] args) throws Exception{
		EmailDelivery email = new EmailDelivery();
		try {
			email.sendEmail("Test Subject", "Chalo Chalo Lahore Chalo", "C:/TestReport.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
