package com.pelletier.jira.plugins;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import java.sql.*;
import com.atlassian.jira.web.action.ProjectActionSupport;
import com.pelletier.jira.plugins.data.SprintDAO;
import com.pelletier.jira.plugins.model.Sprint;
import com.pelletier.jira.plugins.model.UserTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SprintTimeReport extends AbstractReport {

	/*
	 * This will ultimately get a list of sprints from the injected DAO, which in
	 * turn have a list of UserTimes 3. Pass that information to the view.
	 */

	private SprintDAO sprintDAO;

	public SprintTimeReport(SprintDAO sprintDAO) {
		this.sprintDAO = sprintDAO;
		System.out.println(sprintDAO);
	}

	
	
	
	public String generateReportHtml(ProjectActionSupport projectActionSupport, Map params) throws Exception {

		//here is how to get the project id!
		System.out.println(params.get("selectedProjectId"));
		
		List<Sprint> sprints = new ArrayList<>();
		sprints = sprintDAO.getSprints();
		Map<String, Object> velocityParams = new HashMap<String, Object>();
		velocityParams.put("sprints", sprints);
		return descriptor.getHtml("view", velocityParams);
	}

}
