package com.neudesic.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        
        extent = new ExtentReports();
        KlovReporter klovReporter = new KlovReporter();
        ThreadLocal<KlovReporter> klov = new ThreadLocal<KlovReporter>(); 
        klov.set(klovReporter);
        klovReporter.initMongoDbConnection("localhost", 27017);

        klovReporter.setProjectName("Dealer Portal");

        klovReporter.setReportName("Daily Suite");

        klovReporter.setKlovUrl("http://localhost");
        extent.attachReporter(htmlReporter, klovReporter);
        extent.setSystemInfo("Automation Tester", "Vinod Reddy");
        extent.setSystemInfo("Organization", "American First Finance");
        extent.setSystemInfo("Environment", "SV7");
        
        
        return extent;
    }
}
