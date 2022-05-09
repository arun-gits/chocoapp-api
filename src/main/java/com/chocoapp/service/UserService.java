package com.chocoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chocoapp.converter.UserConverter;
import com.chocoapp.dto.UserDTO;
import com.chocoapp.exception.ServiceException;
import com.chocoapp.exception.ValidationException;
import com.chocoapp.model.User;
import com.chocoapp.repository.UserRepository;
import com.chocoapp.util.StringUtil;
import com.chocoapp.validation.UserValidator;

@Repository
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserValidator userValidator;

	// registration
	public void userRegistration(UserDTO userDto) throws ServiceException, ValidationException {
		try {
			User user = UserConverter.toModel(userDto);
			userValidator.validateRegisterUserDetails(user);

			User mailCheck = userRepository.findByMail(user.getMail());
			User mobileCheck = userRepository.findByMobile(user.getMobile());

			if (mailCheck != null || mobileCheck != null) {
				throw new ValidationException("Already an existing user");
			}

			userRepository.save(user);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());

		}
	}

	public void userLogin(UserDTO userDto) throws ValidationException, ServiceException {
		try {
			User user = UserConverter.toModel(userDto);
			boolean isMail = StringUtil.isMail(user.getData());
			if(isMail) {
				userValidator.validateLoginUserMail(user);
			}
			else {
				userValidator.validateLoginUserMobile(user);
			}

			User logUser = userRepository.findByMailOrMobile(user.getData()).orElseThrow(()->new ValidationException ("Invalid Credentials"));
			if(!logUser.getPassword().equals(user.getPassword())) {
				throw new ValidationException("Invalid Credentials");
			}
			
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}