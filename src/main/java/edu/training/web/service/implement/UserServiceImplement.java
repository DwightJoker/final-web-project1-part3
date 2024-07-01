package edu.training.web.service.implement;

import java.util.List;

import edu.training.web.bean.AuthInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegInfo;
import edu.training.web.dao.UserDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.service.ServiceException;
import edu.training.web.service.UserService;
import edu.training.web.service.UserValidatorService;

public class UserServiceImplement implements UserService {
	private final UserDao userDao = DaoProvider.getInstance().getUserDao();
	private final UserValidatorService validator = UserValidatorServiceImpl.getInstance();

	@Override
	public User logIn(AuthInfo info) throws ServiceException {
		try {
			return userDao.logIn(info);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(UserRegInfo userRegInfo) throws ServiceException {
		try {
			if (validator.userExist(userRegInfo)) {
				throw new ServiceException("User already exists");
			}

			if (userDao.userExist(userRegInfo)) {
				throw new ServiceException("Mail is already taken");
			} else {
				return userDao.registration(userRegInfo);
			}
		} catch (DaoException e) {
			throw new ServiceException("Server error. Try again later", e);
		}
	}

	@Override
	public User rememberMe(String token) throws ServiceException {
		try {
			User user = userDao.checkToken(token);
			return user;
		} catch (DaoException e) {
			throw new ServiceException("Failed to remember user", e);
		}
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			return userDao.getAllUsers();
		} catch (DaoException e) {
			throw new ServiceException("Failed to retrieve users", e);
		}
	}

	@Override
	public boolean changeRole(String login, String newRole) throws ServiceException {
		try {
			return userDao.changeRole(login, newRole);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changeProfile(UserProfile userProfile, int userId) throws ServiceException {
		try {
			return userDao.changeProfile(userProfile, userId);
		} catch (DaoException e) {
			throw new ServiceException("Error updating profile", e);
		}
	}

	@Override
	public boolean userDelete(User user) throws ServiceException {
		try {
			return userDao.userDelete(user);
		} catch (DaoException e) {
			throw new ServiceException("Error deleting user", e);
		}
	}

	@Override
	public boolean userDeleteById(int userId) throws ServiceException {
		try {
			return userDao.userDeleteById(userId);
		} catch (DaoException e) {
			throw new ServiceException("Error deleting user", e);
		}
	}
}
