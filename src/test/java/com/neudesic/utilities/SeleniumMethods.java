package com.neudesic.utilities;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumMethods {

	public static int implicitWaitTime = 10;

	/***************************************************************************************
	 * Go to an URL
	 * 
	 * @param driver The Selenium Web Driver object.
	 * @param url    Website URL
	 ****************************************************************************************/
	public static void visitToURL(WebDriver driver, String url) {
		driver.get(url);
	}

	/***************************************************************************************
	 * Maximizes the window.
	 * 
	 * @param driver The Selenium Web Driver object.
	 ***************************************************************************************/
	public static void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/***************************************************************************************
	 * Refreshes the window.
	 * 
	 * @param driver The Selenium Web Driver object.
	 ***************************************************************************************/
	public static void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	/***************************************************************************************
	 * Quits the browser.
	 * 
	 * @param driver The Selenium Web Driver object.
	 ***************************************************************************************/
	public static void quit(WebDriver driver) {
		driver.quit();
	}

	/***************************************************************************************
	 * Send input string to an element - It will try to send the text based on the
	 * attempts count that we give.
	 * 
	 * @param driver             The Selenium Web Driver object.
	 * @param elementDescription Name of the element
	 * @param by                 The locator in which to search for an element.
	 * @param valueToSend        Text to enter
	 ***************************************************************************************/
	public static void enterInput(WebDriver driver, String elementDescription, By by, String valueToSend,
			boolean clickFirst) {
		RuntimeException latestException = null;

		int maxAttempts = 3;
		int waitMillisecondsBetweenAttempts = 1000;

		for (int attempts = 0; attempts < maxAttempts; attempts++) {
			try {
				WebElement element = driver.findElement(by);
				element.sendKeys(valueToSend);
				return;
			} catch (RuntimeException e) {
				latestException = e;
				try {
					Thread.sleep(waitMillisecondsBetweenAttempts);
				} catch (InterruptedException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}

		throw new RuntimeException("Unable to send " + valueToSend + " to element " + elementDescription,
				latestException);
	}

	/***************************************************************************************
	 * Enters the text char by char.
	 * 
	 * @param driver      The Selenium Web Driver object.
	 * @param by          The locator in which to search for an element.
	 * @param valueToSend Text to enter
	 ***************************************************************************************/
	public static void enterTextIndividually(WebDriver driver, By by, String valueToSend) {
		WebElement element = driver.findElement(by);
		for (int i = 0; i < valueToSend.length(); i++) {
			char c = valueToSend.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
		}
	}

	/***************************************************************************************
	 * 
	 * @param driver             The Selenium Web Driver object.
	 * @param elementDescription Name of the element
	 * @param by                 The locator in which to search for an element.
	 ***************************************************************************************/
	public static void clickSimple(WebDriver driver, String elementDescription, By by) {
		RuntimeException latestException = null;

		int maxAttempts = 3;
		int waitMillisecondsBetweenAttempts = 1000;

		for (int attempts = 0; attempts < maxAttempts; attempts++) {
			try {
				WebElement webElement = driver.findElement(by);
				webElement.click();
				return;
			} catch (RuntimeException e) {
				
				latestException = e;
				try {
					Thread.sleep(waitMillisecondsBetweenAttempts);
				} catch (InterruptedException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}

		throw new RuntimeException("Click of " + elementDescription + " failed continuously for all attempts.",
				latestException);
	}

	/***************************************************************************************
	 * Click on an element using JSExecutor
	 * 
	 * @param driver             The Selenium Web Driver object.
	 * @param elementDescription Name of the element
	 * @param element            The locator in which to search for an element.
	 ***************************************************************************************/
	public static void clickJS(WebDriver driver, String elementDescription, By element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement ele = driver.findElement(element);
			js.executeScript("arguments[0].click();", ele);
			System.out.println("[INFO] Successfully clicked with clickJS method on: " + elementDescription);
		} catch (Exception e) {
			throw new RuntimeException("Exception thrown while trying to click on: " + elementDescription
					+ " [Error Message]: " + e.getMessage());
		}
	}

	/***************************************************************************************
	 * Returns a boolean depending on the whether an element exists on the page. If
	 * the element exists, it will return true, otherwise false.
	 *
	 * @param driver The Selenium Web Driver object.
	 * @param by     The locator in which to search for an element.
	 ***************************************************************************************/
	public static boolean elementIsDisplayed(WebDriver driver, By by) {
		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			int maxAttempts = 3;
			int waitMillisecondsBetweenAttempts = 200;

			for (int attempts = 0; attempts < maxAttempts; attempts++) {
				try {
					WebElement element = driver.findElement(by);
					return element.isDisplayed();
				} catch (RuntimeException e) {
					try {
						Thread.sleep(waitMillisecondsBetweenAttempts);
					} catch (InterruptedException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
			return false;
		} catch (RuntimeException e2) {
			return false;
		} finally {
			try {
				driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.MILLISECONDS);
			} catch (RuntimeException e3) {
				System.out.println(e3.getMessage());
			}
		}
	}

}