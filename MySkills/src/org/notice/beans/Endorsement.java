package org.notice.beans;

public class Endorsement
{
    int userSkillId = 0 , level = 0;
    String endorser = null;
    
    public Endorsement(int userSkillId, String endorser, int level)
    {
	super();
	this.userSkillId = userSkillId;
	this.level = level;
	this.endorser = endorser;
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

    public String getEndorser()
    {
        return endorser;
    }

    public void setEndorser(String endorser)
    {
        this.endorser = endorser;
    }

    @Override
    public String toString()
    {
	return "Endorsement [userSkillId=" + userSkillId + ", level=" + level + ", endorser=" + endorser + "]";
    }
    
    
    
}
