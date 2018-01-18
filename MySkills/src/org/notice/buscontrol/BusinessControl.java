package org.notice.buscontrol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.notice.beans.User;
import org.notice.client.Transaction;
import org.notice.dao.MySkillsDAO;

public class BusinessControl
{
    private String userId , userSkillId, action;
    private MySkillsDAO skillsDB  = null;
    private ResultSet RS = null;
    private Transaction transaction = null;
    private ArrayList<User> UserList = null;
    private ResultSet userResult = null;
   
    public BusinessControl()
    {
	try
	{
	    skillsDB = new MySkillsDAO();
	} 
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public Transaction handleTransaction(Transaction transaction)
    {
	this.transaction = transaction;
	action = transaction.getDescription();
	switch(action)

        {

             case "getUser" :

             {
        	userId = transaction.getObject().toString();
                transaction.setObject(this.getUser(userId));
                transaction.setDescription("User");
                
                break;

             }

             case "getUserList" : 
             {
         	
         	break;
             }
                
             case "deleteUserSkill" : 
             {
         	userSkillId =  transaction.getObject().toString();
                transaction.setObject(this.deleteUserSkill(userSkillId));
                transaction.setDescription("deleteUserSkill");
         	break;
             }
             
         default : System.out.println("Incorrect selection"); 

 
        }
	
	return transaction;
	
	
    }
    
    public boolean validateUser(String userId)
    {
        this.userId = userId;
        try
        {
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
		if (validateUser(userId))
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
		} 
		catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		catch (Exception e)
		{
		    System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return UserList;
		}
		else
		{
		 return   UserList= null;
		    
		}
	}
    
    public boolean deleteUserSkill(String userSkillId)
    {
        this.userSkillId = userSkillId;
        
        try
        {
    	skillsDB.updateDB("delete from endorsement where user_skill_id = '" + userSkillId + "'");
    	skillsDB.updateDB("delete from user_skill where user_skill_id = '" + userSkillId + "'");
    	
        } 
        catch (Exception e)
        {
    	e.printStackTrace();
    	return false;
        } 
       
        return true;
    }
    
}
