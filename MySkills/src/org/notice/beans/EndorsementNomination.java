package org.notice.beans;

import java.sql.Date;

public class EndorsementNomination
{
    int userSkillId = 0;
    String nominatedEndorsee = null;
    
    public EndorsementNomination(int userSkillId, String nominatedEndorsee)
    {
	super();
	 
	this.userSkillId = userSkillId;
	this.nominatedEndorsee = nominatedEndorsee;
	
    }

   

	public int getUserSkillId()
	{
		return userSkillId;
	}



	public void setUserSkillId(int userSkillId)
	{
		this.userSkillId = userSkillId;
	}

	public String getNominatedEndorsee()
	{
		return nominatedEndorsee;
	}



	public void setNominatedEndorsee(String nominatedEndorsee)
	{
		this.nominatedEndorsee = nominatedEndorsee;
	}



	@Override
    public String toString()
    {
	return "EndorsementNomination [userSkillId= " + userSkillId + ", nominatedEndorsee=" + nominatedEndorsee + "]";
    }
    
    
    
}