package com.beecow.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfReader {

	public String getPropValues(String varName) {

		Properties prop = new Properties();
		InputStream input = null;
		String result = "";

		try {
			String filename = "config.properties";
			prop.load(getClass().getClassLoader().getResourceAsStream(filename));

			// get the property value and pass to result
			result = prop.getProperty(varName);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
