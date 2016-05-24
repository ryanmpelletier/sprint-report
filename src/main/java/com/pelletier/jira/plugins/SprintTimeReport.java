package com.pelletier.jira.plugins;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import java.sql.*;
import com.atlassian.jira.web.action.ProjectActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SprintTimeReport extends AbstractReport {
	
	
  private JdbcTemplate jdbcTemplate;

  final String query = "SELECT AUTHOR as User, sum(timeworked) as TimeWorked FROM worklog WHERE STARTDATE > (select START_DATE from ao_60db71_sprint order by START_DATE DESC) GROUP BY AUTHOR ORDER BY TimeWorked DESC";
  
  public SprintTimeReport(JdbcTemplate jdbcTemplate){
	  this.jdbcTemplate = jdbcTemplate;
  }

   public String generateReportHtml(ProjectActionSupport projectActionSupport, Map map) throws Exception {

   	List<UserTime> userTimes = new ArrayList<>();

   	try{
   	    userTimes = jdbcTemplate.query(query, new RowMapper<UserTime>(){

   		@Override
   		public UserTime mapRow(ResultSet rs, int rowNum) throws SQLException {
   			return new UserTime(rs.getString("User"), rs.getInt("TimeWorked"));
   		}
   		   
   	   });
   	}catch(Exception e){
   		e.printStackTrace();
   	}

   	
   	Map<String, Object> velocityParams = new HashMap<String, Object>();
   	velocityParams.put("userTimes",userTimes);
    return descriptor.getHtml("view", velocityParams);
   }
   
}
