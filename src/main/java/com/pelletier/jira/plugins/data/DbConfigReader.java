package com.pelletier.jira.plugins.data;

import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DbConfigReader {


	public Map<String, String> getDbConnectionInfo(String dbconfigLocation) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DbConfigHandler dbConfigHandler = new DbConfigHandler();
			
			saxParser.parse(dbconfigLocation, dbConfigHandler);
			return dbConfigHandler.getInfo();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
