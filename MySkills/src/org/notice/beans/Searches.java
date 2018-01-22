package org.notice.beans;

import java.sql.Date;

public class Searches
{
    int searchedSkillId = 0;
    Date searchTime = null;
    String searchchar = null;
    
    public Searches(int searchedSkillId, Date searchTime, String searchchar)
    {
	super();
	this.searchedSkillId = searchedSkillId;
	this.searchTime = searchTime;
	this.searchchar = searchchar;
    }

   
    public int getSearchedSkillId()
	{
		return searchedSkillId;
	}


	public void setSearchedSkillId(int searchedSkillId)
	{
		this.searchedSkillId = searchedSkillId;
	}


	public Date getSearchTime()
	{
		return searchTime;
	}


	public void setSearchTime(Date searchTime)
	{
		this.searchTime = searchTime;
	}


	public String getSearchchar()
	{
		return searchchar;
	}


	public void setSearchchar(String searchchar)
	{
		this.searchchar = searchchar;
	}


	@Override
    public String toString()
    {
	return "Searches [searchedSkillId=" + searchedSkillId + ", searchTime=" + searchTime + ", searchchar=" + searchchar + "]";
    }
    
    
    
}
