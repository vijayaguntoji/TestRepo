package com.aff.dp.testCases;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase{
	

	@BeforeSuite
	public void setUp() {
		TestBase.inIt("dpUrl");
	}
	
	@Test
	public void A() {
		System.out.println("123");
		throw new RuntimeException("");
	}
	
	@Test
	public void Verify_Page() {
		System.out.println("123");
	}
	
	@Test
	public void Verify_Pagse() {
		System.out.println("123");
	}
	
	@Test
	public void Verify_Pag22e() {
		System.out.println("123");
	}
	
	@Test
	public void Verify_Pagde() {
		System.out.println("123");
	}

}
