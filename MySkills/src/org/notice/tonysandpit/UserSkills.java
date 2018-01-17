package org.notice.tonysandpit;

import java.util.Date;

public class UserSkills
{
	private int skillID = 0, userSkillID = 0, level = 0;
	private String userID = null;
	private Date addedDate = null;
	
	public UserSkills(int userSkillID, String userID, int skillID, int level, Date addedDate)
	{
		this.userID = userID;
		this.userSkillID = userSkillID;
		this.skillID = skillID;
		this.level = level;
		this.addedDate = addedDate;
	}

	public int getSkillID()
	{
		return skillID;
	}

	public int getUserSkillID()
	{
		return userSkillID;
	}

	public int getLevel()
	{
		return level;
	}

	public String getUserID()
	{
		return userID;
	}

	public Date getAddedDate()
	{
		return addedDate;
	}

	public void setSkillID(int skillID)
	{
		this.skillID = skillID;
	}

	public void setUserSkillID(int userSkillID)
	{
		this.userSkillID = userSkillID;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public void setAddedDate(Date addedDate)
	{
		this.addedDate = addedDate;
	}

	@Override
	public String toString()
	{
		return "UserSkills [skillID=" + skillID + ", userSkillID=" + userSkillID + ", level=" + level + ", userID="
				+ userID + ", addedDate=" + addedDate + "]";
	}
	
	
	
}
