package org.notice.beans;

import java.io.Serializable;

public class SkillRatingDistribution implements Serializable {
	private int skillId ;
	private String skillName ;
	private int userSkillId ;
	private double averageRating ;
	
	
	
	public SkillRatingDistribution(int skillId, String skillName, int userSkillId, double averageRating) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.userSkillId = userSkillId;
		this.averageRating = averageRating;
	}
	
	
	
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getUserSkillId() {
		return userSkillId;
	}
	public void setUserSkillId(int userSkillId) {
		this.userSkillId = userSkillId;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	} 
	
	
}
