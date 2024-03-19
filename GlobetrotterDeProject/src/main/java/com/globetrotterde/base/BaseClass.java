package com.globetrotterde.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.globetrotterde.utility.ExtentManager;

//import com.globetrotterde.actiondriver.Action;
import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * @Author Sayrina Lopes BaseClass is used to load the config file and Initialize 
 * WebDriver
 */


public class BaseClass {

	public static Properties prop;
	//public static WebDriver driver;
	
	// Declare ThreadLocal Driver - Parallel Execution
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	//loadConfig method is to load the configuration
	@BeforeSuite(groups = { "Smoke","Sanity", "Regression" })
	
	public void loadConfig() throws IOException {
		ExtentManager.setExtent();
		
		//Not Required for log4j2.xml configuration auto-initializes from src/main/resources
		//DOMConfigurator.configure("log4j.xml");
		
		try 
		{
			prop=new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);

		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static WebDriver getDriver() 
	{
		// Get Driver from threadLocalmap
		return driver.get();
	}
	

	public void launchApp(String browserName)
	{
		// String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}
		//Maximize the screen
		getDriver().manage().window().maximize();
		//Delete all the cookies
		getDriver().manage().deleteAllCookies();
		//Implicit TimeOuts
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		//PageLoad TimeOuts
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTimeOut"))));
		//Launching the URL
		getDriver().get(prop.getProperty("url"));
	}
	
	@AfterSuite(groups = { "Smoke","Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
}
