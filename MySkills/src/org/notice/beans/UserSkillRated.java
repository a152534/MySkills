package org.notice.beans;

public class UserSkillRated
{
	int userSkillId = 0;
	long numOfEndorsements = 0L;
	double avgEndorsement = 0.0;
	
	public UserSkillRated(int userSkillId, long numOfEndorsements, double avgEndorsement)
	{
		this.userSkillId = userSkillId;
		this.numOfEndorsements = numOfEndorsements;
		this.avgEndorsement = avgEndorsement;
	}
	
	public UserSkillRated(int userSkillId)
	{
		this(userSkillId, 0L, 0.0);
	}
	
	
	public int getUserSkillId()
	{
		return userSkillId;
	}
	public long getNumEndorsement()
	{
		return numOfEndorsements;
	}
	public double getAvgEndorsement()
	{
		return avgEndorsement;
	}
	public void setUserSkillId(int userSkillId)
	{
		this.userSkillId = userSkillId;
	}
	public void setNumEndorsement(long numOfEndorsements)
	{
		this.numOfEndorsements = numOfEndorsements;
	}
	public void setAvgEndorsement(double avgEndorsement)
	{
		this.avgEndorsement = avgEndorsement;
	}
	@Override
	public String toString()
	{
		return "UserSkillRated [userSkillId=" + userSkillId + ", numOfEndorsements=" + numOfEndorsements + ", avgEndorsement="
				+ avgEndorsement + "]";
	}
	
	
}
