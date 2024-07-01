package edu.training.web.dao.connectionpool;

import java.util.ResourceBundle;

public class DataBaseResourceManager {

	private final static DataBaseResourceManager instance = new DataBaseResourceManager();

	ResourceBundle jdbcProperties = ResourceBundle.getBundle("db");

	public static DataBaseResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return jdbcProperties.getString(key);
	}

}
