package com.pelletier.jira.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import com.atlassian.jira.web.action.ProjectActionSupport;
import com.pelletier.jira.plugins.data.ResultsDAO;


public class SprintTimeReport extends AbstractReport {

	private ResultsDAO resultsDAO;

	public SprintTimeReport(ResultsDAO sprintDAO) {
		this.resultsDAO = sprintDAO;
		System.out.println(sprintDAO);
	}
	
	public String generateReportHtml(ProjectActionSupport projectActionSupport, Map params) throws Exception {
		List<Map<String,Object>> results = new ArrayList<>();
		results = resultsDAO.getResults((String) params.get("selectedProjectId"));
		Map<String, Object> velocityParams = new HashMap<String, Object>();
		velocityParams.put("results", results);
		return descriptor.getHtml("view", velocityParams);
	}

}
