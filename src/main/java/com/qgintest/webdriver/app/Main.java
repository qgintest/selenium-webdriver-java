package com.qgintest.webdriver.app;

import com.qgintest.webdriver.testmanager.TestDeveloper;

public class Main {
	
	public static void main(String[] args) {
		TestDeveloper td = new TestDeveloper();
		td.generateSuitesAndRun(args);
	}

}
