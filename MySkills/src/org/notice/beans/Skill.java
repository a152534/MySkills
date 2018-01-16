package org.notice.beans;

import java.io.Serializable;

public class Skill implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int skillID = 0;
	private String skillName = null;

	
	public Skill()
	{
		
	}

	public Skill(int skillID, String skillName)
	{
		super();
		this.skillID = skillID;
		this.skillName = skillName;
	
	}

	public int getSkillID()
	{
		return skillID;
	}

	public void setSkillID(int skillID)
	{
		this.skillID = skillID;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public void setSkillName(String skillName)
	{
		this.skillName = skillName;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}




}
