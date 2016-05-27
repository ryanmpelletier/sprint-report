package com.pelletier.jira.plugins;

import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import com.pelletier.jira.plugins.data.DbConfigReader;
import com.pelletier.jira.plugins.data.JdbcResultsDAO;
import com.pelletier.jira.plugins.data.ResultsDAO;
/*
 * Author: Ryan Pelletier
 * 
 * This class is used to encapsulate common constructor logic for both SprintTimeReport and SprintUserTimeReport
 */
public abstract class TimeReport extends AbstractReport {
	
	protected ResultsDAO resultsDAO;
	
	public TimeReport(ResultsDAO resultsDAO){

		//only run code if we know ResultsDAO is of type JdbcResults DAO to prevent casting exception
		if(resultsDAO.getClass().equals(JdbcResultsDAO.class)){
			BasicDataSource basicDataSource = null;

			//only read xml file and create data source first time resultsDAO is injected
			//for whatever reason it looks like this class gets created multiple times, but is injected with the same ResultsDAO
			if(this.resultsDAO == null){
				
				DbConfigReader dbConfigReader = new DbConfigReader();
				basicDataSource = new BasicDataSource();
				this.resultsDAO = resultsDAO;
				
				//parse dbconfig.xml and set DataSource properties
				Map<String,String> dbInfo = dbConfigReader.getDbConnectionInfo(((JdbcResultsDAO) this.resultsDAO).getDbconfigLocation());
				if(dbInfo != null){
					basicDataSource.setUrl(dbInfo.get("url"));
					basicDataSource.setDriverClassName(dbInfo.get("driverClass"));
					basicDataSource.setUsername(dbInfo.get("username"));
					basicDataSource.setPassword(dbInfo.get("password"));
				}
			}
			((JdbcResultsDAO) this.resultsDAO).setJdbcTemplate(new JdbcTemplate(basicDataSource));
		}else{
			this.resultsDAO = resultsDAO;
		}
	}
}
