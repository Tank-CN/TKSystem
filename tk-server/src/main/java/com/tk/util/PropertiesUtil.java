package com.tk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties properties = new Properties();

	private static Properties getProperties() {
		if (properties.size() == 0) {
			synchronized (properties) {
				properties = new Properties();
				try {
					String filename = "src/main/resources/conf/tk.properties";
					properties.load(new FileInputStream(new File(filename)));
				} catch (IOException e) {
					System.out.println("加载tk.properties出错！");
				}
			}
		}
		return properties;
	}

	public static String getKey(String key) {
		return getProperties().getProperty(key);
	}
}
