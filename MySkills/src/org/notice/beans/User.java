package org.notice.beans;


import java.io.Serializable;

public class User implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String userID = null;
	private String firstName = null;
	private String surName = null;
	private String aliasName = null;
	public String email = null; 
	public String phoneNumber = null;
	
	public User()
	{
		
	}
	
	public User(String userID, String firstName, String surName, String aliasName,
			String email, String phoneNumber)
	{
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.surName = surName; 
		this.aliasName = aliasName;
		this.email = email;
		this.phoneNumber = phoneNumber ; 
	}
	public String getAliasName()
	{
		return aliasName;
	}
	public void setAliasName(String aliasName)
	{
		this.aliasName = aliasName;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	public String getUserID()
	{
		return userID;
	}
	public void setUserID(String userID)
	{
		this.userID = userID;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getSurName()
	{
		return surName;
	}
	public void setSurName(String surName)
	{
		this.surName = surName;
	}
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}



}