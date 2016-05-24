package com.pelletier.jira.plugins;

import com.atlassian.jira.plugin.report.impl.AbstractReport;
import java.sql.*;
import com.atlassian.jira.web.action.ProjectActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class SprintTimeReport extends AbstractReport {
	
	
  private JdbcTemplate jdbcTemplate;
  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  static final String DB_URL = "jdbc:mysql://localhost/jiradb";

  //  Database credentials
  static final String USER = "root";
  static final String PASS = "test";
  
  public SprintTimeReport(JdbcTemplate jdbcTemplate){
	  this.jdbcTemplate = jdbcTemplate;
	  System.out.println(this.jdbcTemplate);
  }

   public String generateReportHtml(ProjectActionSupport projectActionSupport, Map map) throws Exception {
   	Connection connection = null;
   	Statement statement = null;
   	ResultSet resultSet = null;
   	StringBuilder stringBuilder = new StringBuilder();
   	List<UserTime> userTimes = new ArrayList<>();
   	
   	try{
   		Class.forName("com.mysql.jdbc.Driver");
   		connection = DriverManager.getConnection(DB_URL, USER, PASS);
   		statement = connection.createStatement();
   		resultSet = statement.executeQuery("SELECT AUTHOR as User, sum(timeworked) as TimeWorked FROM worklog WHERE STARTDATE > (select START_DATE from ao_60db71_sprint order by START_DATE DESC) GROUP BY AUTHOR ORDER BY TimeWorked DESC");
   		while(resultSet.next()){
   			userTimes.add(new UserTime(resultSet.getString("User"), resultSet.getInt("TimeWorked")));
   		}
   		
   	}catch(Exception e){
   		e.printStackTrace();
   		stringBuilder.append(e.getMessage());
   	}
   	
   	Map<String, Object> velocityParams = new HashMap<String, Object>();
   	velocityParams.put("userTimes",userTimes);
    return descriptor.getHtml("view", velocityParams);
   }
   
   
   public class UserTime{
	   
	   public String name;
	   public int time;
	   
	   
	   public UserTime(String name, int time){
		   this.name = name;
		   this.time = time;
	   }
	   
	   public String getTime(){
		   Integer hm = new Integer(time);
		   return ((hm/60) + "hr" + (hm % 60) + "m ");
	   }
	   
	   public String getName(){
		   return this.name;
	   }
   }
}
