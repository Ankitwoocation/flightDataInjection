/**
 * 
 */
package com.woocation.elastic.builder;

import java.util.Collection;
import java.util.Iterator;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.google.common.collect.Lists;
import com.woocation.elastic.enums.EsModeQuery;

/**
 * The Class EsQuery.
 *
 * @author ankit.gupta4
 */
public final class EsQuery {

	/** The field name. */
	private String fieldName;

	/** The value. */
	private Object value;

	/**
	 * Represent the query mode.
	 */
	private EsModeQuery mode;

	/** The query int mode. */
	private EsQueryIntMode queryIntMode;

	/**
	 * The Enum EsQueryIntMode.
	 */
	public enum EsQueryIntMode {

		/** The must. */
		MUST,

		/** The should. */
		SHOULD;
	}

	/**
	 * Instantiates a new es query.
	 *
	 * @param fieldName the field name
	 * @param value the value
	 * @param mode the mode
	 */
	public EsQuery(String fieldName, Object value, EsModeQuery mode) {
		this.fieldName = fieldName;
		this.value = value;
		this.mode = mode;
	}

	/**
	 * Instantiates a new es query.
	 *
	 * @param fieldName the field name
	 * @param value the value
	 * @param mode the mode
	 * @param queryIntMode the query int mode
	 */
	public EsQuery(String fieldName, Object value, EsModeQuery mode, EsQueryIntMode queryIntMode) {
		this(fieldName, value, mode);
		this.queryIntMode = queryIntMode;
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public EsModeQuery getMode() {
		return mode;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * To query.
	 *
	 * @return the query builder
	 */
	public QueryBuilder toQuery() {
		QueryBuilder query = null;
		Object refinedValue = getEscapedValue(value);
		switch (this.getMode()) {
		case TERM:
			if (isCollectionValue(refinedValue)) {
				Collection<?> matchCollect = (Collection<?>) refinedValue;
				query = QueryBuilders.termsQuery(fieldName, matchCollect);
			} else {
				query = QueryBuilders.termsQuery(fieldName, refinedValue);
			}
			break;
		case RANGE:
			Iterator<?> itr = ((Collection<?>) refinedValue).iterator();
			String from = itr.next().toString();
			String to = itr.next().toString();
			query = QueryBuilders.rangeQuery(fieldName).from(from).to(to);
			break;
		default:
			break;
		}
		return query;
	}

	/**
	 * Gets the escaped value.
	 *
	 * @param value            the value
	 * @return the escaped value
	 */
	private Object getEscapedValue(Object value) {
		Object escapedValue = value;
		if (Collection.class.isAssignableFrom(value.getClass())) {
			Collection<String> escapedList = Lists.newArrayList();
			for (Object item : ((Collection<?>) value)) {
				escapedList.add(item.toString());
			}
			if (!escapedList.isEmpty()) {
				escapedValue = escapedList;
			}
		} else if (String.class.isAssignableFrom(value.getClass())) {
			escapedValue = value.toString();
		}
		return escapedValue;
	}

	/**
	 * Convert the value for elasticsearch.
	 * 
	 * @param value
	 *            the value to convert.
	 * @return the value converted.
	 */
	private static boolean isCollectionValue(Object value) {
		return Collection.class.isAssignableFrom(value.getClass());
	}

	/**
	 * Gets the query int mode.
	 *
	 * @return the queryIntMode
	 */
	public EsQueryIntMode getQueryIntMode() {
		return queryIntMode;
	}

}
