package org.notice.test;

import static org.junit.Assert.*;


import org.junit.Test;

import org.notice.buscontrol.*;

public class ValidateUserTest
{
    ValidateUser validate = null;
    @Test
    public void testValidateUserString()
    {
	 validate = new ValidateUser();

         assertTrue(validate.ValidateUser1("A000678"));
        
         
    }


}
