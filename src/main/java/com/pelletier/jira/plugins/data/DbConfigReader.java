package com.pelletier.jira.plugins.data;

import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DbConfigReader {

	private String DB_CONFIG_LOCATION = "../Application Data/JIRA/dbconfig.xml";

	public Map<String, String> getDbConnectionInfo() {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DbConfigHandler dbConfigHandler = new DbConfigHandler();
			
			saxParser.parse(DB_CONFIG_LOCATION, dbConfigHandler);
			return dbConfigHandler.getInfo();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
