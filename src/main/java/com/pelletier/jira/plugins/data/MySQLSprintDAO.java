package com.pelletier.jira.plugins.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pelletier.jira.plugins.model.Sprint;

public class MySQLSprintDAO implements SprintDAO{
	
	private JdbcTemplate jdbcTemplate;
	private final String query = "SELECT AUTHOR as User, sum(timeworked) as TimeWorked FROM worklog WHERE STARTDATE > (select START_DATE from ao_60db71_sprint order by START_DATE DESC) GROUP BY AUTHOR ORDER BY TimeWorked DESC";

	@Override
	public List<Sprint> getSprints() {
		
		List<Sprint> sprints = new ArrayList<>();
		try{
			jdbcTemplate.query(query, new RowMapper<Sprint>(){

				@Override
				public Sprint mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
					/*
					 * extract the sprint data from the result set
					 */
					
					return new Sprint();
				}
				
			});
		}catch(Exception e){
			
		}
		return sprints;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

}
