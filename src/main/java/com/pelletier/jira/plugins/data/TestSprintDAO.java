package com.pelletier.jira.plugins.data;

import java.util.ArrayList;
import java.util.List;

import com.pelletier.jira.plugins.model.Sprint;
import com.pelletier.jira.plugins.model.UserTime;

public class TestSprintDAO implements SprintDAO {

	@Override
	public List<Sprint> getSprints() {

		List<Sprint> sprints = new ArrayList<>();

		Sprint sprint1 = new Sprint();
		sprint1.setName("sprint1");

		Sprint sprint2 = new Sprint();
		sprint2.setName("sprint2");

		Sprint sprint3 = new Sprint();
		sprint3.setName("sprint3");

		Sprint sprint4 = new Sprint();
		sprint4.setName("sprint4");

		UserTime userTime1 = new UserTime("ryan", 60);
		UserTime userTime2 = new UserTime("mike", 120);
		UserTime userTime3 = new UserTime("matt", 180);
		UserTime userTime4 = new UserTime("ryan", 240);
		UserTime userTime5 = new UserTime("mike", 300);
		UserTime userTime6 = new UserTime("matt", 360);
		UserTime userTime7 = new UserTime("ryan", 420);
		UserTime userTime8 = new UserTime("mike", 480);
		UserTime userTime9 = new UserTime("matt", 540);
		UserTime userTime10 = new UserTime("ryan", 600);
		UserTime userTime11 = new UserTime("mike", 660);
		UserTime userTime12 = new UserTime("matt", 720);

		List<UserTime> userTimesList1 = new ArrayList<>();
		userTimesList1.add(userTime1);
		userTimesList1.add(userTime2);
		userTimesList1.add(userTime3);

		List<UserTime> userTimesList2 = new ArrayList<>();
		userTimesList2.add(userTime4);
		userTimesList2.add(userTime5);
		userTimesList2.add(userTime6);

		List<UserTime> userTimesList3 = new ArrayList<>();
		userTimesList3.add(userTime7);
		userTimesList3.add(userTime8);
		userTimesList3.add(userTime9);

		List<UserTime> userTimesList4 = new ArrayList<>();
		userTimesList4.add(userTime10);
		userTimesList4.add(userTime11);
		userTimesList4.add(userTime12);

		sprint1.setUserTimes(userTimesList1);
		sprint2.setUserTimes(userTimesList2);
		sprint3.setUserTimes(userTimesList3);
		sprint4.setUserTimes(userTimesList4);

		sprints.add(sprint1);
		sprints.add(sprint2);
		sprints.add(sprint3);
		sprints.add(sprint4);

		return sprints;
	}

}
