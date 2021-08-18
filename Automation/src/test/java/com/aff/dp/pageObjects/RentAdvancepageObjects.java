package com.aff.ra.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aff.main.TestBase;
import com.aff.utilities.SeleniumMethods;

public class RentAdvancepageObjects extends TestBase{
	public By HomePagelink=By.xpath("//img[@class='d-block pb-0 nav-logo mx-auto']");
	public By aboutlink = By.xpath("//a[contains(@href,'/about')]");
	public By aboutheader1=By.xpath("//h1[contains(text(),'About Us')]");
	public By aboutheader2=By.xpath("//h4[@class='pt-5'][contains(text(),'About Us')]");
	public By aboutheader3=By.xpath("//h4[contains(text(),'What Customers Are Saying About Us')]");
	public By aboutcontent1=By.xpath("//p[contains(text(),'We’re here to help')]");
	public By aboutcontent2=By.xpath("//div[@class='text-center']//span[normalize-space(text()='Rent Advance')]");
	public By homeheader1=By.xpath("//h1[contains(text(),'Rent Paid on Time. Every Time.')]");
	public By homecontent1=By.xpath("//p[contains(text(),'Rent Advance')]");
	public By homeresidents_button=By.xpath("//a[@class='button btn btn-primary'][contains(text(),'Residents')]");
	public By homeproperty_managers_button=By.xpath("//a[@class='button btn btn-primary'][contains(text(),'Property Managers')]");
	public By homeheader2=By.xpath(".//h4[text()='Learn How We Can Help']");
	public By homeproperty_manager_button=By.xpath("//button//a[@href='/managers']");
	public By homeresident_button=By.xpath("//button//a[@href='/residents']");
	public By image=By.xpath("//img[@src='img/ra/triangle1.svg']");
	public By PropertyManagelink=By.xpath("//a[@href='/managers'][contains(text(),'Property Managers')]");
	public By PropertyManager_Header1=By.xpath("//h1[normalize-space(text()='Partner with')]");
	public By PropertyManager_content1=By.xpath("//p[contains(text(),'Rent Advance')]");
	public By PropertyManager_Join_RA_button=By.xpath("//a[@class='button btn btn-primary']");
	public By PropertyManager_Header2=By.xpath("//h4[@class='text-center mt-5 col-12' and contains(text(),' Want to know more? ')]");
	public By PropertyManager_Business_label=By.xpath("//label[contains(text(),'Business Name')]");
	public By PropertyManager_Business_Txtbox=By.xpath("//input[@placeholder='Business Name']");
	public By PropertyManager_Name_label=By.xpath("//div[@class='col-md-6 col-12']//label[contains(text(),'Your Name')]");
	public By PropertyManager_Name_Txtbox=By.xpath("//input[@placeholder='Your Full Name']");
	public By PropertyManager_Phone_Number_label=By.xpath("//label[contains(text(),'Phone Number')]");
	public By PropertyManager_Phone_Number_Txtbox=By.xpath("//input[@placeholder='Business Phone Number']");
	public By PropertyManager_Email_label=By.xpath("//label[contains(text(),'Email Address')]");
	public By PropertyManager_Email_Txtbox=By.xpath("//input[@placeholder='Business Email']");
	public By PropertyManager_Number_Of_Units_label=By.xpath("//label[contains(text(),'Number of Units Managed/Owned')]");
	public By PropertyManager_Number_Of_Units_Txtbox=By.xpath("//input[@placeholder='#']");
	public By PropertyManager_Comments_or_Questions_label=By.xpath("//label[contains(text(),'Comments or Questions')]");
	public By PropertyManager_Comments_or_Questions_Txtbox=By.xpath("//div[@class='col-12']//textareawaqq2");



	private void Verifylablestxtbox(String label) {
		switch (label) {
		case "Business Name":
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Bussiness Name label", PropertyManager_Business_label);
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Bussiness Name Textbox", PropertyManager_Business_Txtbox);
			break;
		case "Your Name":
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Your Name label", PropertyManager_Name_label);
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Your Name Textbox", PropertyManager_Name_Txtbox);
			break;
		case "Phone Number":
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Phone Number label", PropertyManager_Phone_Number_label);
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Phone Number Textbox", PropertyManager_Phone_Number_Txtbox);
			break;
		case "Email Address":
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Email Address label", PropertyManager_Email_label);
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Email Address Textbox", PropertyManager_Email_Txtbox);
			break;
		case "Number Of Units Managed/Owned":
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Number of Units Managed/Owned label", PropertyManager_Number_Of_Units_label);
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Number of Units Managed/Owned Textbox", PropertyManager_Number_Of_Units_Txtbox);
			break;
		case "Comments Or Questions":
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Comments or Questions label", PropertyManager_Comments_or_Questions_label);
			SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Comments or Questions Textbox", PropertyManager_Comments_or_Questions_Txtbox);
			break;
		}
	}


