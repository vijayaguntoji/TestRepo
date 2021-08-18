package com.aff.dp.testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aff.dp.pageObjects.AccountListPage;
import com.aff.dp.pageObjects.LoginPage;
import com.aff.main.TestBase;
import com.aff.utilities.SeleniumMethods;

public class AccountListPageTest extends TestBase {
	
	AccountListPage accountlistpage = new AccountListPage();
	LoginPage loginPage = new LoginPage();
	@BeforeClass
	public void Login() {
		loginPage.login(returnDriver(), "1300", "AFF3515", "7515");
	}
	
	@BeforeMethod
	public void WaitForAccountsList () {
		SeleniumMethods.elementIsDisplayed(returnDriver(), "AccountList", accountlistpage.AccountsList);
	}
	
	@Test
	public void Dateselected() {
		accountlistpage.Datefilter(returnDriver(),1,28);
	}

	@Test
	public void Typeselected() throws IOException {
		accountlistpage.TypeFilter(returnDriver(),"Bronze");
	}

	@Test
	public void Statusselected() throws IOException {
		accountlistpage.StatusFilter(returnDriver(),"APPLIED%DENIED%APPROVED");
	}

	@Test 
	public void Filters() throws IOException  {
		accountlistpage.FilterAccountList(returnDriver(),"APPLIED%DENIED%APPROVED", 1, 28, "Bronze");
	}

	@Test
	public void RefreshList() throws IOException  {
		accountlistpage.TypeFilter(returnDriver(),"Bronze");
		accountlistpage.RefreshFilters(returnDriver());
	}

	@AfterMethod 
	public void ClearingFilters() {
		//Click on Refresh icon
		SeleniumMethods.clickSimple(returnDriver(), "Refresh icon", AccountListPage.Refreshlist);
	}
	
}

