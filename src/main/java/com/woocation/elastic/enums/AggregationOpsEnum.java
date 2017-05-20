/**
 * 
 */
package com.woocation.elastic.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum AggregationOpsEnum.
 *
 * @author ankit.gupta4
 */
public enum AggregationOpsEnum {

	/** The filter. */
	FILTER("filter"),

	/** The terms. */
	TERMS("terms"),

	/** The sum. */
	SUM("sum"),

	/** The datehistogram. */
	DATE_HISTOGRAM("dateHistogram"),
	
	/** The avg. */
	AVG("avg"),
	
	TOPHITS("top_hits");

	/** The operation. */
	private String operation;

	/**
	 * Instantiates a new aggregation operator enum.
	 *
	 * @param operation            the operation
	 */
	private AggregationOpsEnum(String operation) {
		this.operation = operation;
	}

	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

}
