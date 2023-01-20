package practice2_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabase {

	public static void main(String[] args) throws SQLException 
	{
		Connection con=null;
		
		try {
		Driver driver=new Driver();
		//Steps 1: Register to the Database
		DriverManager.registerDriver(driver);
		
		//Steps 2: Connections to The database
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet45","root","root");
		
		//Steps 3: Issue Create statements
		Statement state=con.createStatement();
		String query="select * from studentInfo where Sname='Anoop';";
		
		//Steps 4: Execute query
		ResultSet result=state.executeQuery(query);
		while(result.next()) {
		System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4));	
		}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
		     //Steps 5: close the database
		     con.close();
		     System.out.println("database closed successfully!!");
		
		}
		
		

	}

}
