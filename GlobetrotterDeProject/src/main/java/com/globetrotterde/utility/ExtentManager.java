package com.globetrotterde.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManager {
	public static ExtentSparkReporter htmlReporter;
	//public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static void setExtent() throws IOException {
		
		try {
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+BaseClass.getCurrentTime()+".html");
		htmlReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport.html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		//htmlReporter.config().setDocumentTitle("Automation Test Report");
		//htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
		//htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", "SaLo");
		extent.setSystemInfo("ProjectName", "GlobetrotterDeProject");
		extent.setSystemInfo("Tester", "Sayrina");
		extent.setSystemInfo("OS", "Win11 Enterprise");
		extent.setSystemInfo("Browser", "Chrome");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void endReport() {
		extent.flush();
	}
}
