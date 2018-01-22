package org.notice.beans;

public class EndorsementsPerSkill
{
	int skillId = 0;
	long numResources = 0L;
	String skillName = null;
	double numEndorsements = 0.0, avgEndorsement = 0.0;
	
	public EndorsementsPerSkill(int skillId, long numResources, String skillName, double numEndorsements, double avgEndorsement)
	{
		this.skillId = skillId;
		this.numResources = numResources;
		this.skillName = skillName;
		this.numEndorsements = numEndorsements;
		this.avgEndorsement = avgEndorsement;
	}
	
	public EndorsementsPerSkill(int skillId)
	{
		this(skillId, 0, " ", 0.0, 0.0);
	}

	public int getSkillId()
	{
		return skillId;
	}

	public long getNumResources()
	{
		return numResources;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public double getNumEndorsements()
	{
		return numEndorsements;
	}

	public double getAvgEndorsement()
	{
		return avgEndorsement;
	}

	public void setSkillId(int skillId)
	{
		this.skillId = skillId;
	}

	public void setNumResources(long numResources)
	{
		this.numResources = numResources;
	}

	public void setSkillName(String skillName)
	{
		this.skillName = skillName;
	}

	public void setNumEndorsements(double numEndorsements)
	{
		this.numEndorsements = numEndorsements;
	}

	public void setAvgEndorsement(double avgEndorsement)
	{
		this.avgEndorsement = avgEndorsement;
	}

	@Override
	public String toString()
	{
		return "EndorsementsPerSkill [skillId=" + skillId + ", numResources=" + numResources + ", skillName="
				+ skillName + ", numEndorsements=" + numEndorsements + ", avgEndorsement=" + avgEndorsement + "]";
	}
	
	
	
}
