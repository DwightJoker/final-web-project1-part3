package edu.training.web.service;

import java.util.List;

import edu.training.web.bean.AuthInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegInfo;

public interface UserService {

	User logIn(AuthInfo info) throws ServiceException;

	boolean registration(UserRegInfo userRegInfo) throws ServiceException;

	User rememberMe(String token) throws ServiceException;

	List<User> getAllUsers() throws ServiceException;

	boolean changeRole(String login, String newRole) throws ServiceException;

	boolean changeProfile(UserProfile userProfile, int userId) throws ServiceException;

	boolean userDelete(User user) throws ServiceException;

	public boolean userDeleteById(int userId) throws ServiceException;

}
