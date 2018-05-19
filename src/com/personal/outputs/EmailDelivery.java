package com.personal.outputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.personal.utils.ConfigurationLoader;
import com.sun.mail.smtp.SMTPTransport;

public class EmailDelivery implements OutputNode {
	private String host;
	private String port;
	private String user;
	private String domain;
	private String password;
	
	List<String> recipients;
	
	
	public EmailDelivery() throws Exception {
		ConfigurationLoader loader = new ConfigurationLoader();

		host = loader.getNodeAttribValue("smtp-settings", "host");
		port = loader.getNodeAttribValue("smtp-settings", "port");
		user = loader.getNodeAttribValue("smtp-settings", "user");
		domain = loader.getNodeAttribValue("smtp-settings", "domain");
		password = loader.getNodeAttribValue("smtp-settings", "password");
		String[] list = loader.getNodeValue("recipients").split(Pattern.quote("|"));
		recipients = new ArrayList<>(Arrays.asList(list));
	}

	@Override
	public void generateOutput(Map<String, Object> datamap) {
		String subject = (String) datamap.get("subject");
		String body = (String) datamap.get("body");
		String attachment = (String) datamap.get("attachment");
		
		try {
			sendEmail(subject, body, attachment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Session getSession(){
		
		Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "false");
        
        return Session.getInstance(props, null);
	}
	
	private InternetAddress[] getRecipientsAddress(List<String> recipients) throws Exception {
		int recipientsSize = recipients.size();
		InternetAddress[] addressTo = new InternetAddress[recipientsSize];

		for (int j = 0; j < recipientsSize; j++) {
			addressTo[j] = new InternetAddress(recipients.get(j).toString());
		}
		return addressTo;
	}
	
	public void sendEmail(String subject, String body, String attachment) throws Exception {
		
		Session session = getSession();
		
		MimeMessage message = new MimeMessage(session);
		
		message.setSubject(subject);
		message.setFrom(new InternetAddress(user + "@" + domain));
		message.setRecipients(Message.RecipientType.TO, getRecipientsAddress(recipients));
		
		message.setHeader("X-Mailer", "smtpsend");
		message.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(body, "text/html");
        messageBodyPart.setText(body);
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (attachment != null) {
            messageBodyPart = new MimeBodyPart();

            DataSource attachementSource = new FileDataSource(attachment);

            messageBodyPart.setDataHandler(new DataHandler(attachementSource));
            messageBodyPart.setFileName(attachementSource.getName());
            multipart.addBodyPart(messageBodyPart);
        }
        
        message.setContent(multipart);

        SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
		System.out.println("Message is ready");
		
        try {
            t.connect(host, user, password);
            t.sendMessage(message, message.getAllRecipients());

    		System.out.println("Email Sent Successfully!!");
        } finally {
            t.close();
        }
	}
}
