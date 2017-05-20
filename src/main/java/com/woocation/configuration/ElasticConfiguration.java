/**
 * 
 */
package com.woocation.configuration;

import com.google.common.collect.Lists;
import com.woocation.elastic.core.ElasticEntityManager;
import com.woocation.elastic.core.ElasticServer;

/**
 * The Class ElasticConfiguration.
 *
 * @author ankit.gupta4
 */
public class ElasticConfiguration {

	/**
	 * Get tne entity manager.
	 * 
	 * @return the entity manager.
	 * @throws Exception
	 *             if an error occurs.
	 */
	public static void entityManager() throws Exception {
//		try {
			ElasticEntityManager entityManager = ElasticEntityManager.getInstance();
			ElasticServer elasticServer = elasticServer();
			entityManager.openSessionTransport(elasticServer);
//		} catch (Exception e) {
//			System.out.println("Couldn't initialize elastic search ---> " + e.getMessage());
//		}
	}

	/**
	 * Elastic server.
	 *
	 * @return the elastic server
	 * @throws Exception
	 *             the exception
	 */
	public static ElasticServer elasticServer() throws Exception {
		return new ElasticServer(Lists.newArrayList(PropertiesLoader.getProperty("elasticsearch.host")),
				Integer.parseInt(PropertiesLoader.getProperty("elasticsearch.port")));
	}

}
