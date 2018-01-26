package org.notice.beans;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import org.notice.client.SkillClient;

public class CommonStuff {

	private User loggedOnUser;
	private SkillClient client;
	private ArrayList<Skill> skillsList;
	private JTabbedPane tabbedPane;
	private User colleague ; 
	private Skill skill ; 
	
	public CommonStuff(User loggedOnUser, SkillClient client, ArrayList<Skill> skillsList) {
		super();
		this.loggedOnUser = loggedOnUser;
		this.client = client;
		this.skillsList = skillsList;
	}

	public CommonStuff() {
		this(null, null, null);
	}

	

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

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public User getColleague() {
		return colleague;
	}

	public void setColleague(User colleague) {
		this.colleague = colleague;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}
