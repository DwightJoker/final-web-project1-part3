package edu.training.web.dao;

import edu.training.web.dao.implement.NewsDaoImplements;
import edu.training.web.dao.implement.UserDaoImplements;

public class DaoProvider {

	private static final DaoProvider instance = new DaoProvider();

	private UserDao userDao = new UserDaoImplements();
	private NewsDao newsDao = new NewsDaoImplements();

	private DaoProvider() {
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public static DaoProvider getInstance() {
		return instance;
	}

}
