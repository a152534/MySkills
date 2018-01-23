package org.notice.test;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.Test;
import org.notice.beans.Skill;
import org.notice.buscontrol.*;


public class SearchSkillsTest
{
	private ArrayList<Skill> skillList = null;
	SearchSkills searchSkills = null;
	
	@Test
	public void testSearchSkillsTest() throws Exception
	{
		searchSkills = new SearchSkills();
		ArrayList<Skill>  userSkillList = new ArrayList<Skill> ();
		userSkillList = searchSkills.getSearchSkills("ACC");
		for(int pos = 0; pos < userSkillList.size(); pos++)
		{
			System.out.println("Skill ID: " + userSkillList.get(pos).getSkillID() + " Skill desc: " + userSkillList.get(pos).getSkillName());
			}
		
		assertFalse(searchSkills.getSearchSkills("ACC").isEmpty());
	}
	
}
