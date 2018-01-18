package org.notice.tonysandpit;

import java.sql.*;
import org.notice.dao.MySkillsDAO;

public class CreateEndorsement
{
	private String endorser = null;
	private MySkillsDAO skillsDB  = null;
	private int userSkillId, level;
	private PreparedStatement endorseUpdate = null;
	private Connection endorseConnect = null;
	
	
	public CreateEndorsement()
	{
		
	}
	
	public void createEndorsement(int userSkillId, String endorser, int level)
	{	
		this.userSkillId = userSkillId; 
		this.endorser = endorser;
		this.level = level;
		try
		{
			skillsDB = new MySkillsDAO();
			endorseUpdate = endorseConnect.prepareStatement("INSERT INTO endorsement VALUES (null, ?, ?, ?, null)");
			endorseUpdate.setInt(1, userSkillId);
			endorseUpdate.setString(2, endorser);
			endorseUpdate.setInt(3, level);
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	    
}	    
	    
	    
	    
	    
	    
	    
//		    try
//		    {
//				skillsDB = new MySkillsDAO();
//				RSA = skillsDB.queryDB("select user_skill_id from user_skill where user_id = '" + userId + "'");
//				while(RSA.next())
//				{
//					int userSkillId = RSA.getInt("user_skill_id");
//					RSB = skillsDB.queryDB("select * from endorsement where user_skill_id = '" + userSkillId + "'");
//					while(RSB.next())
//					{
//						int endorsementId = RSB.getInt("endorsement_id");
//						int level = RSB.getInt("level");
//						Date addedTimestamp = RSB.getDate("added_timestamp");
//					}
//				}
//		    }
//		    catch (SQLException e)
//		    {
//				e.printStackTrace();
//				return;
//		    } 
	
//public void displayForEndorsement(String userId) throws Exception
//{
//    this.userId = userId;
//    getSkills = new GetUserSkills();
//    skillList = new GetSkillList();
//    skillsToEndorse = getSkills.getUserSkills(userId);
//    skillNamesForEndorsement = skillList.getSkillList();
//    for(int pos = 0; pos < skillsToEndorse.size(); pos++)
//    {
//    	int userSkillID = skillsToEndorse.get(pos).getSkillID();
//    	String userID = skillsToEndorse.get(pos).getUserID();
//    	int skillID = skillsToEndorse.get(pos).getSkillID();
//    	int level = skillsToEndorse.get(pos).getLevel();
//    	String skillName = skillNamesForEndorsement.get(skillID).getSkillName();
//    	
//    	
//    }
//}
//
//public class EndoresementDisplay
//{
//	int usrSklId, sklId, lvl;
//	String usrId, sklNm;
//	public EndorsementDisplay(int usrSklId, String usrId, int sklId, int lvl, String sklNm)
//	{
//		this.usrSklId = usrSklId;
//		this.usrId = usrId;
//		this.sklId = sklId;
//		this.lvl = lvl;
//		this.sklNm = sklNm;
//	}
//}
