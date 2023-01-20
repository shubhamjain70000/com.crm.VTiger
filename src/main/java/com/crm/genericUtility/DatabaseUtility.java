package com.crm.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	Connection con=null;
	/**
	 * This method is used to connect to the database
	 * @author admin
	 * @throws Throwable
	 */
	public void connectionToDB() throws Throwable
	{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection(IPathConstant.DBURL,IPathConstant.DBUSERNAME,IPathConstant.DBPASSWORD);
    }
	/**
	 * This method is used to execute query
	 * @author admin
	 * @param query
	 * @param coloumnIndex
	 * @param expdata
	 * @return
	 * @throws Throwable
	 */
	public String executeQueryandgetdata(String query, int coloumnIndex,String expdata) throws Throwable
	{
		Statement state = con.createStatement();
		ResultSet result = state.executeQuery(query);
		boolean flag=false;
		while(result.next())
		{
			String data = result.getString(coloumnIndex);
			System.out.println(data);
			if(data.equalsIgnoreCase(expdata))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println(expdata+" project is created ");
			return expdata;
		}
		else
		{
			System.out.println("project is not created");
			return "";
		}
	}
	public void closeDB() throws Throwable
	{
		//to close database connections
		con.close();
	}

}
