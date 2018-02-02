package org.notice.beans;

import java.io.Serializable;

public class SkillDistribution implements Serializable {
	//SELECT `user_id`, `first_name`, `surname`, `alias_name`, `skill_name`, `skill_id`, `user_value` FROM `myskills`.`v_skill_distribution`;
	String userId ;
	String firstName ;
	String surname ; 
	String aliasName ; 
	String skillName ; 
	int skillId ; 
	Double userValue ;
	
	
	public SkillDistribution(String userId, String firstName, String surname, String aliasName, String skillName,
			int skillId, Double userValue) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.surname = surname;
		this.aliasName = aliasName;
		this.skillName = skillName;
		this.skillId = skillId;
		this.userValue = userValue;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public Double getUserValue() {
		return userValue;
	}
	public void setUserValue(Double userValue) {
		this.userValue = userValue;
	} 
	
	

}
