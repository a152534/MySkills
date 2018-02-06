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
			case 1	:	enumDisplay = ("1. Novice");
						return enumDisplay;	
			case 2	:	enumDisplay = ("2. Advanced Beginner");
						return enumDisplay;
			case 3	:	enumDisplay = ("3. Competent");
						return enumDisplay;
			case 4	:	enumDisplay = ("4. Proficient");
						return enumDisplay;
			case 5	:	enumDisplay = ("5. Expert");
						return enumDisplay;
			default :	enumDisplay = ("No Rating");
						return enumDisplay;
		}
	}
}
