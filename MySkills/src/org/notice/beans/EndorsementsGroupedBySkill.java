package org.notice.beans;

public class EndorsementsGroupedBySkill
{
	int skillId = 0;
	String skillName = null;
	double avgRating = 0.0, numRating = 0.0;
	
	public EndorsementsGroupedBySkill(int skillId, String skillName, double avgRating, double numRating)
	{
		this.skillId = skillId;
		this.skillName = skillName;
		this.avgRating = avgRating;
		this.numRating = numRating;
	}
	
	public EndorsementsGroupedBySkill(int skillId)
	{
		this(skillId, " ", 0.0, 0.0);
	}

	public int getSkillId()
	{
		return skillId;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public double getAvgRating()
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

	public void setAvgRating(double avgRating)
	{
		this.avgRating = avgRating;
	}

	public void setNumRating(double numRating)
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
