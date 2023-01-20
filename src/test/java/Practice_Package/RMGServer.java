package Practice_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class RMGServer {

	public static void main(String[] args) throws SQLException {
		 Connection con= null;
		 int result=0;
		 try {
		 Driver driver=new Driver();
		//Steps 1: Register to the database
		DriverManager.registerDriver(driver);
		
		//steps 2: Connection to the database
        con = DriverManager.getConnection("Jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
        
        //steps 3: Issue create statement
        Statement state = con.createStatement();
        String query="insert into project values('TY_Project_789','Shubham','21/12/2022','Vtiger','created',4);";
        
        //Steps 4: update query
        result = state.executeUpdate(query);
		 } 
		 catch(Exception e)
		 {
			 
		 }
		 finally
		 {
			 if(result==1)
			 {
				 System.out.println("data stored successfully");
			 }
			 else
			 {
				 System.out.println("data not entered");
			 }
		//Steps 5: close the database
			 con.close();
		 }
        
        
	}

}
