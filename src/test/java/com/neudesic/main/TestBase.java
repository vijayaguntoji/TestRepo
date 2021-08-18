package com.neudesic.main;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.neudesic.utilities.SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ThreadLocal<WebDriver> tLocal = new ThreadLocal<WebDriver>();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	private static Properties config = new Properties();
	private static FileInputStream fis;

	public WebDriver getDriver() {
		return tLocal.get();
	}

	public static void setDriver(WebDriver driver) {
		tLocal.set(driver);
	}

	/***************************************************************************************
	 * This is an initialization method that should be called before every test
	 * execution.
	 * 
	 * @param browser
	 ***************************************************************************************/
	public static void inIt(String URL) {

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			config.load(fis);
			log.info("Configuration file was loaded successfully!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		String sBrowser = config.getProperty("browser").toLowerCase();
		log.info("Launching: " + sBrowser);
		switch (sBrowser) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		default: {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		}
		
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();

		log.info(caps.getBrowserName() + " was initiated successfully!");

		setDriver(driver);

		SeleniumMethods.maximize(driver);

		if (URL.equals("dpUrl")) {
			driver.get(config.getProperty("dpUrl"));
			log.info("Successfully navigated to New Dealer Portal!");
		} else if (URL.equals("raUrl")) {
			driver.get(config.getProperty("raUrl"));
			log.info("Successfully navigated to Rent Advance Portal!");
		}

		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
				TimeUnit.SECONDS);
	}

}
