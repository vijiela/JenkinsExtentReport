package com.training.Demo_ExtendReports;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.X509ExtendedTrustManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestReports {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//date format on report
		String dateFormat =new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		ExtentReports extent = new ExtentReports();
		String filepath = System.getProperty("user.dir") + "//Reports//"+dateFormat+"_FirstReport.html";
		ExtentHtmlReporter report = new ExtentHtmlReporter(filepath);
		extent.attachReporter(report);

		ExtentTest test = extent.createTest("Login_TC01");
		test.info("Entered Username");
		test.info("Entered Password");
		test.log(Status.PASS, "Login_TC01 Passed");

		ExtentTest test2 = extent.createTest("Login_TC02");
		test2.info("Entered Username");
		test.log(Status.INFO, "Entered Username");
		test2.info("Entered Password");
		test2.log(Status.FAIL, "Login_TC02 Failed");
		extent.flush();

	}

}
