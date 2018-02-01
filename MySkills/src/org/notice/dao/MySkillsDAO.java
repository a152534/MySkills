package org.notice.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

//url=jdbc:mysql://192.168.1.101:3306/myskills
//String url = "jdbc:mysql://localhost:3306/myskills";
//dbuser=dbuser
//dbpassword=password

public class MySkillsDAO
{
    Connection con = null;
    Statement sqlstat = null;
    ResultSet RS = null;
    
    String url = null;
    String user = null;
    String passwd = null;
 
    public MySkillsDAO() throws Exception
    {
    	
    	Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            url = prop.getProperty("url");
            user = prop.getProperty("dbuser");
            passwd = prop.getProperty("dbpassword");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        

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
	    return false;
	}
	return true;

    }

    public ResultSet queryDB(String sql)
    {

	try
	{
	    RS = sqlstat.executeQuery(sql);
	               
            
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   
	}
	return RS;
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
