package org.notice.buscontrol;

import java.util.ArrayList;
import org.notice.beans.Skill;
import org.notice.beans.User;
import org.notice.dao.*;
import java.sql.*;

public class SearchSkills 
{
	private MySkillsDAO skillAccess = null;
	private ArrayList<Skill> skillList = null;
	private ResultSet skillResult = null;
	
	public SearchSkills() throws Exception
	{
		skillAccess = new MySkillsDAO();
	}
	
	public ArrayList<Skill> getSearchSkills(String skillNameSearch)
	{
//		this.getCon();
		skillAccess.getCon();
		skillList = new ArrayList<Skill>();
		
		try
		{
			//Fetch from database
			
			skillResult = skillAccess.queryDB("select skill_id, skill_name from skills"
					+ " where skill_name like ('" + skillNameSearch + "%')");
			
			//Write to ArrayList
			skillResult.next();
			int skillId = skillResult.getInt("skill_id");
			String skillName = skillResult.getString("skill_name");
		 
			 
			skillList.add(new Skill(skillId, skillName));
			
					} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillList;
	}
	
	
	
}