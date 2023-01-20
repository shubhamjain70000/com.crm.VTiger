package Practice_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class NonSelectQueryTest {

	public static void main(String[] args) throws SQLException {
		Connection con=null;
		int result=0;
		
		try {
		Driver driver=new Driver();
		//steps 1: register to the database//cj.jdbc
		DriverManager.registerDriver(driver);
		
		//steps 2: Connection to the Databases
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet45","root","root");
		
		//steps 3: Issue Create statement
		Statement state = con.createStatement();
		String query="insert into studentInfo values('Nitish','BTM','Appium',2);";
		
		//Steps: 4 Update query
		 result = state.executeUpdate(query);
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			if(result==1) {
				System.out.println("Data inserted successfully!");
			}
			else
			{
				System.out.println("Data not inserted");
			}
		
		//steps: 5 close the database
			con.close();
			
		}

	}

}
