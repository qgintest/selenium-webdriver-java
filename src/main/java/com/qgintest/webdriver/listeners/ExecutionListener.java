package com.qgintest.webdriver.listeners;

import org.testng.IExecutionListener;

public class ExecutionListener implements IExecutionListener {
	private long startTime;

	@Override
	public void onExecutionStart() {
		startTime = System.currentTimeMillis();
		System.out.println("msg from listener******** TestNG is going to start***********");		
	}

	@Override
	public void onExecutionFinish() {
		System.out.println("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
	}
}
