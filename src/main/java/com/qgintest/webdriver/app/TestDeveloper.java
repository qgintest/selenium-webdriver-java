package com.qgintest.webdriver.app;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.qgintest.webdriver.commandline.CommandLine;
import com.qgintest.webdriver.listeners.ExecutionListener;
import com.qgintest.webdriver.listeners.TestAnnotationTransformerListener;
import com.qgintest.webdriver.listeners.TestListener;

public class TestDeveloper {
	
	final CommandLine mainArgs = new CommandLine();
	
	void generateSuitesAndRun(String[] args){
		
		handleInputArgs(args);
		
		XmlSuite suite = new XmlSuite();
		suite.setName("Mercury Tours Website Test Suite");
		 
		XmlTest test = new XmlTest(suite);
		test.setName("Regression Tests");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.qgintest.webdriver.tests.MercuryToursTests"));
		test.setXmlClasses(classes) ;
		test.addParameter("Browser-Type", mainArgs.browserType);
		test.addParameter("UsePreviousSession", "false");
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.setAnnotationTransformer(new TestAnnotationTransformerListener());
		tng.addExecutionListener(new ExecutionListener());
		tng.addListener(new TestListener());

		tng.run();
	}
	
	void handleInputArgs(String args[]){
		JCommander jCommander = new JCommander(mainArgs);
		jCommander.setProgramName("execute");
		
		try{
			jCommander.parse(args);
		}catch(ParameterException exception){
			System.out.println(exception.getMessage());
			showUsage(jCommander);
		}
		
		if(mainArgs.help){
			showUsage(jCommander);
		}
	}
	
	void showUsage(JCommander jCommander){
		jCommander.usage();
		System.exit(0);
	}
	
}
