package org.notice.buscontrol;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.notice.dao.*;

public class SaveUserSkill
{

private String userId;
private int skillId, level; 
private MySkillsDAO skillsDB  = null;
private ResultSet RS = null;


public SaveUserSkill()
{
    
}

public boolean SaveUSkill(String userId , int skillId, int level)
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
}