package com.qgintest.webdriver.app;

public class TestRunner {
	
	static TestDeveloper td = new TestDeveloper();

	public static void main(String[] args) {
		td.generateSuitesAndRun(args);
	}

}
