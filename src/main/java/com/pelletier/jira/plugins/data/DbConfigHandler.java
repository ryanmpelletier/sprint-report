package com.pelletier.jira.plugins.data;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DbConfigHandler extends DefaultHandler {
	boolean isUrl = false,
			isDriverClass = false,
			isUsername = false,
			isPassword = false;
	
	Map<String,String> info = new HashMap<String,String>();
			
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		isUrl = (qName.equalsIgnoreCase("URL"));
		isDriverClass = (qName.equalsIgnoreCase("DRIVER-CLASS"));
		isUsername = (qName.equalsIgnoreCase("USERNAME"));
		isPassword = (qName.equalsIgnoreCase("PASSWORD"));
	}			
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(isUrl){
			info.put("url", new String(ch,start,length) );
			isUrl = false;
		}
		if(isDriverClass){
			info.put("driverClass", new String(ch,start,length));
			isDriverClass = false;
		}
		if(isUsername){
			info.put("username", new String(ch,start,length));
			isUsername = false;
		}
		if(isPassword){
			info.put("password", new String(ch,start,length));
			isPassword = false;
		}
	}

	public Map<String, String> getInfo() {
		return info;
	}
	
	
}
