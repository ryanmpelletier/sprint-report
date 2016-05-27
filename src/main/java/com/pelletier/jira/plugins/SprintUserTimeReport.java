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
	
	private final int SPRINT_USER_TIME_REPORT_QUERY = 1;
	
	//Since there is a ResultsDAO bean defined in atlassian-spring.xml, it will be injected here
	public SprintUserTimeReport(ResultsDAO sprintUserReportDAO){
		this.sprintUserReportDAO = sprintUserReportDAO;
	}	
	
	//Map params contains the parameters such as the project id as well as things the user enters before running the report
    public String generateReportHtml(ProjectActionSupport projectActionSupport, Map params) throws Exception {
	
    	String selectedProjectId = (String) params.get("selectedProjectId");
    	
    	//if left blank, report configuration properties are the empty string, we would like them to be null (for our query)
    	String selectedUser = ((String) params.get("user")).equals("") ? null : (String) params.get("user") ;
    	String selectedSprint = ((String) params.get("sprint")).equals("") ? null : (String) params.get("sprint");
    	
		Map<String, Object> velocityParams = new HashMap<String, Object>();
		velocityParams.put("results", sprintUserReportDAO.getResults(new Object[]{selectedProjectId, selectedSprint ,selectedUser},SPRINT_USER_TIME_REPORT_QUERY));
		return descriptor.getHtml("view", velocityParams);
    }
}
