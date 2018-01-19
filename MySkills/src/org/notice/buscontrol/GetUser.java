package org.notice.buscontrol;

import java.sql.*;
import java.util.ArrayList;

import org.notice.beans.User;
import org.notice.dao.MySkillsDAO;

public class GetUser
{
	private MySkillsDAO userAccess = null;
	private ArrayList<User> UserList = null;
	private Connection userConnect = null;
	private Statement Usertate = null;
	private ResultSet userResult = null;
	
	public GetUser() throws Exception
	{
		userAccess = new MySkillsDAO();
	}
	
	public ArrayList<User> getUser(String user_ID)
	{
//		this.getCon();
		userAccess.getCon();
		UserList = new ArrayList<User>();
		
		try
		{
			//Fetch from database
			Usertate = userConnect.createStatement();
			userResult = Usertate.executeQuery("select user_id, first_name, "
					+ "surname, alias_name, email, phone_num from user where user_id = '" + user_ID + "'");
			
			//Write to ArrayList
			userResult.next();
			String userID = userResult.getString("user_id");
			String firstName = userResult.getString("first_name");
			String surname = userResult.getString("surname");
			String aliasName = userResult.getString("alias_name");
			String email = userResult.getString("email");
			String phoneNum = userResult.getString("phone_num");
			 
			UserList.add(new User(userID, firstName, surname, aliasName, email, phoneNum));
		} catch (SQLException se)
		{
			System.out.println("ERROR: " + se.getMessage());
			return null;
		}
		return UserList;
	}
	
}
	
	