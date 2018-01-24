package org.notice.beans;

import java.math.BigDecimal;

public class UserSkillEndorsements
{
	String userId = null, firstName = null, surname = null, alias = null, eMail = null, phoneNum = null, skillName = null;
	int userSkillId = 0, skillId = 0, level = 0;
	Long numOfEndorsements = null;
	BigDecimal avgEndorsement = null;
	
	public UserSkillEndorsements(String userId, String firstName, String surname, String alias, String eMail,
			String phoneNum, String skillName, int userSkillId, int skillId, int level, Long numOfEndorsements,
			BigDecimal avgEndorsement)
	{
		this.userId = userId;
		this.firstName = firstName;
		this.surname = surname;
		this.alias = alias;
		this.eMail = eMail;
		this.phoneNum = phoneNum;
		this.skillName = skillName;
		this.userSkillId = userSkillId;
		this.skillId = skillId;
		this.level = level;
		this.numOfEndorsements = numOfEndorsements;
		this.avgEndorsement = avgEndorsement;
	}
	
	public UserSkillEndorsements( String firstName, String surname,  long numOfEndorsements,BigDecimal avgEndorsement)
	{
	
	this.firstName = firstName;
	this.surname = surname;
	this.numOfEndorsements = numOfEndorsements;
	this.avgEndorsement = avgEndorsement;
	}

	public UserSkillEndorsements(String userId)
	{
		this(userId, " ", " ", " ", " ", " ", " ", 0, 0, 0, 0L, null);
	}

	public String getUserId()
	{
		return userId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getSurname()
	{
		return surname;
	}

	public String getAlias()
	{
		return alias;
	}

	public String geteMail()
	{
		return eMail;
	}

	public String getPhoneNum()
	{
		return phoneNum;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public int getUserSkillId()
	{
		return userSkillId;
	}

	public int getSkillId()
	{
		return skillId;
	}

	public int getLevel()
	{
		return level;
	}

	public long getNumOfEndorsements()
	{
		return numOfEndorsements;
	}

	public BigDecimal getAvgEndorsement()
	{
		return avgEndorsement;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public void seteMail(String eMail)
	{
		this.eMail = eMail;
	}

	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	public void setSkillName(String skillName)
	{
		this.skillName = skillName;
	}

	public void setUserSkillId(int userSkillId)
	{
		this.userSkillId = userSkillId;
	}

	public void setSkillId(int skillId)
	{
		this.skillId = skillId;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public void setNumOfEndorsements(long numOfEndorsements)
	{
		this.numOfEndorsements = numOfEndorsements;
	}

	public void setAvgEndorsement(BigDecimal avgEndorsement)
	{
		this.avgEndorsement = avgEndorsement;
	}

	@Override
	public String toString()
	{
		return "UserSkillEndorsements [userId=" + userId + ", firstName=" + firstName + ", surname=" + surname
				+ ", alias=" + alias + ", eMail=" + eMail + ", phoneNum=" + phoneNum + ", skillName=" + skillName
				+ ", userSkillId=" + userSkillId + ", skillId=" + skillId + ", level=" + level + ", numOFEndorsements="
				+ numOfEndorsements + ", avgEndorsement=" + avgEndorsement + "]";
	}
	
	
}
