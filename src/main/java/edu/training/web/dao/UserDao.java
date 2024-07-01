package edu.training.web.dao;

import java.util.List;

import edu.training.web.bean.AuthInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegInfo;

public interface UserDao {

	User logIn(AuthInfo info) throws DaoException;

	User checkToken(String token) throws DaoException;

	boolean userExist(UserRegInfo userRegInfo) throws DaoException;

	boolean registration(UserRegInfo userRegInfo) throws DaoException;

	List<User> getAllUsers() throws DaoException;

	boolean setRole(int userId) throws DaoException;

	boolean changeProfile(UserProfile userProfile, int userId) throws DaoException;

	boolean changeRole(String login, String newRole) throws DaoException;

	boolean userDelete(User user) throws DaoException;

	int getRoleId(String title) throws DaoException;

	int getUserIdByLogin(String login) throws DaoException;

	public boolean userDeleteById(int userId) throws DaoException;

}
