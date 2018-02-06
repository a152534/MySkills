package org.notice.beans;

public class LevelDescription
{
	public LevelDescription()
	{
		
	}
	
	public String getLevelDescription(byte levelValueFromDB)
	{
		String enumDisplay = new String();
		
		switch (levelValueFromDB)
		{
			case 1	:	enumDisplay = ("Novice");
						return enumDisplay;	
			case 2	:	enumDisplay = ("Advanced Beginner");
						return enumDisplay;
			case 3	:	enumDisplay = ("Competent");
						return enumDisplay;
			case 4	:	enumDisplay = ("Proficient");
						return enumDisplay;
			case 5	:	enumDisplay = ("Expert");
						return enumDisplay;
			default :	enumDisplay = ("No Rating");
						return enumDisplay;
		}
	}
}
