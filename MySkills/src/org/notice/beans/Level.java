package org.notice.beans;

import java.sql.Date;

public class Level
{
    int level = 0;
    String description = null;
    
    public Level(int level, String description)
    {
	super();
	this.level = level;
	this.description = description;
    }

   
	public int getLevel()
	{
		return level;
	}


	public void setLevel(int level)
	{
		this.level = level;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	@Override
    public String toString()
    {
	return "Level [level=" + level + ", description=" + description + "]";
    }
    
    
    
}
