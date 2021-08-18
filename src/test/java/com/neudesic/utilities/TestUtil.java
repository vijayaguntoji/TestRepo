package com.neudesic.utilities;

import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.neudesic.main.*;

public class TestUtil  extends TestBase{
	
	public static String screenshotName;
	
	/******************************
	 * Generates a randon number based on the given length
	 * @param length
	 * @return String A random number
	 */
	public static String randomNumber(int length)
	{
		String chars = "0123456789";
		char[] stringChars = new char[length];
		Random random = new Random();

		for (int i = 0; i < stringChars.length; i++)
		{
			stringChars[i] = chars.charAt(random.nextInt(chars.length()));
		}

		return new String(stringChars);
	}
	
	/******************************
	 * Takes screenshot of the browser's current page
	 * @throws IOException
	 */
	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		screenshotName = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\screenshots\\" + screenshotName));

	}

}
