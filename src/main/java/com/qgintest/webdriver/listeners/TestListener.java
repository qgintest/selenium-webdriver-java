package com.qgintest.webdriver.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println("on test method " +  getTestMethodName(result) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println("on test method " + getTestMethodName(result) + " success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println("on test method " + getTestMethodName(result) + " failure");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println("test method " + getTestMethodName(result) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//System.out.println("test failed but within success % " + getTestMethodName(result));
	}
	
//	private static String getTestMethodName(ITestResult result) {
//		return result.getMethod().getConstructorOrMethod().getName();
//	}

	@Override
	public void onStart(ITestContext context) {
		//System.out.println("On start");
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("On finish test");
	}
}