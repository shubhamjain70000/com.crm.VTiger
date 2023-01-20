package Practice_Package;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws Throwable {
		//Steps 1: get object representation for physical file
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		
		//Steps 2: create object for Properties
		Properties pobj=new Properties();
		
		//Steps 3: load the file
		pobj.load(fis);
		
		//Steps 4: Read the data from Properties file
		String URL=pobj.getProperty("url");
		String USERNAME=pobj.getProperty("username");
		String PASSWORD=pobj.getProperty("password");
		String BROWSER=pobj.getProperty("broswer");
		
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		System.out.println(BROWSER);
		

	}

}
