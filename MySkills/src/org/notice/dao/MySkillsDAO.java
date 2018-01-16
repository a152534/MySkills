package org.notice.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

public class MySkillsDAO
{
    String url = "jdbc:mysql://localhost:3306/myskills";
    String user = "root", uName, password, sql;
    String passwd = "Delano@1";
    Connection con = null;

    private Statement sqlstat = null;
    ResultSet RS = null;

    public MySkillsDAO() throws Exception
    {

	try
	{
	    Class.forName("com.mysql.jdbc.Driver");
	    con = DriverManager.getConnection(url, user, passwd);
	    System.out.println("connected");
	} catch (ClassNotFoundException e)
	{
	    System.out.println(" No driver found");
	    return;
	}

	sqlstat = con.createStatement();
	RS = sqlstat.executeQuery("Select *  from user");
	
	
	
    }

    public boolean updateDB(String sql)
    {

	try
	{
	    sqlstat.executeUpdate(sql);
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return true;

    }

    public void queryDB(String sql)
    {

	try
	{
	    sqlstat.executeQuery(sql);
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
    
    public Connection getCon()
    {
	return con;
    }

    public void closeDB()
    {
	try
	{
	    con.close();
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
    
    public static void main(String[] args)
    {
	try
	{
	    new MySkillsDAO();
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
