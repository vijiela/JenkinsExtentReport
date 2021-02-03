package com.training.Demo_ExtendReports;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Salesforce_Login {
	
	public static WebDriver driver;
	static ExtentReports extent;
	static ExtentHtmlReporter report;
	String dateformat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	ExtentTest test;
	
	public String takescreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		String dstpath = System.getProperty("user.dir")+"//Reports//Screenshots//"+dateformat+"_SFDC.PNG";
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		File dstfile = new File(dstpath);
		FileUtils.copyFile(srcfile, dstfile);	
		test.info("Screenshot captured");
		return dstpath;
	}
	
	
	
	@BeforeClass
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		extent = new ExtentReports();
		report = new ExtentHtmlReporter(System.getProperty("user.dir")+"//Reports//"+dateformat+"_SFDCreport.html");
		extent.attachReporter(report);
	}
	
	@AfterClass
	public void closereport() {
		extent.flush();
		driver.close();
	}
	
	@Test
	public void Login_01(Method name) throws InterruptedException, IOException {
		test = extent.createTest(name.getName());
		driver.get("https://login.salesforce.com");
		test.log(Status.INFO, "Login page launched");
		driver.findElement(By.id("username")).sendKeys("vijiela@abc.com");
		test.info("Entered username");
		driver.findElement(By.id("password")).sendKeys("tekarch@123");
		test.info("Entered password");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.id("error")).getText().equals("Please check your username and password. If you still can't log in, contact your Salesforce administrator.")) {
			test.log(Status.PASS, "Login_TC01 Passed");
			Assert.assertEquals(driver.findElement(By.id("error")).getText(), "Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
		}
		else {
			test.addScreenCaptureFromPath(takescreenshot());
			test.log(Status.FAIL, "Login_TC01 FAILED");
			Assert.fail("Login_TC01 FAILED");
		}
		
	}
	
	@Test
	public void Login_02(Method name) throws InterruptedException, IOException {
		test = extent.createTest(name.getName());
		driver.get("https://login.salesforce.com");
		test.log(Status.INFO, "Login page launched");
		driver.findElement(By.id("username")).sendKeys("vijiela@abc.com");
		test.info("Entered username");
		driver.findElement(By.id("password")).sendKeys("tekarch@1235");
		test.info("Entered password");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.id("error")).getText().equals("Password enterd is incorrect")) {
			test.log(Status.PASS, "Login_TC01 Passed");
			Assert.assertEquals(driver.findElement(By.id("error")).getText(), "Password enterd is incorrect");
		}
		else {
			test.addScreenCaptureFromPath(takescreenshot());
			test.log(Status.FAIL, "Login_TC02 FAILED");
			Assert.fail("Login_TC02 FAILED");
		}
		
	}

}