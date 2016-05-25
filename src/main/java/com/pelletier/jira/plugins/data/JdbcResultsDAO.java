package com.pelletier.jira.plugins.data;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcResultsDAO implements ResultsDAO {

	private String query;
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Map<String, Object>> getResults(String projectID) {
		List<Map<String,Object>> results = null;
		try{
			System.out.println("QUERYING");
			results = jdbcTemplate.queryForList(query, new Object[]{projectID});
		}catch(Exception e){
			e.printStackTrace();
		}
		return results;
	}
	

	public void setQuery(String query) {
		this.query = query;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
