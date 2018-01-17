package org.notice.buscontrol;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.notice.dao.*;

public class ValidateUser
{
private String userId;
private MySkillsDAO skillsDB  = null;
private ResultSet RS = null;


public ValidateUser()
{
    
}

public boolean ValidateUser1(String userId)
{
    this.userId = userId;
    try
    {
	skillsDB = new MySkillsDAO();
	RS = skillsDB.queryDB("select * from user where user_id = '" + userId + "'");
	if (!RS.next())
	{
		
		System.out.println("user does not exist");
		return false;
	}
	else 
	{
	return true;
	}
    } 
    catch (SQLException e)
    {
	e.printStackTrace();
	return false;
    } 
    catch (Exception e)
    {
	// TODO Auto-generated catch block
	e.printStackTrace();
	return false;
    }
}

}
