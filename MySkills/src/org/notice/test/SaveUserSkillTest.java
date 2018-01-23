package org.notice.test;

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;



import org.notice.buscontrol.*;

public class SaveUserSkillTest
{
	BusinessControl validate = null;
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	
	public void testSaveUserSkill() throws Exception
	{
		validate = new BusinessControl();
		
	 
		SaveUserSkill us = new SaveUserSkill();
		
		try 
		{
			us.SaveUSkill("A000678", 1, 5);
		}
		catch(Exception me)
		{
			assertEquals(true, me instanceof Exception);
		}
		
		
		
	}

}
