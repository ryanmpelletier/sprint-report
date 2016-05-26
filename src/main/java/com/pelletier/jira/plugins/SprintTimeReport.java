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

	public SprintTimeReport(ResultsDAO sprintReportDAO) {
		this.sprintReportDAO = sprintReportDAO;
		System.out.println(sprintReportDAO);
	}
	
	public String generateReportHtml(ProjectActionSupport projectActionSupport, Map params) throws Exception {
		Map<String, Object> velocityParams = new HashMap<String, Object>();
		velocityParams.put("results", sprintReportDAO.getResults(new Object[]{(String) params.get("selectedProjectId")},0));
		return descriptor.getHtml("view", velocityParams);
	}

}
