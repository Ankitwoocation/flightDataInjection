package com.woocation;

import com.woocation.configuration.ElasticConfiguration;

/**
 * The Class FlightInjectionApplication.
 */
public class FlightInjectionApplication{

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			ElasticConfiguration.entityManager();
		} catch (Exception e) {
			System.out.println("Couldn't initialize the application");
		}
	}
}
