/**
 * 
 */
package com.somday.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.somday.service.ApplicationContextServe;

/**
 * @Since : Aug 26, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 26, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class PropertyUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);
	public static String getProperty(String propertyName) {
		return getProperty(propertyName, null);
	}

	public static String getProperty(String propertyName, String defaultValue) {
		String value = defaultValue;
		ApplicationContext applicationContext = ApplicationContextServe.getApplicationContext();
		if (applicationContext.getEnvironment().getProperty(propertyName) == null) {
			LOGGER.info(propertyName + " properties was not loaded.");
		} else {
			value = applicationContext.getEnvironment().getProperty(propertyName).toString();
		}
		return value;
	}
}
