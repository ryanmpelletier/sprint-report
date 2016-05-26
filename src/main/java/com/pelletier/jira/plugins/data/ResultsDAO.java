package com.pelletier.jira.plugins.data;
/*
 * Interface for getting a list of "rows" from a data source. 
 * Unfortunately, it is not entirely implementation independent, as you can see by the
 * queryNumber parameter.
 * 
 * This is because multiple reports must use the same ResultsDAO object, as atlassian
 * will only instantiate and inject one instance of an interface into dependent classes,
 * which in this case are SprintUserTimeReport and SprintTimeReport.
 * 
 * The solution to this would be to create two separate plugin projects.
 * 
 * Author: Ryan Pelletier
 */
import java.util.List;
import java.util.Map;

public interface ResultsDAO {
	
	public List<Map<String,Object>> getResults(Object[] params, int queryNumber);
}
