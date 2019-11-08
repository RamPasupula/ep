package com.uob.edag.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Util {
	public static final String getProperties(String propertyName) {
		try {
			ResourceBundle bundle = PropertyResourceBundle.getBundle("portal");
			return bundle.getString(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
