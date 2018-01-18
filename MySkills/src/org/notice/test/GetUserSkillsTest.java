package org.notice.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.notice.tonysandpit.GetUserSkills;

public class GetUserSkillsTest
{

	GetUserSkills userSkills = null;

	@Test
	public void testGetUserSkills() throws Exception
	{
		userSkills = new GetUserSkills();
		userSkills.getUserSkills("A000678");
	}

}
