package org.notice.beans;

import java.math.BigDecimal;
import java.util.Date;

public class RatedSkills
{
	private int skillId = 0, userSkillId = 0, level = 0 , numEndorsement = 0;
	private String userId = null , SkillName = null;
	private BigDecimal avgEndorsement;
	
	
	
	public RatedSkills(int skillId, int userSkillId, int level, int numEndorsement, String userId,
		String skillName, BigDecimal avgEndorsement)
	{
	    super();
	    this.skillId = skillId;
	    this.userSkillId = userSkillId;
	    this.level = level;
	    this.numEndorsement = numEndorsement;
	    this.userId = userId;
	    this.SkillName = skillName;
	    this.avgEndorsement = avgEndorsement;
	}

	
	
	public int getSkillId()
	{
	    return skillId;
	}



	public void setSkillId(int skillId)
	{
	    this.skillId = skillId;
	}



	public int getUserSkillId()
	{
	    return userSkillId;
	}



	public void setUserSkillId(int userSkillId)
	{
	    this.userSkillId = userSkillId;
	}



	public int getLevel()
	{
	    return level;
	}



	public void setLevel(int level)
	{
	    this.level = level;
	}



	public int getNumEndorsement()
	{
	    return numEndorsement;
	}



	public void setNumEndorsement(int numEndorsement)
	{
	    this.numEndorsement = numEndorsement;
	}



	public String getUserId()
	{
	    return userId;
	}



	public void setUserId(String userId)
	{
	    this.userId = userId;
	}



	public String getSkillName()
	{
	    return SkillName;
	}



	public void setSkillName(String skillName)
	{
	    SkillName = skillName;
	}



	public BigDecimal getAvgEndorsement()
	{
	    return avgEndorsement;
	}



	public void setAvgEndorsement(BigDecimal avgEndorsement)
	{
	    this.avgEndorsement = avgEndorsement;
	}



	@Override
	public String toString()
	{
	    return "GetRatedSkills [skillId=" + skillId + ", userSkillId=" + userSkillId + ", level=" + level
		    + ", numEndorsement=" + numEndorsement + ", userId=" + userId + ", SkillName=" + SkillName
		    + ", avgEndorsement=" + avgEndorsement + "]";
	}



	
	
	
	
}
