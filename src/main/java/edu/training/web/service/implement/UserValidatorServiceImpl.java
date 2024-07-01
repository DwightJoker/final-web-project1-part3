package edu.training.web.service.implement;

import edu.training.web.bean.UserRegInfo;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.dao.UserDao;
import edu.training.web.service.ServiceException;
import edu.training.web.service.UserValidatorService;

public class UserValidatorServiceImpl implements UserValidatorService {

	private final UserDao userDao = DaoProvider.getInstance().getUserDao();

	private static final UserValidatorService instance = new UserValidatorServiceImpl();

	public static UserValidatorService getInstance() {
		return instance;
	}

	@Override
	public boolean userExist(UserRegInfo userRegInfo) throws ServiceException {
		try {
			return userDao.userExist(userRegInfo);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}