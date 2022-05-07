package com.chocoapp.chocoappapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chocoapp.chocoappapi.converter.UserConverter;
import com.chocoapp.chocoappapi.dto.UserDTO;
import com.chocoapp.chocoappapi.exception.ServiceException;
import com.chocoapp.chocoappapi.exception.ValidationException;
import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.repository.UserRepository;
import com.chocoapp.chocoappapi.util.StringUtil;
import com.chocoapp.chocoappapi.validation.UserValidator;

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