package org.notice.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.notice.tonysandpit.GetSkillList;

public class GetSkillsListTest
{
	GetSkillList skills = null;
	@Test
	public void testGetSkillList() throws Exception
	{
		skills = new GetSkillList();
		skills.getSkillList();
	}

}
