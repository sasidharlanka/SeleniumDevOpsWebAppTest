package com.test;

import java.util.*;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SeleniumGoogleSearchTest {

	public static RemoteWebDriver driver;
	public static String appURL = "http://localhost:8080/DevOpsWebApp-1.0.0-SNAPSHOT/index.jsp";
	
	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) throws MalformedURLException {
		System.out.println("*******************");
		driver = Browser.getDriver(browser);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testTitle() throws Exception{
		
		String expectedTtile = "DevOpsWebApp";

		System.out.println("Opening DevOps website..");
		driver.navigate().to(appURL);
		System.out.println("Actual Title of the home page now is: " + driver.getTitle());
		System.out.println("Expected title of the home page: " + expectedTtile);
		String homePageTitle = driver.getTitle();
		this.takeSnapShot(driver, "WebSiteHomePage.png");
		Assert.assertTrue(homePageTitle.equalsIgnoreCase(expectedTtile), "DevOps website HomePage title doesn't match");
	}
	
	//https://www.testingexcellence.com/click-link-href-value-webdriver/
	@Test
	public void testClick() throws Exception{

		System.out.println("Opening DevOps website..");
		driver.navigate().to(appURL);
		driver.findElement(By.linkText("Login")).click();
		this.takeSnapShot(driver, "Login.png");
		String loginPageTitle = driver.getTitle();
		System.out.println("Actual Title of the login page now is: " + loginPageTitle);
		
		driver.findElement(By.id("username")).sendKeys("devops2018");

		driver.findElement(By.id("userpwd")).sendKeys("devops@2018");

		driver.findElement(By.id("btnLogin")).click();
		
		this.takeSnapShot(driver, "AfterLogin.png");
	}
	
	//https://www.testingexcellence.com/click-link-href-value-webdriver/
	@Test
	public void testAllLinksClick() throws Exception{
		
		System.out.println("Opening DevOps website..");
		driver.navigate().to(appURL);
		driver.findElement(By.linkText("Login")).click();
		this.takeSnapShot(driver, "Login.png");
		
		driver.navigate().to(appURL);
		driver.findElement(By.linkText("here")).click();
		this.takeSnapShot(driver, "SampleHtml.png");
		
		driver.navigate().to(appURL);
		driver.findElement(By.linkText("Go To Training Page")).click();
		this.takeSnapShot(driver, "TrainingHtml.png");
	}

	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
