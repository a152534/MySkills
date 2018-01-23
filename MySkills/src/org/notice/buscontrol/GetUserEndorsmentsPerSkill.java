package org.notice.buscontrol;



import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import org.notice.beans.RatedSkills;
import org.notice.beans.User;
import org.notice.dao.MySkillsDAO;

public class GetUserEndorsmentsPerSkill
{
	private MySkillsDAO userAccess = null;
	private ArrayList<RatedSkills> ratedSkillsList = null;
	private Connection userConnect = null;
	private Statement Usertate = null;
	private ResultSet userResult = null;
	
	public GetUserEndorsmentsPerSkill() throws Exception
	{
		userAccess = new MySkillsDAO();
	}
	
	public ArrayList<RatedSkills> getUser(int skillId)
	{
//		this.getCon();
		userAccess.getCon();
		ratedSkillsList = new ArrayList<RatedSkills>();
		
		try
		{
			//Fetch from database
			Usertate = userConnect.createStatement();
			userResult = Usertate.executeQuery("Select user_id, user_skill_id, skill_id, level, " +
					"num_of_endorsements, avg_endorsement, skill_name from v_user_skill_endorsements where skill_id = '" + skillId + "'");
			
			while (userResult.next())
			{
			int userSkillId = userResult.getInt("user_skill_id");
			String userId = userResult.getString("user_id");
			skillId = userResult.getInt("skill_id");
			int level = userResult.getInt("level");
			int numEndorsement = userResult.getInt("num_of_endorsements");
			String skillName = userResult.getString("skill_name");
			BigDecimal avgEndorsement = userResult.getBigDecimal("avg_endorsement");
			ratedSkillsList.add(new RatedSkills(skillId, userSkillId, level, numEndorsement, userId,
			skillName, avgEndorsement));
			}
		} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return ratedSkillsList;
	}
	
}