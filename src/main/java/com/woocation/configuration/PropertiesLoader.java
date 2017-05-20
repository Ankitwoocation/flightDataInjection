/**
 * 
 */
package com.woocation.configuration;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author ankit
 *
 */
public class PropertiesLoader {

	/** The read properties. */
	private static PropertiesLoader loader = null;

	/** The prop. */
	private static Properties prop = new Properties();

	private PropertiesLoader() {
		String filePath = System.getProperty("user.dir") + "/app.properties";
		
		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			prop.load(inputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets the property.
	 *
	 * @param key
	 *            the key
	 * @return the property
	 */
	public static String getProperty(String key) {
		if (loader == null) {
			loader = new PropertiesLoader();
		}
		return prop.getProperty(key);
	}

}
