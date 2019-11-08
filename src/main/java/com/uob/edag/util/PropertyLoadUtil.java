/**
 * 
 */
package com.uob.edag.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author vencp9
 *
 */
public class PropertyLoadUtil {

	/**
	 * Gets theproperties value from the ./edagportal.properties file of the base
	 * folder
	 *
	 * @return Properties Object
	 * @throws IOException
	 */

	public static Properties getproperties() {

		// to load application's properties
		Properties edagProperties = new Properties();

		FileInputStream file = null;

		// the base folder is ./, the root of the main.properties file

		// local
		// String path = "C:\\prodlib\\EDA\\edf\\config\\edagportal.properties";
		String path = "../../config/edagportal.properties";

		// load the file handle for main.properties
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {

			System.err.println(e.getMessage() + "file not found at :: " + path);
		} catch (Exception e) {

			System.err.println(e.getMessage());
		}

		// load all the properties from this file
		try {
			edagProperties.load(file);
		} catch (Exception e) {
			System.err.println(e.getMessage() + "Not able to load the file :: ");
			// e.printStackTrace();
		}

		// we have loaded the properties, so close the file handle
		try {
			file.close();
		} catch (Exception e) {

		}

		return edagProperties;
	}

	public static Properties getQueryproperties() {
		Properties edagProperties = new Properties();

		FileInputStream file = null;

		String path = "../../config/edagportalqueries.properties";

		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {

			System.err.println(e.getMessage() + "file not found at :: " + path);
		} catch (Exception e) {

			System.err.println(e.getMessage());
		}

		try {
			edagProperties.load(file);
		} catch (Exception e) {
			System.err.println(e.getMessage() + "Not able to load the file :: ");
		}

		try {
			file.close();
		} catch (Exception exception) {
		}

		return edagProperties;
	}

	public static String getProperty(String key) {
		Properties props = getproperties();
		return props.getProperty(key.trim());
	}

	public static String getQueryProperty(String key) {
		Properties props = getQueryproperties();
		return props.getProperty(key.trim());
	}
	
	public static int getLongProperty(String key) {
		return Integer.valueOf(getProperty( key)) ;
	}
	
}
