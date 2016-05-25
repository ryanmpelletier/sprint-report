package com.pelletier.jira.plugins.data;

import java.util.List;
import java.util.Map;

public interface ResultsDAO {
	public List<Map<String,Object>> getResults(String projectID);
}
