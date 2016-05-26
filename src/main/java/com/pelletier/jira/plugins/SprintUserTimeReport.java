package com.pelletier.jira.plugins;

import java.util.HashMap;
import java.util.Map;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import com.atlassian.jira.web.action.ProjectActionSupport;
import com.pelletier.jira.plugins.data.ResultsDAO;

/*
 * Queries for List<Map<String,Object>>, so essentially a list of "rows" 
 * where each row is represented by a Map, and each column value of the row is accessed by the String key
 * 
 * Author: Ryan Pelletier
 */


public class SprintUserTimeReport extends AbstractReport {
	
	private ResultsDAO sprintUserReportDAO;
	
	public SprintUserTimeReport(ResultsDAO sprintUserReportDAO){
		this.sprintUserReportDAO = sprintUserReportDAO;
		System.out.println(sprintUserReportDAO);
	}	
	
    public String generateReportHtml(ProjectActionSupport projectActionSupport, Map params) throws Exception {
	
    	String selectedProjectId = (String) params.get("selectedProjectId");
    	
    	//if left blank, report configuration properties are the empty string, would like them to be null
    	String selectedUser = ((String) params.get("user")).equals("") ? null : (String) params.get("user") ;
    	String selectedSprint = ((String) params.get("sprint")).equals("") ? null : (String) params.get("sprint");
    	
		Map<String, Object> velocityParams = new HashMap<String, Object>();
		velocityParams.put("results", sprintUserReportDAO.getResults(new Object[]{selectedProjectId, selectedSprint ,selectedUser},1));
		return descriptor.getHtml("view", velocityParams);
    }
}
