package com.pelletier.jira.plugins.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pelletier.jira.plugins.model.Sprint;

public class JdbcSprintDAO implements SprintDAO {

	private String query;
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Sprint> getSprints() {

		return jdbcTemplate.query(query, new RowMapper<Sprint>() {

			@Override
			public Sprint mapRow(ResultSet rs, int rowNum) throws SQLException {
				/*
				 * extract the sprint data from the result set
				 */
				return null;
			}

		});
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
