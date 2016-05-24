package com.pelletier.jira.plugins.data;

import java.util.List;

import com.pelletier.jira.plugins.model.Sprint;

public interface SprintDAO {
	public List<Sprint> getSprints();
}
