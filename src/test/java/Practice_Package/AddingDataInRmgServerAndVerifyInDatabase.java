package Practice_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddingDataInRmgServerAndVerifyInDatabase {

	public static void main(String[] args) throws SQLException {
		WebDriver driver;
		Connection con=null;
		Statement state=null;
		String Projectname="dream11";
		try
		{
			
		driver=WebDriverManager.chromedriver().create();
		
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			//launching the browser
			driver.get("http://rmgtestingserver:8084");
			
			
			driver.findElement(By.id("usernmae")).sendKeys("rmgyantra",Keys.TAB,"rmgy@9999");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			//click on project
			driver.findElement(By.linkText("Projects")).click();
			
			//click on create project
			driver.findElement(By.xpath("//span[text()='Create Project']")).click();
			
			//projectname
			driver.findElement(By.name("projectName")).sendKeys("Vtiger");
			
			//project manager
			driver.findElement(By.name("createdBy")).sendKeys("Deepak");
			
			//project status
			WebElement ps=driver.findElement(By.xpath("//label[text()='Project Status ']/following-sibling::select[1]"));
			
			//using select class
			Select s=new Select(ps);
			s.selectByVisibleText("Created");
			
			//add projects
			driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
			
			//validation of database//
			
			Driver driver1=new Driver();
			
			//steps 1: register to the database
			DriverManager.registerDriver(driver1);
			//steps 2: connection to the database
			con=DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
			//steps 3: issue create statement
			state=con.createStatement();
			String query="select * from project;";
			//steps 4: execute query
			ResultSet result=state.executeQuery(query);
			boolean flag=false;
			while(result.next())
			{
				String actualProject=result.getString(4);
				System.out.println(actualProject);
				if(actualProject.equalsIgnoreCase(Projectname))
				{
					flag=true;
					break;
				}
			}
			if(flag) {
				System.out.println("project is created");
			}
			else {
				System.out.println("Project is not created");
			}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			con.close();
			System.out.println("DB is closed");
		}
		
			
			

	}

}
