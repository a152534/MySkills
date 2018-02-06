package org.notice.beans;

import java.io.Serializable;

public class ColleagueRatings implements Serializable{
	String userId, skillName;
	int userSkillId, skillId, level, numOfEndorsements, myRating;
	double avgEndorsement;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNumOfEndorsements() {
		return numOfEndorsements;
	}

	public void setNumOfEndorsements(int numOfEndorsements) {
		this.numOfEndorsements = numOfEndorsements;
	}

	public int getMyRating() {
		return myRating;
	}

	public void setMyRating(int myRating) {
		this.myRating = myRating;
	}

	public double getAvgEndorsement() {
		return avgEndorsement;
	}

	public void setAvgEndorsement(double avgEndorsement) {
		this.avgEndorsement = avgEndorsement;
	}

	public ColleagueRatings(String userId, String skillName, int userSkillId, int skillId, int level,
			int numOfEndorsements, int myRating, double avgEndorsement) {
		super();
		this.userId = userId;

		this.skillName = skillName;

		this.userSkillId = userSkillId;
		this.skillId = skillId;
		this.level = level;
		this.numOfEndorsements = numOfEndorsements;
		this.myRating = myRating;
		this.avgEndorsement = avgEndorsement;
	}

}
