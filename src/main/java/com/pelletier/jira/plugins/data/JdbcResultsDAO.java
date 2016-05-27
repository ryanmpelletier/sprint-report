package com.pelletier.jira.plugins.data;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
/*
 * Dependent on a list of queries, which will be run by the jdbcTemplate object.
 * 
 * Author: Ryan Pelletier
 */


public class JdbcResultsDAO implements ResultsDAO {

	private List<String> queries;
	private JdbcTemplate jdbcTemplate;
	private String dbconfigLocation;
	

	@Override
	public List<Map<String, Object>> getResults(Object[] params, int queryNumber) {
		List<Map<String,Object>> results = null;
		try{
			results = jdbcTemplate.queryForList(queries.get(queryNumber), params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return results;
	}
	

	public void setQueries(List<String> queries) {
		this.queries = queries;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	

	public void setDbconfigLocation(String dbconfigLocation) {
		this.dbconfigLocation = dbconfigLocation;
	}

	public String getDbconfigLocation() {
		return dbconfigLocation;
	}

}
