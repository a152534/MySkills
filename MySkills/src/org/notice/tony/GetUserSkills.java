package org.notice.tony;

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
	
	public ArrayList<UserSkills> getUserSkills()
	{
		
	}
	
}
