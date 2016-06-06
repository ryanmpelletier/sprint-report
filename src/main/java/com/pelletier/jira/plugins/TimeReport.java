package com.pelletier.jira.plugins;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import com.pelletier.jira.plugins.data.ResultsDAO;
/*
 * Author: Ryan Pelletier
 * 
 * This class is used to encapsulate common constructor logic for both SprintTimeReport and SprintUserTimeReport
 */
public abstract class TimeReport extends AbstractReport {
	
	protected ResultsDAO resultsDAO;
	
	//all of this constructor code needs to go in the jdbcResultsDAO
	public TimeReport(ResultsDAO resultsDAO){
		this.resultsDAO = resultsDAO;
	}
}
