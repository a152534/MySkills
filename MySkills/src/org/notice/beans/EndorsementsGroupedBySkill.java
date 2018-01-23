package org.notice.beans;

import java.math.BigDecimal;

public class EndorsementsGroupedBySkill
{
	int skillId = 0;
	String skillName = null;
	BigDecimal avgRating = null;
	int numRating = 0;
	
	public EndorsementsGroupedBySkill(int skillId, String skillName, BigDecimal avgRating, int numRating)
	{
		this.skillId = skillId;
		this.skillName = skillName;
		this.avgRating = avgRating;
		this.numRating = numRating;
	}
	
	public EndorsementsGroupedBySkill(int skillId)
	{
		this(skillId, " ", null, 0);
	}

	public int getSkillId()
	{
		return skillId;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public BigDecimal getAvgRating()
	{
		return avgRating;
	}

	public double getNumRating()
	{
		return numRating;
	}

	public void setSkillId(int skillId)
	{
		this.skillId = skillId;
	}

	public void setSkillName(String skillName)
	{
		this.skillName = skillName;
	}

	public void setAvgRating(BigDecimal avgRating)
	{
		this.avgRating = avgRating;
	}

	public void setNumRating(int numRating)
	{
		this.numRating = numRating;
	}

	@Override
	public String toString()
	{
		return "EndorsementBySkill [skillId=" + skillId + ", skillName=" + skillName + ", avgRating=" + avgRating
				+ ", numRating=" + numRating + "]";
	}
	
	
}
