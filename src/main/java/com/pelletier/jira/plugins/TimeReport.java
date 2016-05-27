package com.pelletier.jira.plugins;

import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import com.pelletier.jira.plugins.data.DbConfigReader;
import com.pelletier.jira.plugins.data.JdbcResultsDAO;
import com.pelletier.jira.plugins.data.ResultsDAO;

public abstract class TimeReport extends AbstractReport {
	
	protected ResultsDAO resultsDAO;

	
	public TimeReport(ResultsDAO resultsDAO){
		BasicDataSource basicDataSource = null;

		if(this.resultsDAO == null){
			DbConfigReader dbConfigReader = new DbConfigReader();
			basicDataSource = new BasicDataSource();
			Map<String,String> dbInfo = dbConfigReader.getDbConnectionInfo();
			if(dbInfo != null){
				basicDataSource.setUrl(dbInfo.get("url"));
				basicDataSource.setDriverClassName(dbInfo.get("driverClass"));
				basicDataSource.setUsername(dbInfo.get("username"));
				basicDataSource.setPassword(dbInfo.get("password"));
			}
		}
		this.resultsDAO = resultsDAO;
		((JdbcResultsDAO) this.resultsDAO).setJdbcTemplate(new JdbcTemplate(basicDataSource));
	}
}
