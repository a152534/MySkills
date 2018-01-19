package org.notice.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.notice.tonysandpit.GetUserSkills;
import org.notice.tonysandpit.UserSkills;

public class GetUserSkillsTest
{
	GetUserSkills userSkills = null;
	
	@Test
	public void testGetUserSkills() throws Exception
	{
		userSkills = new GetUserSkills();
		ArrayList<UserSkills> userSkillList = new ArrayList<UserSkills>();
		userSkillList = userSkills.getUserSkills("A000678");
		for(int pos = 0; pos < userSkillList.size(); pos++)
		{
			System.out.println("User skill ID: " + userSkillList.get(pos).getUserSkillID() + " User ID: " + userSkillList.get(pos).getUserID() + " Skill ID: " + userSkillList.get(pos).getSkillID()
			+ " Level: " + userSkillList.get(pos).getLevel() + " Timestamp: "+ userSkillList.get(pos).getAddedDate());
		}
		
		assertFalse((userSkills.getUserSkills("A000678")).isEmpty());
	}
	
}
