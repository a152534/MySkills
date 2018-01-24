package org.notice.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class EndorsementsPerSkill implements Serializable
{
	int skillId = 0;
	long numResources = 0L;
	String skillName = null;
	BigDecimal numEndorsements = null, avgEndorsement = null;
	
	public EndorsementsPerSkill(int skillId, long numResources, String skillName, BigDecimal numEndorsements, BigDecimal avgEndorsement)
	{
		this.skillId = skillId;
		this.numResources = numResources;
		this.skillName = skillName;
		this.numEndorsements = numEndorsements;
		this.avgEndorsement = avgEndorsement;
	}
	
	public EndorsementsPerSkill(int skillId)
	{
		this(skillId, 0, " ", null, null);
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

	public BigDecimal getNumEndorsements()
	{
		return numEndorsements;
	}

	public BigDecimal getAvgEndorsement()
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

	public void setNumEndorsements(BigDecimal numEndorsements)
	{
		this.numEndorsements = numEndorsements;
	}

	public void setAvgEndorsement(BigDecimal avgEndorsement)
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
