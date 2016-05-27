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

public class SprintTimeReport extends AbstractReport {

	private ResultsDAO sprintReportDAO;

	private final int SPRINT_TIME_REPORT_QUERY = 0;
	//Since there is a ResultsDAO bean defined in atlassian-spring.xml, it will be injected here
	public SprintTimeReport(ResultsDAO sprintReportDAO) {
		this.sprintReportDAO = sprintReportDAO;
	}
	
	//Map params contains the parameters such as the project id as well as things the user enters before running the report
	public String generateReportHtml(ProjectActionSupport projectActionSupport, Map params) throws Exception {
		Map<String, Object> velocityParams = new HashMap<String, Object>();
		velocityParams.put("results", sprintReportDAO.getResults(new Object[]{(String) params.get("selectedProjectId")},SPRINT_TIME_REPORT_QUERY));
		return descriptor.getHtml("view", velocityParams);
	}

}
