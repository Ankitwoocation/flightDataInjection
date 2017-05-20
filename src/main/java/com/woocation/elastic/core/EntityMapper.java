/**
 * 
 */
package com.woocation.elastic.core;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woocation.exception.ElasticException;
import com.woocation.model.EsInner;

/**
 * @author ankit.gupta4
 *
 */
public class EntityMapper {

	/**
	 * The logger.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * Get the unique instnce of entity builder.
	 */
	private static EntityMapper instance;

	/**
	 * Json mapper.
	 */
	private ObjectMapper mapper;

	/**
	 * Default constructor.
	 */
	private EntityMapper() {
		mapper = new ObjectMapper();
	}

	/**
	 * Gets the entity builder.
	 * 
	 * @return the entity builder.
	 */
	public static EntityMapper getInstance() {
		if (instance == null) {
			instance = new EntityMapper();
		}
		return instance;
	}

	/**
	 * Return an object from json value.
	 * 
	 * @param <T>
	 *            generic entity.
	 * @param json
	 *            the json value
	 * @param aClass
	 *            type of object.
	 * @return an object.
	 * @throws ElasticException
	 *             if an error occurs.
	 */
	public <T> T getObject(String json, Class<T> aClass) throws ElasticException {
		try {
			T obj = mapper.readValue(json, aClass);
			return obj;
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ElasticException(e);
		}
	}

	/**
	 * Serialize the EsBean.
	 * 
	 * @param bean
	 *            the bean
	 * @param <T>
	 *            the generic object.
	 * @return the json value.
	 * @throws JsonProcessingException
	 *             if an error occurs.
	 */
	public <T extends EsInner> String getSerialize(T bean) throws JsonProcessingException {
		return mapper.writeValueAsString(bean);
	}

	/**
	 * Get Id of Object
	 * 
	 * @param entity
	 *            Object
	 * @param <T>
	 *            generic bean.
	 * @return Id
	 * @throws Exception
	 *             if error occurs
	 */
	public static <T extends EsInner> String getId(T entity) throws Exception {
		Class<? extends EsInner> aClass = entity.getClass();
		// Field idField = aClass.getField("id");
		// Object idValue = idField.get(entity);
		return entity.getId();
	}

	/**
	 * Gets the fields.
	 *
	 * @param <T>
	 *            the generic type
	 * @param beanClass
	 *            the class name
	 * @return the fields
	 */
	public static <T extends EsInner> List<String> getFields(Class<T> beanClass) {
		List<String> privateFields = new ArrayList<>();
		Field[] allFields = beanClass.getDeclaredFields();
		for (Field field : allFields) {
			if (Modifier.isPrivate(field.getModifiers()) && field.getType().getSimpleName().equals(String.class.getSimpleName())) {
				if (field.getName().contains("date") || field.getName().contains("Date")){
					continue;
				}
				privateFields.add(field.getName());
			}
		}
		return privateFields;
	}

}
