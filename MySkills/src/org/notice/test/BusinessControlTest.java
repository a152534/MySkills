package org.notice.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.notice.beans.User;
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
		// GetUserList getUserList = null;
		
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
		    public void testGetUserListArray()
		    {
			// GetUserList getUserList = null;
			
			try
			{
				validate = new BusinessControl();
			
				final ArrayList<User> result = validate.getUserList("A000");
		
				assertFalse(result.isEmpty());
				 
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			    
	    }


	}
	

