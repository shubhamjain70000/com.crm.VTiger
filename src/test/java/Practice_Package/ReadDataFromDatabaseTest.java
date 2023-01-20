package Practice_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabaseTest {
	

	public static void main(String[] args) throws SQLException {
		
		Connection con=null;
		try {
		Driver driver=new Driver();//import from mysql.cj.jdbc
		
		//Steps:1 Register to the database
		DriverManager.registerDriver(driver);
		
		//Steps:2 Connection to the database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet45","root","root");
		
		//Steps:3 Issue Create Statement
		Statement state=con.createStatement();
		String query="select * from studentInfo;";
		
		//Steps:4 Execute Query
		ResultSet result = state.executeQuery(query);
		
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4));
		}
		}
		catch(Exception e) {
			
		}
		
		
		finally
		{
	       //Steps:5 Close the databases
			con.close();
			System.out.println("close the database connection successfully!!");
		}
		

	}

}
