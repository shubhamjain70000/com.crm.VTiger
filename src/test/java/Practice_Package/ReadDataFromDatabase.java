package Practice_Package;

import com.crm.genericUtility.DatabaseUtility;

public class ReadDataFromDatabase {

	public static void main(String[] args) throws Throwable {
		
		DatabaseUtility dLib=new DatabaseUtility();
		dLib.connectionToDB();
		
	    String query="select * from studentInfo;";
	    dLib.executeQueryandgetdata(query, 1,"Anoop");
	    
	    dLib.closeDB();

	}

}
