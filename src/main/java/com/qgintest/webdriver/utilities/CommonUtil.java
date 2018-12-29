package com.qgintest.webdriver.utilities;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.qgintest.testutilities.file.FileUtil;
import com.qgintest.webdriver.app.TestRunner;
import com.qgintest.testutilities.date.DateUtil;

public class CommonUtil {

	FileUtil fileUtil = new FileUtil();

	public File setDriver(String filepath, String filename, String outputDir) {

		File file = new File(fileUtil.getWorkingDir() + File.separator + outputDir + File.separator + filename + "_"
				+ DateUtil.returnTimestamp("yyyyMMdd.HHmmss"));

		try {
			try (InputStream in = TestRunner.class.getResourceAsStream("/" + filepath + filename)) {
				FileUtils.copyInputStreamToFile(in, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

}
