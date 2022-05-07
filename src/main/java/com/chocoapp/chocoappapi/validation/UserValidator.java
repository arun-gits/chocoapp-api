package com.chocoapp.chocoappapi.validation;

import org.springframework.stereotype.Component;

import com.chocoapp.chocoappapi.exception.ValidationException;
import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.util.StringUtil;

@Component
public class UserValidator {

	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */

	public void validateRegisterUserDetails(User user) throws ValidationException {
		StringUtil.isValidString(user.getName(), "Invalid name");
		StringUtil.isValidString(user.getAddress(), "Invalid address");
		StringUtil.isValidMail(user.getMail());
		StringUtil.isValidPassword(user.getPassword());
		StringUtil.isValidMobile(user.getMobile());
	}

	public void validateLoginUserMail(User user) throws ValidationException {

		StringUtil.isValidMail(user.getData());

		StringUtil.isValidPassword(user.getPassword());
	}

	public void validateLoginUserMobile(User user) throws ValidationException {

		StringUtil.isValidMobile(user.getData());

		StringUtil.isValidPassword(user.getPassword());
	}

}
