package com.woocation.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectCloner {
	
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(ObjectCloner.class);
	
	/** The object cloner. */
	private static ObjectCloner objectCloner;

	/**
	 * Instantiates a new object cloner.
	 */
	private ObjectCloner() {

	}

	/**
	 * Gets the single instance of ObjectCloner.
	 *
	 * @return single instance of ObjectCloner
	 */
	public static ObjectCloner getInstance() {
		if (objectCloner == null) {
			objectCloner = new ObjectCloner();
		}
		return objectCloner;
	}

	/**
	 * Deep copy.
	 *
	 * @param oldObj
	 *            the old obj
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	public Object deepCopy(Object oldObj) throws Exception {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(oldObj);
			ByteArrayInputStream bin = new ByteArrayInputStream(
					bos.toByteArray());
			ois = new ObjectInputStream(bin);
			return ois.readObject();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw (e);
		} finally {
			oos.flush();
			oos.close();
			ois.close();
		}
	}
}