package com.pelletier.jira.plugins.data;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.pelletier.valuelist.ValueListService;
import com.pelletier.valuelist.DefaultValuesListService;
import com.pelletier.valuelist.DefaultValuesListService;
import com.pelletier.valuelist.ValueListService;
/*
 * Dependent on a list of queries, which will be run by the jdbcTemplate object.
 * 
 * Author: Ryan Pelletier
 */

/**
 * 
 * @author 578993
 *
 *Ight so this is going to be changed, we are going to inject the valuelist service in here and
 *then tell it what to do based on that, return our Values<> object, then somehow the view is oing
 *to have to dynamically create a table...it'll work
 */
public class JdbcResultsDAO implements ResultsDAO {

	private List<String> queries;
	private JdbcTemplate jdbcTemplate;
	

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

	
	//This function should do all the XML reading and stuff
	public void setDbconfigLocation(String dbconfigLocation) {
		
		ValueListService valueListService = new DefaultValuesListService<>();
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
}
