package org.notice.beans;

import java.sql.Date;

public class EndorsementNomination
{
    int endorsementNominationId = 0, userSkillId = 0;
    Date addedTimestamp = null;
    
    public EndorsementNomination(int endorsementNominationId, int userSkillId, Date addedTimestamp)
    {
	super();
	this.endorsementNominationId = endorsementNominationId;
	this.userSkillId = userSkillId;
	this.addedTimestamp = addedTimestamp;
    }

   

	public int getEndorsementNominationId()
	{
		return endorsementNominationId;
	}



	public void setEndorsementNominationId(int endorsementNominationId)
	{
		this.endorsementNominationId = endorsementNominationId;
	}



	public int getUserSkillId()
	{
		return userSkillId;
	}



	public void setUserSkillId(int userSkillId)
	{
		this.userSkillId = userSkillId;
	}



	public Date getAddedTimestamp()
	{
		return addedTimestamp;
	}



	public void setAddedTimestamp(Date addedTimestamp)
	{
		this.addedTimestamp = addedTimestamp;
	}



	@Override
    public String toString()
    {
	return "EndorsementNomination [endorsementNominationId=" + endorsementNominationId + ", userSkillId=" + userSkillId + ", addedTimestamp=" + addedTimestamp + "]";
    }
    
    
    
}