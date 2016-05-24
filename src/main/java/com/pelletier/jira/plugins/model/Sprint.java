package com.pelletier.jira.plugins.model;

import java.util.ArrayList;
import java.util.List;

public class Sprint {
	
	private String name;
	private List<UserTime> userTimes;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserTime> getUserTimes() {
		if(userTimes == null){
			userTimes = new ArrayList<>();
		}
		return userTimes;
	}

	public void setUserTimes(List<UserTime> userTimes) {
		this.userTimes = userTimes;
	}

	
}
