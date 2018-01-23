package org.notice.buscontrol;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.notice.beans.Endorsement;
import org.notice.beans.EndorsementNomination;
import org.notice.beans.EndorsementsGroupedBySkill;
import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;
import org.notice.beans.User;
import org.notice.beans.UserSkills;
import org.notice.client.Transaction;
import org.notice.dao.MySkillsDAO;


public class BusinessControl
{
    private String userId , skillName, endorsor, action;
    private MySkillsDAO skillsDB  = null;
    private ResultSet RS = null;
    private Transaction transaction = null;
    private ArrayList<User> UserList = null;
    private ArrayList<RatedSkills> userSkillList = null ;
    private  ArrayList<Skill> skillList = null;
    private ResultSet userResult = null , userSkillResult = null, skillResult = null;
    private int userSkillId, skillId ,level, numEndorsement;
    private BigDecimal avgEndorsement;
    private UserSkills US = null;
    private Endorsement endorse = null;
    private  EndorsementNomination endorseNom = null;
    private  ArrayList<EndorsementsGroupedBySkill> skillReport = null;
    
   
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
                transaction.setObject(this.getUserSkills(userId));
                transaction.setDescription("UserSkills");
         	break;
             }
             
             case "getSkillList" : 
             {
         	transaction.setObject(this.getSkillList());
                transaction.setDescription("SkillsList");
         	break;
             }
             
             case "SearchSkills" : 
             {
        	skillName = transaction.getObject().toString();
         	transaction.setObject(this.searchSkills(skillName));
                transaction.setDescription("SearchSkills");
         	break;
             }
             
             case "SaveUserSkill" : 
             {
        	US=(UserSkills)transaction.getObject();
         	transaction.setObject(this.SaveUserSkill(US.getUserId(), US.getSkillID(), US.getLevel()));
                transaction.setDescription("SaveUserSkill");
         	break;
             }
             
             case "createEndorsement" : 
             {
        	endorse =(Endorsement)transaction.getObject();
        	transaction.setObject(this.createEndorsement( endorse.getUserSkillId(), endorse.getEndorser(), endorse.getLevel()));
                transaction.setDescription("createEndorsement");
         	break;
             }
             
             
             case "endorseNomination" : 
             {
        	endorseNom =(EndorsementNomination)transaction.getObject();
        	transaction.setObject(this.endorseNomination( endorseNom.getUserSkillId(), endorseNom.getNominatedEndorsee()));
                transaction.setDescription("endorsementNomination");
         	break;
             }
             
             case "getSkillsReport" : 
             {
        	transaction.setObject(this.endorseNomination( endorseNom.getUserSkillId(), endorseNom.getNominatedEndorsee()));
                transaction.setDescription("endorsementNomination");
         	break;
             }
             
             case "getUsersPerSkill" : 
             {
        	skillName = transaction.getObject().toString();
          	transaction.setObject(this.searchSkills(skillName));
                transaction.setDescription("SearchSkills");
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
    
    public ArrayList<RatedSkills> getUserSkills(String userId)
	{
		this.userId = userId;
		userSkillList = new ArrayList<RatedSkills>();
		
		try
		{
			//Fetch from database
			
			userSkillResult = skillsDB.queryDB("Select user_id, user_skill_id, skill_id, level, " +
				"num_of_endorsements, avg_endorsement, skill_name from v_user_skill_endorsements where user_id = '" + userId + "'");
			
			//Write to ArrayList
			while (userSkillResult.next())
			{
			userSkillId = userSkillResult.getInt("user_skill_id");
			userId = userSkillResult.getString("user_id");
			skillId = userSkillResult.getInt("skill_id");
			level = userSkillResult.getInt("level");
			numEndorsement = userSkillResult.getInt("num_of_endorsements");
			skillName = userSkillResult.getString("skill_name");
			avgEndorsement = userSkillResult.getBigDecimal("avg_endorsement");
			userSkillList.add(new RatedSkills(skillId, userSkillId, level, numEndorsement, userId,
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
    
    public ArrayList<Skill> searchSkills(String skillName)
	{
		this.skillName = skillName;
		skillList = new ArrayList<Skill>();
		
		try
		{
			//Fetch from database
			
			skillResult = skillsDB.queryDB("select skill_id, skill_name from skills"
					+ " where skill_name like ('" + skillName + "%')");
			
			//Write to ArrayList
			while (skillResult.next())
			{
			int skillId = skillResult.getInt("skill_id");
			skillName = skillResult.getString("skill_name");
		 	 
			skillList.add(new Skill(skillId, skillName));
			}
			
		} 
		catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillList;
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
    
    public ArrayList<Skill> getSkillList()
	{
//		
		skillList = new ArrayList<Skill>();
		
		try
		{
			//Fetch from database
			
			skillResult = skillsDB.queryDB("SELECT skill_id, skill_name from skills");
			
			//Write to ArrayList
			while(skillResult.next())
			{
			 skillId = skillResult.getInt("skill_id");
			 skillName = skillResult.getString("skill_name");
			 skillList.add(new Skill(skillId, skillName));		
			}//End while	
		} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillList;
	}
    
    public boolean SaveUserSkill(String userId , int skillId, int level)
    {
        this.userId = userId;
        this.skillId = skillId;
        this.level = level;
       
        try
        {
        skillsDB = new MySkillsDAO();
    	RS = skillsDB.queryDB("SELECT * from user_skill where user_id = '" + userId + "' and skill_id = " + skillId);
    	
    	if (!RS.next())
    	{
    		skillsDB.updateDB("insert into user_skill values(null, '" + userId + "', " + skillId + ", " + level + ", null");
    	
    	}
    	else
    			
    		skillsDB.updateDB("update user_skill set level = " +  level + " where user_id = '" + userId + "' and skill_id = " + skillId);
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
        return true;
    }
    
   
    public boolean createEndorsement(int userSkillId, String endorser, int level)
	{	
		this.userSkillId = userSkillId; 
		this.endorsor = endorser;
		this.level = level;
		try
		{
			skillsDB.updateDB("INSERT INTO endorsement VALUES (null, " + userSkillId + ",'" + endorsor + "'," + level + ", null)");
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
		return true;
	}
    
<<<<<<< HEAD
    
	public String fetchUserIDFromArrayList(String surnameSearched, String firstNameSearched)
	{
		//Allows get of specific field details for graphical representation
		for(int pos = 0; pos < UserList.size(); pos++)
		{
			if((UserList.get(pos).getSurName().equals(surnameSearched)) && (UserList.get(pos).getSurName().equals(firstNameSearched)))
			{
				userId = UserList.get(pos).getUserID();
			}
		}
		return userId;
	}
	
	public String fetchUserNamesFromArrayList(ArrayList detailsSearched)
	{
		//Allows get of specific field details for graphical representation
		String surnameAndName = null;
		for(int pos = 0; pos < UserList.size(); pos++)
		{
			if((UserList.get(pos).getSurName().equals(detailsSearched)) || (UserList.get(pos).getSurName().equals(detailsSearched)) ||
					(UserList.get(pos).getUserID().equals(detailsSearched)))
			{
				surnameAndName = surnameAndName + UserList.get(pos).getSurName() + ", " + UserList.get(pos).getFirstName();
			}
		}
		return surnameAndName;
	}
	
=======
    public ArrayList<EndorsementsGroupedBySkill> getSkillsReport()
	{
//		
		skillReport = new ArrayList<EndorsementsGroupedBySkill>();
		
		try
		{
			//Fetch from database
			
			skillResult = skillsDB.queryDB("SELECT skill_id, skill_name, avg_rating, num_rating from v_endorsements_grouped_by_skills");
			
			//Write to ArrayList
			while(skillResult.next())
			{
			 skillId = skillResult.getInt("skill_id");
			 skillName = skillResult.getString("skill_name");
			 avgEndorsement = skillResult.getBigDecimal("avg_rating");
			 numEndorsement = skillResult.getInt("num_rating");
			 
			 skillReport.add(new EndorsementsGroupedBySkill(skillId, skillName, avgEndorsement, numEndorsement));		
			}//End while	
		} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillReport;
	}
    
>>>>>>> branch 'master' of https://github.com/a152534/MySkills.git
}
