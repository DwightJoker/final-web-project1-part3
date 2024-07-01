package edu.training.web.service;

import edu.training.web.bean.UserRegInfo;

public interface UserValidatorService {

	boolean userExist(UserRegInfo userRegInfo) throws ServiceException;

}
