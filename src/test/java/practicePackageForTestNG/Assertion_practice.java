package practicePackageForTestNG;

import org.testng.Reporter;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class Assertion_practice {
	
	@Test
	public void sample_01()
	{
		System.out.println("--Test Script 1--");
		System.out.println("--Test Script 2--");
		assertEquals("A", "B","Script is failed");
		System.out.println("--Test Script 3--");
		System.out.println("--Test Script 4--");
	}
	@Test
	public void sample_02()
	{
		System.out.println("--Test Script 5--");
		System.out.println("--Test Script 6--");
		String i="India";
		String a="Ind";
		assertTrue(i.contains(a));
		Reporter.log("Test script passed ",true);
		System.out.println("--Test Script 7--");
		System.out.println("--Test Script 8--");
	}
	@Test
	public void sample_03()
	{
		String s=null;
		assertNull(s,"Test script is Passed");
	}
	@Test
	public void sample_04()
	{
		String s=null;
		assertNotNull(s,"Test script is Failed");
	}
	@Test
	public void sample_05()
	{
		String s="shubham";
		String s1="sparsh";
		assertTrue(s.contains(s1));
	}


}
