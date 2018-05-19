package com.personal.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;


public class ConfigurationLoader {
	
	//TODO: Review: should we use POJOs? considering this template can be used for
	// multiple services and every time new set of classes will have to be generated
	private Document doc;
	private String servicePath = "/services/schedular-service/";
	
	//TODO: DRY principle not being followed for the constructor
	public ConfigurationLoader() throws Exception {
		InputStream input = ConfigurationLoader.class.getClassLoader().getResourceAsStream("services-conf.xml");
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			setDoc(builder.parse(input));
		} finally {
			input.close();
		}
	}
	
	public ConfigurationLoader(String path) throws Exception {
		InputStream input = new FileInputStream(path);
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			setDoc(builder.parse(input));
		} finally {
			input.close();
		}
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	} 
	
	public String getNodeValue(String nodeName) throws Exception{
		String nodeExpression = servicePath + nodeName;
		System.out.println(nodeExpression);
		XPath xpath = XPathFactory.newInstance().newXPath();
	    XPathExpression expr = xpath.compile(nodeExpression);
	    return (String) expr.evaluate(getDoc(), XPathConstants.STRING);
	}
	
	public String getNodeAttribValue(String nodeName, String attribName) throws XPathExpressionException {
		String nodeExpression = servicePath + nodeName + "/@" + attribName;
		XPath xpath = XPathFactory.newInstance().newXPath();
	    XPathExpression expr = xpath.compile(nodeExpression);
	    return (String) expr.evaluate(getDoc(), XPathConstants.STRING);
	}
	
	
}
