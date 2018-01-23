package org.notice.enums;

public enum Skill_Levels
{
	Notice(1),
	Advanced_Beginner(2),
	Competent(3),
	Proficient(4),
	Expert(5);
	
	private int value;
	
	private Skill_Levels(int value)
	{
		this.value = value;
	}
}
