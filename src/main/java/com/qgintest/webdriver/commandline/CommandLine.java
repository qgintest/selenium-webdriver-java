package com.qgintest.webdriver.commandline;

import com.beust.jcommander.Parameter;

public class CommandLine {
	
	@Parameter(names = {"-h", "--help"}, help = true, description = "Displays help Information")
	public boolean help;
	
	@Parameter(names = {"-b", "--browser"}, 
			//validateWith = ParameterValidation.class, 
			description = "Select browser you want to run test in. Valid types are \n\tchrome \tfirefox \texplorer")
	public String browserType = "chrome";
}
