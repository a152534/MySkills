package org.notice.tonysandpit;

import java.util.ArrayList;
import org.notice.beans.Skill;
import org.notice.dao.*;
import java.sql.*;

public class GetSkillList 
{
	private MySkillsDAO skillAccess = null;
	private ArrayList<Skill> skillList = null;
	private Connection skillConnect = null;
	private Statement skillState = null;
	private ResultSet skillResult = null;
	
	public GetSkillList() throws Exception
	{
		skillAccess = new MySkillsDAO();
	}
	
	public ArrayList<Skill> getSkillList()
	{
//		this.getCon();
		skillAccess.getCon();
		skillList = new ArrayList<Skill>();
		
		try
		{
			//Fetch from database
			skillState = skillConnect.createStatement();
			skillResult = skillState.executeQuery("SELECT * from skills");
			
			//Write to ArrayList
			while(skillResult.next())
			{
				int skillID = skillResult.getInt("skill_id");
				String skillName = skillResult.getString("skill_name");
				skillList.add(new Skill(skillID, skillName));		
			}//End while	
		} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillList;
	}
	
	
	
}