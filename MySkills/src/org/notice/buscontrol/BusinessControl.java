package org.notice.buscontrol;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.plaf.SliderUI;

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
	private Long numOfResources = 0L, numOfEndorsement = 0L;
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
		//	System.out.println("in case delete " + skillId);
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

		case "createEndorsementNomination": {
			endorseNom = (EndorsementNomination) transaction.getObject();
			transaction.setObject(this.createEndorseNomination(endorseNom.getUserID(), endorseNom.getEndorserUserID()));
			transaction.setDescription("createEndorsement");
			break;
		}

		case "getEndorseNominations": {
			userId = (String) transaction.getObject();
			transaction.setObject(this.getEndorseNominations(userId));
			transaction.setDescription("getEndorseNominations");
			break;
		}

		case "deleteEndorsementNomination": {
			endorseNom = (EndorsementNomination) transaction.getObject();
			transaction.setObject(this.deleteEndorseNomination(endorseNom.getUserID(), endorseNom.getEndorserUserID()));
			transaction.setDescription("deleteEndorsement");
			break;
		}

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
			ArrayList<Skill> skills = (ArrayList<Skill>) transaction.getObject();
			// skillId = Integer.parseInt(transaction.getObject().toString());
			transaction.setObject(this.getUserEndorsementsPerSkill(skills));
			transaction.setDescription("getUserEndorsementPerSkill");
			break;
		}

		case "getSkillRatingDistribution": {
			Skill skill = (Skill) transaction.getObject();

			transaction.setObject(this.getSkillRatingDistribution(skill));
			transaction.setDescription("getSkillRatingdistrubution");
			break;
		}

		case "getSkillDistribution": {
			Skill skill = (Skill) transaction.getObject();

			transaction.setObject(this.getSkillDistribution(skill));
			transaction.setDescription("getSkillDistribution");
			break;
		}
		
		case "getColleagueProfile": {
		//	System.out.println("getColleagueProfile");
			ArrayList<User> users = (ArrayList<User>) transaction.getObject();

			transaction.setObject(this.getColleagueProfile(users));
			transaction.setDescription("getColleagueProfile");
			break;
		}
		//    getColleagueProfile(ArrayList<User> users)

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

	public ArrayList<ColleagueRatings> getColleagueProfile(ArrayList<User> users) {
		String endorsee1 = users.get(1).getUserID();
		String endorsor1 = users.get(0).getUserID();

		String selectStatement = " SELECT    a.user_id,   "
				+ "    a.user_skill_id,   a.skill_id,"
				+ "    a.level,   a.num_of_endorsements, a.avg_endorsement,    a.skill_name,"
				+ "    COALESCE(e.level, 0) my_rating" + " FROM " + "    myskills.v_user_skill_endorsements a"
				+ "        LEFT OUTER JOIN" + "    (SELECT " + "        user_skill_id, level" + "    FROM   "
				+ "        endorsement" + "    WHERE " + "        endorsor = '" + endorsor1
				+ "') e ON a.user_skill_id = e.user_skill_id " +

				"Where a.user_id = '" + endorsee1 +"'";
		//System.out.println(selectStatement);
		ArrayList<ColleagueRatings> ratings = new ArrayList<ColleagueRatings>();

		
		try {
			userSkillResult = skillsDB.queryDB(selectStatement);
			while (userSkillResult.next()) {
				userSkillId = userSkillResult.getInt("user_skill_id");
				userId = userSkillResult.getString("user_id");
				skillId = userSkillResult.getInt("skill_id");
				level = userSkillResult.getInt("level");
				numEndorsement = userSkillResult.getInt("num_of_endorsements");
				skillName = userSkillResult.getString("skill_name");
				avgEndorsement = userSkillResult.getBigDecimal("avg_endorsement");
				int myRating =  userSkillResult.getInt("my_rating");
				
				ratings.add(new ColleagueRatings(userId, skillName, userSkillId, skillId, level, numEndorsement, myRating, avgEndorsement.doubleValue())) ; 
				/* 
				 * String userId, firstName, surname, aliasName, skillName; 
	int userSkillId, skillId, level, numOfEndorsements, myRating;
	double avgEndorsement;
				 * */
				
				
			}

		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());

			return null;
		}
		//System.out.println("Coll rating size "  + ratings.size());
		return ratings;
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

	private ArrayList<SkillRatingDistribution> getSkillRatingDistribution(Skill skill) {
		int skillId = skill.getSkillID();
		ArrayList<SkillRatingDistribution> ratings = new ArrayList<SkillRatingDistribution>();
		String selectStatement = "select * from v_skill_rating_distribution where skill_id = " + skillId;
		try {
			userSkillResult = skillsDB.queryDB(selectStatement);
			while (userSkillResult.next()) {
				skillId = userSkillResult.getInt("skill_id");
				skillName = userSkillResult.getString("skill_name");
				userSkillId = userSkillResult.getInt("user_skill_id");
				double averageRating = userSkillResult.getDouble("average_rating");
				ratings.add(new SkillRatingDistribution(skillId, skillName, userSkillId, averageRating));

			}

		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;

		}
		return ratings;

	}

	private ArrayList<SkillDistribution> getSkillDistribution(Skill skill) {
		int skillId = skill.getSkillID();
		ArrayList<SkillDistribution> ratings = new ArrayList<SkillDistribution>();
		String selectStatement = "select * from v_skill_distribution where skill_id = " + skillId
				+ " order by user_value asc ";
		try {
			userSkillResult = skillsDB.queryDB(selectStatement);
			while (userSkillResult.next()) {
				skillId = userSkillResult.getInt("skill_id");
				skillName = userSkillResult.getString("skill_name");

				userId = userSkillResult.getString("user_id");
				firstName = userSkillResult.getString("first_name");
				surname = userSkillResult.getString("surname");
				String aliasName = userSkillResult.getString("alias_name");
				skillName = userSkillResult.getString("first_name");
				Double userValue = userSkillResult.getDouble("user_value");

				ratings.add(
						new SkillDistribution(userId, firstName, surname, aliasName, skillName, skillId, userValue));

			}

		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;

		}
		return ratings;

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
			RS = skillsDB.queryDB("select * from user_skill where skill_id = '" + skillId + "'");
			if (!RS.next()) {
				skillsDB.updateDB("delete from skills where skill_id = " + skillId);
			//	System.out.println("BC  after DB delete");
			} else {
			//	System.out.println("BC   no RS next");
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

	public boolean createEndorseNomination(String userId, String endorserId) {
		//System.out.println("in business cxontrol create endorsement");
		this.endorsor = endorserId;
		this.userId = userId;
		try {
			skillsDB.updateDB(
					"insert into endorsement_nomination values(null, '" + endorsor + "','" + userId + "',null)");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean deleteEndorseNomination(String userId, String endorserId) {

		this.endorsor = endorserId;
		this.userId = userId;
		try {
			skillsDB.updateDB("delete from  endorsement_nomination where user_id = '" + userId
					+ "' and  nominated_endorsee = '" + endorsor + "';");
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
			skillsDB = new MySkillsDAO();// Added by Tony
			RS = skillsDB.queryDB("SELECT * from endorsement where user_skill_id = " + userSkillId + " and endorsor = '"
					+ endorser + "';");// Added by tony
			if (!RS.next()) {
				skillsDB.updateDB("INSERT INTO endorsement VALUES (null, " + userSkillId + ",'" + endorsor + "',"
						+ level + ", null)");// Moved into if statement by Tony
			} else {
				skillsDB.updateDB("update endorsement set level = " + level + " where user_skill_id = " + userSkillId
						+ " and  endorsor = '" + endorser + "'");// Added by Tony
			}

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
					+ " avg_endorsement  from v_endorsements_per_skill order by avg_endorsement desc");

			// Write to ArrayList
			while (skillResult.next()) {
				skillId = skillResult.getInt("skill_id");
				skillName = skillResult.getString("skill_name");
				avgEndorsement = skillResult.getBigDecimal("avg_endorsement");
				numOfEndorsements = skillResult.getBigDecimal("num_of_endorsements");
				numOfResources = skillResult.getLong("num_of_resources");

				skillReport.add(new EndorsementsPerSkill(skillId, numOfResources, skillName, numOfEndorsements,
						avgEndorsement));
			} // End while
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return skillReport;
	}

	public ArrayList<UserSkillEndorsements> getUserEndorsementsPerSkill(ArrayList<Skill> skillList) {
		this.skillList = skillList;
		ratedSkillsList = new ArrayList<UserSkillEndorsements>();

		try

		{
			String userIdList = null;
			for (int pos = 0; pos < skillList.size(); pos++) {
				//System.out.println("pos" + pos);
				if (pos == 0) {
					userIdList = "" + skillList.get(pos).getSkillID();
				} else {
					userIdList = userIdList + ", " + skillList.get(pos).getSkillID();
				}
				//System.out.println("Array  Length " + skillList.size());
			}

			String sQL = null;
			sQL = "SELECT  * FROM myskills.v_user_skill_endorsements a, (SELECT user_id FROM myskills.user_skill WHERE skill_id IN ("
					+ userIdList + ") GROUP BY user_id HAVING COUNT(*) = " + skillList.size()
					+ " ) b WHERE skill_id IN (" + userIdList
					+ ") AND b.user_id = a.user_id order by avg_endorsement desc;";
			{
				userResult = skillsDB.queryDB(sQL);

				while (userResult.next()) {
					int userSkillId = userResult.getInt("user_skill_id");
					String userId = userResult.getString("user_id");
					skillId = userResult.getInt("skill_id");
					level = userResult.getInt("level");
					numOfEndorsement = userResult.getLong("num_of_endorsements");
					skillName = userResult.getString("skill_name");
					firstName = userResult.getString("first_name");
					surname = userResult.getString("surname");
					avgEndorsement = userResult.getBigDecimal("avg_endorsement");
					ratedSkillsList.add(new UserSkillEndorsements(userId, firstName, surname, " ", " ", " ", skillName,
							0, skillId, level, numOfEndorsement, avgEndorsement));

				}
			}
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return ratedSkillsList;
	}

	private Object getEndorseNominations(String userId2) {
		ArrayList<EndorsementNomination> nominations = new ArrayList<EndorsementNomination>();
		String selectString = "SELECT * FROM myskills.v_expanded_endorsement_nomination where nominated_endorsee = '"
				+ userId2 + "'";

		try {
			userResult = skillsDB.queryDB(selectString);
			while (userResult.next()) {
				nominations.add(new EndorsementNomination(userResult.getInt("endorsement_nomination_id"),
						userResult.getString("user_id"), userResult.getString("fullname"),
						userResult.getString("nominated_endorsee")));
			}
		} catch (SQLException se) {
			System.out.println("ERROR: " + se.getMessage());
			return nominations;
		}

		return nominations;
	}

}
