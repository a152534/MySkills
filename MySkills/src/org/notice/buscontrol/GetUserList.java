package org.notice.buscontrol;

import java.sql.*;
import java.util.ArrayList;

import org.notice.beans.User;
import org.notice.dao.MySkillsDAO;

public class GetUserList
{
	private MySkillsDAO userAccess = null;
	private ArrayList<User> UserList = null;
	private Connection userConnect = null;
	private Statement Usertate = null;
	private ResultSet userResult = null;
	
	public GetUserList() throws Exception
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
			userResult = Usertate.executeQuery("select * from user where user_id like '" + user_ID + "%'" +
					 " or first_name like '" + user_ID +  "%' or surname like '" + user_ID + "%'");
			
			//Write to ArrayList
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
