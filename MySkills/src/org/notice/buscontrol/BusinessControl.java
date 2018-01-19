package org.notice.buscontrol;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.notice.beans.GetRatedSkills;
import org.notice.beans.User;
import org.notice.client.Transaction;
import org.notice.dao.MySkillsDAO;


public class BusinessControl
{
    private String userId , skillName, action;
    private MySkillsDAO skillsDB  = null;
    private ResultSet RS = null;
    private Transaction transaction = null;
    private ArrayList<User> UserList = null;
    private ArrayList<GetRatedSkills> userSkillList = null ;
    private ResultSet userResult = null , userSkillResult = null;
    int userSkillId, skillId ,level, numEndorsement;
    Date addedDate = null;
    BigDecimal avgEndorsement;
    
   
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
        	 userId = transaction.getObject().toString();
                 transaction.setObject(this.getUserList(userId));
                 transaction.setDescription("UserList");
         	break;
             }
                
             case "deleteUserSkill" : 
             {
         	userSkillId =  Integer.parseInt(transaction.getObject().toString());
                transaction.setObject(this.deleteUserSkill(userSkillId));
                transaction.setDescription("deleteUserSkill");
         	break;
             }
             
             case "getUserSkills" : 
             {
         	userId =  transaction.getObject().toString();
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
//		
		UserList = new ArrayList<User>();
		if (validateUser(userId))
		{
		
		try
		{
			//Fetch from database
			
			userResult = skillsDB.queryDB("select user_id, first_name, "
					+ "surname, alias_name, email, phone_num from user where user_id = '" + userId + "'");
			
			//Write to ArrayList
			userResult.next();
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
    
    public ArrayList<User> getUserList(String userId)
	{
		UserList = new ArrayList<User>();
		this.userId = userId;
		
		try
		{
			//Fetch from database
		    	
			userResult = skillsDB.queryDB("select * from user where user_id like '" + userId + "%'" +
					 " or first_name like '" + userId +  "%' or surname like '" + userId + "%'");
			
			//Write to ArrayList
			while (userResult.next())
			{
			String userID = userResult.getString("user_id");
			String firstName = userResult.getString("first_name");
			String surname = userResult.getString("surname");
			String aliasName = userResult.getString("alias_name");
			String email = userResult.getString("email");
			String phoneNum = userResult.getString("phone_num");
			 
			UserList.add(new User(userID, firstName, surname, aliasName, email, phoneNum));
			}
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
    
    public boolean deleteUserSkill(int userSkillId)
    {
        this.userSkillId = userSkillId;
        
        try
        {
    	skillsDB.updateDB("delete from endorsement where user_skill_id = " + userSkillId );
    	skillsDB.updateDB("delete from user_skill where user_skill_id = " + userSkillId );
    	
        } 
        catch (Exception e)
        {
    	e.printStackTrace();
    	return false;
        } 
       
        return true;
    }
    
    public ArrayList<GetRatedSkills> getUserSkills(String userId)
	{
		this.userId = userId;
		userSkillList = new ArrayList<GetRatedSkills>();
		
		try
		{
			//Fetch from database
			
			userSkillResult = skillsDB.queryDB("Select user_id, user_skill_id, skill_id, level, " +
				"num_endorsement, avg_endorsement, skill_name from v_user_skill_endorsements where user_id = '" + userId + "'");
			
			//Write to ArrayList
			while (userSkillResult.next())
			{
			userSkillId = userSkillResult.getInt("user_skill_id");
			userId = userSkillResult.getString("user_id");
			skillId = userSkillResult.getInt("skill_id");
			level = userSkillResult.getInt("level");
			numEndorsement = userSkillResult.getInt("num_endorsement");
			skillName = userSkillResult.getString("skill_name");
			avgEndorsement = userSkillResult.getBigDecimal("avg_endorsement");
			userSkillList.add(new GetRatedSkills(skillId, userSkillId, level, numEndorsement, userId,
			skillName, avgEndorsement));
			}
		} 
		catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return userSkillList;
	}
    
    
    public boolean endorseNomination(int userSkillId, String userId)
    {
	this.userSkillId = userSkillId;
	this.userId = userId;
	try
        {
    		skillsDB.updateDB("insert into endorsement_nomination values(null, " + userSkillId + ",'"  + userId + "',null)");
    	    	
        } 
        catch (Exception e)
        {
    	e.printStackTrace();
    	return false;
        } 
       
        return true;
	
	
    }
    
}
