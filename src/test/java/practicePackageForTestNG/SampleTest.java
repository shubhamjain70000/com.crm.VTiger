package practicePackageForTestNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class SampleTest {
	
	@Test
	public void create()
	{
		System.out.println("created");
	}
	
	@Test(dependsOnMethods="create",priority=0)
	public void update()
	{
		System.out.println("updated");
	}
	
	@Test(invocationCount=2,priority=-1)
	public void delete() {
		Reporter.log("deleted",true);
	}
	
	@Test(dependsOnMethods="delete")
	public void modify()
	{
		System.out.println("modified");
	}
	
	
}
