package org.notice.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.notice.beans.GetRatedSkills;
import org.notice.beans.User;
import org.notice.buscontrol.*;
import org.notice.tonysandpit.UserSkills;
import org.junit.Test;

public class BusinessControlTest
{
		BusinessControl validate = null;
		
	    @Test
	    public void testValidateUserString()
	    {
		 validate = new BusinessControl();

	         assertTrue(validate.validateUser("A000678"));
	        
	         
	    }
	    @Test
	    public void testGetUserArray()
	    {
		
		
		try
		{
			validate = new BusinessControl();
		
			final ArrayList<User> result = validate.getUser("A000678");
			assertEquals(1, result.size());
			 
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    
	    @Test
 	    public void testGetUserList()
	    {

		
		try
		{
			validate = new BusinessControl();
		
			final ArrayList<User> result = validate.getUserList("A000");
			assertNotEquals(0, result.size());
			 
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    @Test
 	    public void testGetUserSkills()
	    {
		
		try
		{
			validate = new BusinessControl();
		
			final ArrayList<GetRatedSkills> result = validate.getUserSkills("A000678");
			assertNotEquals(0, result.size());
			 
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    
//		@Test
		
//		public void testSaveUserSkill() throws Exception
//		{
//		 
//			validate = new BusinessControl();
//			System.out.println("BegoreSave " );
//			result = validate.SaveUSkill("A000678", "1", "5");
//			System.out.println("AfterSave " );
//			System.out.println("GetUserSkills " + us1.getUserSkills("A000678") );
//	//		assertEquals("A000678", us1.getUserSkills("A000678"));
//		}
	}
	

