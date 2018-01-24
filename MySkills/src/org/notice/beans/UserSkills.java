package org.notice.beans;

import java.io.Serializable;

public class UserSkills implements Serializable
{
    private int skillID = 0  , level = 0;
    private String userId = null;
    
    public UserSkills( String userId, int skillID, int level)
    {
	
	this.skillID = skillID;
	this.level = level;
	this.userId = userId;
    }

    public int getSkillID()
    {
        return skillID;
    }

    public void setSkillID(int skillID)
    {
        this.skillID = skillID;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
	return "UserSkills [skillID=" + skillID + ", level=" + level + ", userId=" + userId + "]";
    }
    
    
    
    
}
