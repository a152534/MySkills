package org.notice.buscontrol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.notice.beans.User;
import org.notice.dao.MySkillsDAO;

public class BusinessControl
{
    private String userId;
    private MySkillsDAO skillsDB  = null;
    private ResultSet RS = null;
    
    private MySkillsDAO userAccess = null;
    private ArrayList<User> UserList = null;
	private Connection userConnect = null;
	private Statement Usertate = null;
	private ResultSet userResult = null;
   
    public BusinessControl()
    {

    }
    
    public boolean ValidateUser(String userId)
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
    
    public ArrayList<User> getUser(String userId)
	{
		UserList = new ArrayList<User>();
		this.userId = userId;
		if (ValidateUser(userId))
		{
		
		try
		{
			//Fetch from database
			
			userResult = skillsDB.queryDB("select * from user where user_id like '" + userId + "%'" +
					 " or first_name like '" + userId +  "%' or surname like '" + userId + "%'");
			
			//Write to ArrayList
			String userID = userResult.getString("user_id");
			String firstName = userResult.getString("first_name");
			String surname = userResult.getString("surname");
			String aliasName = userResult.getString("alias_name");
			String email = userResult.getString("email");
			String phoneNum = userResult.getString("phone_num");
			 
			UserList.add(new User(userID, firstName, surname, aliasName, email, phoneNum));
		} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return UserList;
		}
		else
		{
		 return   UserList= null;
		    
		}
	}
    
}
