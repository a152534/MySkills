package org.notice.beans;

import java.util.ArrayList;

import org.notice.client.SkillClient;

public class CommonStuff {
	public CommonStuff(User loggedOnUser, SkillClient client, ArrayList<Skill> skillsList) {
		super();
		this.loggedOnUser = loggedOnUser;
		this.client = client;
		this.skillsList = skillsList;
	}
	public CommonStuff() {
		this(null, null, null) ; 
	}
	User loggedOnUser ; 
	SkillClient client ; 
	ArrayList<Skill>  skillsList;
	public User getLoggedOnUser() {
		return loggedOnUser;
	}
	public void setLoggedOnUser(User loggedOnUser) {
		this.loggedOnUser = loggedOnUser;
	}
	public SkillClient getClient() {
		return client;
	}
	public void setClient(SkillClient client) {
		this.client = client;
	}
	public ArrayList<Skill> getSkillsList() {
		return skillsList;
	}
	public void setSkillsList(ArrayList<Skill> skillsList) {
		this.skillsList = skillsList;
	}
	
	

}
