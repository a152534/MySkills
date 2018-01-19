package org.notice.test;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;
import org.notice.beans.Skill;
import org.notice.buscontrol.GetSkillList;


public class GetSkillListTest
{
	GetSkillList fetchSkills = null;

	@Test
	public void testGetSkillsList() throws Exception
	{
		fetchSkills = new GetSkillList();
		ArrayList<Skill> skills = new ArrayList<Skill>();
		skills = fetchSkills.getSkillList();
		for(int pos = 0; pos < skills.size(); pos++)
		{
			System.out.println("Skill " + pos + " is" + skills.get(pos).getSkillID() + " "
					+ skills.get(pos).getSkillName());
		}
		
		assertFalse((fetchSkills.getSkillList()).isEmpty());
	}

}