	public void AbouPageContents(WebDriver returnDriver) {
		//click on about link
		SeleniumMethods.clickSimple(returnDriver(), "about", aboutlink);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Header1", aboutheader1);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify content1", aboutcontent1);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify image", image);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Header2", aboutheader2);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify content2", aboutcontent2);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Header3", aboutheader3);
	}


	private String[] lsCardContent1 = {
			"Fast and Easy For most the application process is as simple as clicking a link and answering a few questions.",
			"RAPID FUNDING Funds can be sent directly to property managers in as little as 24 hours.*",
			"QUICK DECISIONS Our automated application process is paperless and provides decisions in seconds.",
			"FLEXIBLE ADVANCES Residents can get $300 to $5,000 to cover rent, partial rent or even a deposit.",
			"REGARDLESS OF CREDIT Our decisioning process relies on more than just a FICO® credit score to determine creditworthiness.*",
			"REAL-WORLD LOANS We provide financing that fits life, not a FICO® score."
	};

	public void ValidationOfHomePageCards() {
		List<String> lists=returnDriver().findElements(By.xpath("//div[@class='card-container col-12 mx-auto px-0 d-md-inline-flex flex-wrap justify-content-around']//h3")).stream().map(label -> label.getText()).collect(Collectors.toList());
		for (int iCard = 0; iCard<lists.size(); iCard++) { Assert.assertTrue(!(lists.get(iCard)).isEmpty());
		Assert.assertEquals(returnDriver().findElement(By.xpath(".//div[@class='cards']["+(iCard+1)+"]")).getText().toLowerCase().trim().replaceAll("\n", " ").replaceAll(",",""),lsCardContent1[iCard].toLowerCase().replaceAll(",","")); }

	}


	private String[] lsCardContent2 = {
			"GET YOUR RENT ON TIME Provide your residents an easy way to pay their rent on time. We advance from $300 - $5,000 to cover entire rent, partial rent or even a deposit.",
			"FAST FUNDING Property Managers receive funding via direct ACH deposit, next business day, after residents complete their transaction.",
			"NO RECOURSE Our service costs you nothing as the property manager, and residents pay us back over time with no recourse to you.**",
			"REDUCE COSTS Reduce your administrative and accounting costs associated with delinquent rent and turnovers.",
			"REGARDLESS OF CREDIT Our decisioning process relies on more than just a FICO® credit score to determine creditworthiness for your residents.*",
			"PAPERLESS PROCESS We can seamlessly integrate with your website, or you can send a link directly to your residents via email or text message.",
			"HOW IT WORKS FOR YOUR RESIDENT Residents pay us back in easy installments that work with their pay schedule. And, there's no prepayment penalty for paying their loan off early."
	};

	public void ValidationOfPropertyManagersPageCards() {
		List<WebElement> CardsList = new ArrayList<>();
		CardsList.add(returnDriver().findElement(By.xpath(".//img[contains(@src,'money-hand.svg')]")));
		CardsList.add(returnDriver().findElement(By.xpath(".//img[contains(@src,'calendar.svg')]")));
		CardsList.add(returnDriver().findElement(By.xpath(".//img[contains(@src,'scale.svg')]")));
		CardsList.add(returnDriver().findElement(By.xpath(".//img[contains(@src,'icon-graph.svg')]")));
		CardsList.add(returnDriver().findElement(By.xpath(".//img[contains(@src,'icon-check.svg')]")));
		CardsList.add(returnDriver().findElement(By.xpath(".//img[contains(@src,'mobile.svg')]")));
		CardsList.add(returnDriver().findElement(By.xpath(".//img[contains(@src,'icon-thumb.svg')]")));
		for (int iCard = 0; iCard <CardsList.size(); iCard++) {
			Assert.assertTrue(CardsList.get(iCard).isDisplayed());
			Assert.assertEquals(returnDriver().findElement(By.xpath(".//div[@class='cards']["+(iCard+1)+"]")).getText().toLowerCase().replaceAll("\n", " ").replaceAll(",","").trim(), lsCardContent2[iCard].toLowerCase().replaceAll(",","").trim());
		}
	}


	public void HomePageContents(WebDriver returnDriver) {
		
		SeleniumMethods.clickSimple(returnDriver(), "HomePage", HomePagelink);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Header1", homeheader1);

		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Header1", homecontent1);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Residents button", homeresidents_button);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Property managers button", homeproperty_managers_button);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify image", image);
		//Validation of cards
		ValidationOfHomePageCards();
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Header2", homeheader2);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Property Manager Button", homeproperty_manager_button);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify resident button", homeresident_button);

	}

	public void PropertyManagersPageContents(WebDriver returnDriver) {
		//Click on Property manager link
		SeleniumMethods.clickSimple(returnDriver(), "Property Manager Link", PropertyManagelink);

		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Header1",PropertyManager_Header1);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Content1",PropertyManager_content1);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Join RA button",PropertyManager_Join_RA_button);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify image", image);
		SeleniumMethods.elementIsDisplayed(returnDriver(), "Verify Want to know more? Send us your details! Header", PropertyManager_Header2);
		//verify PropertyManager
		ValidationOfPropertyManagersPageCards();

		//Verify labels and text boxes
		List<Object> labels=returnDriver().findElements(By.xpath("//form[@class='d-flex flex-wrap']//label")).stream().map(label -> label.getText()).collect(Collectors.toList());
		for(Object label:labels) {
			Verifylablestxtbox(label.toString());
		}
	}
}

