package org.notice.tonysandpit;

import java.sql.*;
import java.util.ArrayList;

import org.notice.dao.MySkillsDAO;

public class GetUserSkills
{
	private MySkillsDAO skillAccess = null;
	private ArrayList<UserSkills> userSkillList = null;
	private Connection userSkillConnect = null;
	private Statement userSkillState = null;
	private ResultSet userSkillResult = null;
	
	public GetUserSkills() throws Exception
	{
		skillAccess = new MySkillsDAO();
	}
	
	public ArrayList<UserSkills> getUserSkills(String user_ID)
	{
//		this.getCon();
		skillAccess.getCon();
		userSkillList = new ArrayList<UserSkills>();
		
		try
		{
			//Fetch from database
			userSkillState = userSkillConnect.createStatement();
			userSkillResult = userSkillState.executeQuery("SELECT * from user_skill where user_id = '" + user_ID + "'");
			
			//Write to ArrayList
			int userSkillID = userSkillResult.getInt("user_skill_id");
			String userID = userSkillResult.getString("user_id");
			int skillID = userSkillResult.getInt("skill_id");
			int level = userSkillResult.getInt("level");
			Date addedDate = userSkillResult.getDate("added_date");
			userSkillList.add(new UserSkills(userSkillID, userID, skillID, level, addedDate));
		} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return userSkillList;
	}
	
}
