package com.woocation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class Helper.
 */
public class ObjectDeserializer {

	/**
	 * Java object to json.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return the string
	 */
	public static <T> String javaObjectToJson(T object) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonInString;
	}
}