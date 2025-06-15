package com.automation.api.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileReader {

	Properties properties=new Properties();
	
	public Properties getProperty() {
		FileInputStream inStream = null;
		Properties properties=new Properties();
		try {
			inStream=new FileInputStream("resources/config.properties");
			properties.load(inStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
}
