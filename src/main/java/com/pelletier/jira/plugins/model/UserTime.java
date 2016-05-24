package com.pelletier.jira.plugins.model;


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
