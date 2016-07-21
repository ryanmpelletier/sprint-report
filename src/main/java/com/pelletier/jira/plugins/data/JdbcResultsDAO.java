package com.pelletier.jira.plugins.data;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
/*
 * Dependent on a list of queries, which will be run by the jdbcTemplate object.
 * 
 * Author: Ryan Pelletier
 */


public class JdbcResultsDAO implements ResultsDAO {

	private Map<String,String> queries;
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Map<String, Object>> getResults(Object[] params, String queryKey) {
		List<Map<String,Object>> results = null;
		try{
			results = jdbcTemplate.queryForList(queries.get(queryKey), params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return results;
	}
	
	
	
	//This function should do all the XML reading and stuff
	public void setDbconfigLocation(String dbconfigLocation) {
		
		
		//unfortunately right now we rely on BasicDataSource because we can create it with url, driverClass, username, and password
		//it would be better probably to use an interface with a createDataSource method, be able to write and inject an implementation
		//that gets us the DataSource, I will ask about that later
		BasicDataSource basicDataSource = new BasicDataSource();
		
		DbConfigReader dbConfigReader = new DbConfigReader();
		
		//parse dbconfig.xml and set DataSource properties
		Map<String,String> dbInfo = dbConfigReader.getDbConnectionInfo(dbconfigLocation);
		if(dbInfo != null){
			basicDataSource.setUrl(dbInfo.get("url"));
			basicDataSource.setDriverClassName(dbInfo.get("driverClass"));
			basicDataSource.setUsername(dbInfo.get("username"));
			basicDataSource.setPassword(dbInfo.get("password"));
			this.jdbcTemplate = new JdbcTemplate(basicDataSource);
		}
	}


	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}





	public void setQueries(Map<String, String> queries) {
		this.queries = queries;
	}





	public Map<String, String> getQueries() {
		return queries;
	}
	
	
	
	
}
