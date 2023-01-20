package practice2_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class NonSelecteQueryTest {

	public static void main(String[] args) throws SQLException {
		Connection con=null;
		int result=0;
		try {
		Driver driver=new Driver();
		//Steps 1: Register to the driver
		DriverManager.registerDriver(driver);
		
		//Steps 2: Connection to the Database
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet45","root","root");
		
		//Steps 3: Issue create statement
		Statement state=con.createStatement();
		String query="insert into studentInfo values('Amit','BTM','Manual',3);";
		
		//Steps 4: execute query
		result=state.executeUpdate(query);
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			if(result==1)
			{
				System.out.println("data inserted successfully!!");
			}
			else
			{
				System.out.println("dta not isnerted!!");
			}
			//Steps 5:  close the database
			con.close();
			
		}
		
		
		
		
		

	}

}
