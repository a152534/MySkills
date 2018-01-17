package org.notice.buscontrol;


    import java.sql.ResultSet;
    import java.sql.SQLException;

    import org.notice.dao.*;

    public class DeleteUserSkill
    {
    private String userId , skillId;
    private MySkillsDAO skillsDB  = null;
    private ResultSet RS = null;


    public DeleteUserSkill()
    {
        
    }

    public boolean DeleteUSkill(String userId , String skillId)
    {
        this.userId = userId;
        this.skillId = skillId;
        try
        {
    	skillsDB = new MySkillsDAO();
    	skillsDB.updateDB("delete from user_skill where user_id = '" + userId + "'" + " and skill_id = '" + skillId + "'");
    	
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
