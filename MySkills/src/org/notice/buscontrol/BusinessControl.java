package org.notice.buscontrol;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.notice.beans.*;
import org.notice.client.Transaction;
import org.notice.dao.MySkillsDAO;

public class BusinessControl {
	private String userId, skillName, endorsor, action, firstName, surname;
	private MySkillsDAO skillsDB = null;
	private ResultSet RS = null;
	private Transaction transaction = null;
	private ArrayList<User> UserList = null;
	private ArrayList<RatedSkills> userSkillList = null;
	private ArrayList<Skill> skillList = null;
	private ResultSet userResult = null, userSkillResult = null, skillResult = null;
	private int userSkillId, skillId, level, numEndorsement;
	private Long numOfResources = 0L;
	private BigDecimal avgEndorsement, numOfEndorsements;
	private UserSkills US = null;
	private Endorsement endorse = null;
	private EndorsementNomination endorseNom = null;
	private ArrayList<EndorsementsPerSkill> skillReport = null;
	private ArrayList<UserSkillEndorsements> ratedSkillsList = null;

	public BusinessControl() {
		try {
			skillsDB = new MySkillsDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Transaction handleTransaction(Transaction transaction) {
		this.transaction = transaction;
		action = transaction.getDescription();
		switch (action)

		{

		case "getUser":

		{
			userId = transaction.getObject().toString();
			transaction.setObject(this.getUser(userId));
			transaction.setDescription("User");

			break;

		}

		case "getUserList": {
			userId = transaction.getObject().toString();
			transaction.setObject(this.getUserList(userId));
			transaction.setDescription("UserList");
			break;
		}

		case "deleteUserSkill": {
			userSkillId = Integer.parseInt(transaction.getObject().toString());
			transaction.setObject(this.deleteUserSkill(userSkillId));
			transaction.setDescription("deleteUserSkill");
			break;
		}

		case "getUserSkills": {
			userId = transaction.getObject().toString();
			transaction.setObject(this.getUserSkills(userId));
			transaction.setDescription("UserSkills");
			break;
		}

		case "getSkillList": {
			transaction.setObject(this.getSkillList());
			transaction.setDescription("SkillList");
			break;
		}

		case "SearchSkills": {
			skillName = transaction.getObject().toString();
			transaction.setObject(this.searchSkills(skillName));
			transaction.setDescription("SearchSkills");
			break;
		}

		case "SaveUserSkill": {
			US = (UserSkills) transaction.getObject();
			transaction.setObject(this.SaveUserSkill(US.getUserId(), US.getSkillID(), US.getLevel()));
			transaction.setDescription("SaveUserSkill");
			break;
		}

		case "DeleteSkill": {
			skillId = Integer.parseInt(transaction.getObject().toString());
			System.out.println("in case delete " + skillId);
			transaction.setObject(this.deleteSkill(skillId));
			transaction.setDescription("DeleteSkill");
			break;
		}

		case "AddSkill": {
			skillName = transaction.getObject().toString();
			transaction.setObject(this.addSkill(skillName));
			transaction.setDescription("AddSkill");
			break;
		}

		case "createEndorsement": {
			endorse = (Endorsement) transaction.getObject();
			transaction.setObject(
					this.createEndorsement(endorse.getUserSkillId(), endorse.getEndorser(), endorse.getLevel()));
			transaction.setDescription("createEndorsement");
			break;
		}

//		case "getEndorseNominations": {
//			userId = (String) transaction.getObject();
//			transaction.setObject(this.getEndorseNominations(userId));
//			transaction.setDescription("getEndorseNominations");
//			break;
//		}

		case "getSkillsReport": {
			transaction.setObject(this.getSkillsReport());
			transaction.setDescription("getSkillsReport");
			break;
		}

		case "getUsersPerSkill": {
			skillName = transaction.getObject().toString();
			transaction.setObject(this.searchSkills(skillName));
			transaction.setDescription("SearchSkills");
			break;
		}

		case "getUserEndorsementsPerSkill": {
			skillId = Integer.parseInt(transaction.getObject().toString());
			transaction.setObject(this.getUserEndorsementsPerSkill(skillId));
			transaction.setDescription("getUserEndorsementPerSkill");
			break;
		}

		default:
			System.out.println("Incorrect selection");

		}

		return transaction;

	}

	public boolean validateUser(String userId) {
		this.userId = userId;
		try {
			RS = skillsDB.queryDB("select * from user where user_id = '" + userId + "'");
			if (!RS.next()) {

				System.out.println("user does not exist");
				return false;
			} else {

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<User> getUser(String userId) {
		//
		UserList = new ArrayList<User>();
		if (validateUser(userId)) {

			try {
				// Fetch from database

				userResult = skillsDB.queryDB("select user_id, first_name, "
						+ "surname, alias_name, email, phone_num from user where user_id = '" + userId + "'");

				// Write to ArrayList
				userResult.next();
				String userID = userResult.getString("user_id");
				String firstName = userResult.getString("first_name");
				String surname = userResult.getString("surname");
				String aliasName = userResult.getString("alias_name");
				String email = userResult.getString("email");
				String phoneNum = userResult.getString("phone_num");

				UserList.add(new User(userID, firstName, surname, aliasName, email, phoneNum));
			} catch (SQLException se) {
				System.out.println("ERROR: " + se.getMessage());
				return null;
			}
			return UserList;
		} else {
			return UserList = null;

		}
	}

	public ArrayList<User> getUserList(String userId) {
		UserList = new ArrayList<User>();
		this.userId = userId;

		try {
			// Fetch from database

			userResult = skillsDB.queryDB("select * from user where user_id like '" + userId + "%'"
					+ " or first_name like '" + userId + "%' or surname like '" + userId + "%'");

			// Write to ArrayList
			while (userResult.next()) {
				String userID = userResult.getString("user_id");
				String firstName = userResult.getString("first_name");
				String surname = userResult.getString("surname");
				String aliasName = userResult.getString("alias_name");
				String email = userResult.getString("email");
				String phoneNum = userResult.getString("phone_num");

				UserList.add(new User(userID, firstName, surname, aliasName, email, phoneNum));
			}
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return UserList;

	}

	public boolean deleteUserSkill(int userSkillId) {
		this.userSkillId = userSkillId;

		try {
			skillsDB.updateDB("delete from endorsement where user_skill_id = " + userSkillId);
			skillsDB.updateDB("delete from user_skill where user_skill_id = " + userSkillId);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<RatedSkills> getUserSkills(String userId) {
		this.userId = userId;
		userSkillList = new ArrayList<RatedSkills>();

		try {
			// Fetch from database

			userSkillResult = skillsDB.queryDB("Select user_id, user_skill_id, skill_id, level, "
					+ "num_of_endorsements, avg_endorsement, skill_name from v_user_skill_endorsements where user_id = '"
					+ userId + "'");

			// Write to ArrayList
			while (userSkillResult.next()) {
				userSkillId = userSkillResult.getInt("user_skill_id");
				userId = userSkillResult.getString("user_id");
				skillId = userSkillResult.getInt("skill_id");
				level = userSkillResult.getInt("level");
				numEndorsement = userSkillResult.getInt("num_of_endorsements");
				skillName = userSkillResult.getString("skill_name");
				avgEndorsement = userSkillResult.getBigDecimal("avg_endorsement");
				userSkillList.add(new RatedSkills(skillId, userSkillId, level, numEndorsement, userId, skillName,
						avgEndorsement));
			}
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return userSkillList;
	}

	public ArrayList<Skill> searchSkills(String skillName) {
		this.skillName = skillName;
		skillList = new ArrayList<Skill>();

		try {
			// Fetch from database

			skillResult = skillsDB.queryDB(
					"select skill_id, skill_name from skills" + " where skill_name like ('" + skillName + "%')");

			// Write to ArrayList
			while (skillResult.next()) {
				int skillId = skillResult.getInt("skill_id");
				skillName = skillResult.getString("skill_name");

				skillList.add(new Skill(skillId, skillName));
			}

		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillList;
	}

	public boolean deleteSkill(int skillId) {
		this.skillId = skillId;
		try {
			RS = skillsDB.queryDB("select * from skills where skill_id = '" + skillId + "'");
			if (RS.next()) {
				skillsDB.updateDB("delete from skills where skill_id = " + skillId);
				System.out.println("BC  after DB delete");
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean addSkill(String skillName) {
		this.skillName = skillName;
		try {
			skillsDB.updateDB("insert into skills values(null,'" + skillName + "')");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean endorseNomination(int userSkillId, String userId) {
		this.userSkillId = userSkillId;
		this.userId = userId;
		try {
			skillsDB.updateDB(
					"insert into endorsement_nomination values(null, " + userSkillId + ",'" + userId + "',null)");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public ArrayList<Skill> getSkillList() {
		//
		skillList = new ArrayList<Skill>();

		try {
			// Fetch from database

			skillResult = skillsDB.queryDB("SELECT skill_id, skill_name from skills");

			// Write to ArrayList
			while (skillResult.next()) {
				skillId = skillResult.getInt("skill_id");
				skillName = skillResult.getString("skill_name");
				skillList.add(new Skill(skillId, skillName));
			} // End while
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillList;
	}

	public boolean SaveUserSkill(String userId, int skillId, int level) {
		this.userId = userId;
		this.skillId = skillId;
		this.level = level;

		try {
			skillsDB = new MySkillsDAO();
			RS = skillsDB
					.queryDB("SELECT * from user_skill where user_id = '" + userId + "' and skill_id = " + skillId);

			if (!RS.next()) {
				skillsDB.updateDB(
						"insert into user_skill values(null, '" + userId + "', " + skillId + ", " + level + ", null)");

			} else

				skillsDB.updateDB("update user_skill set level = " + level + " where user_id = '" + userId
						+ "' and skill_id = " + skillId);
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean createEndorsement(int userSkillId, String endorser, int level) {
		this.userSkillId = userSkillId;
		this.endorsor = endorser;
		this.level = level;
		try {
			skillsDB.updateDB("INSERT INTO endorsement VALUES (null, " + userSkillId + ",'" + endorsor + "'," + level
					+ ", null)");

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
		return true;
	}

	public ArrayList<EndorsementsPerSkill> getSkillsReport() {
		//
		skillReport = new ArrayList<EndorsementsPerSkill>();

		try {
			// Fetch from database

			skillResult = skillsDB.queryDB("SELECT skill_id, skill_name, num_of_resources, num_of_endorsements,"
					+ " avg_endorsement  from v_endorsements_per_skill");

			// Write to ArrayList
			while (skillResult.next()) {
				skillId = skillResult.getInt("skill_id");
				skillName = skillResult.getString("skill_name");
				avgEndorsement = skillResult.getBigDecimal("avg_endorsement");
				numOfEndorsements = skillResult.getBigDecimal("num_of_endorsements");
				numOfResources = skillResult.getLong("num_of_resources");

				skillReport.add(new EndorsementsPerSkill(skillId, numOfResources, skillName, numOfEndorsements,	avgEndorsement));
			} // End while
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillReport;
	}

	public ArrayList<UserSkillEndorsements> getUserEndorsementsPerSkill(int skillId) {
		this.skillId = skillId;
		ratedSkillsList = new ArrayList<UserSkillEndorsements>();

		try {
			// Fetch from database

			userResult = skillsDB.queryDB("Select user_id, first_name, surname,  user_skill_id, skill_id, level, "
					+ "num_of_endorsements, avg_endorsement, skill_name from v_user_skill_endorsements where skill_id = '"
					+ skillId + "'");

			while (userResult.next()) {
				int userSkillId = userResult.getInt("user_skill_id");
				String userId = userResult.getString("user_id");
				skillId = userResult.getInt("skill_id");
				level = userResult.getInt("level");
				numEndorsement = userResult.getInt("num_of_endorsements");
				skillName = userResult.getString("skill_name");
				firstName = userResult.getString("first_name");
				surname = userResult.getString("surname");
				avgEndorsement = userResult.getBigDecimal("avg_endorsement");
				ratedSkillsList.add(new UserSkillEndorsements(firstName, surname, numEndorsement, avgEndorsement,skillName ));

			}
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return ratedSkillsList;
	}
}
