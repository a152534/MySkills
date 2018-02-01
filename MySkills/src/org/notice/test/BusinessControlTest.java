package org.notice.test;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.notice.beans.EndorsementNomination;
import org.notice.beans.RatedSkills;
import org.notice.beans.User;
import org.notice.beans.UserSkills;
import org.notice.buscontrol.*;
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
		
			final ArrayList<RatedSkills> result = validate.getUserSkills("A000678");
			assertNotEquals(0, result.size());
			 
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    @Test
	    public void testCreateEndorseNomination()
	    {
		validate = new BusinessControl();
		EndorsementNomination en = new EndorsementNomination("A000456", "A000123");
//		assertTrue(validate.createEndorseNomination("A000456", "A000123"));
		assertTrue(validate.createEndorseNomination(en.getUserID(), en.getEndorserUserID()));
		
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
	

