package com.chocoapp.chocoappapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chocoapp.chocoappapi.logic.UserValidator;

@Configuration
public class Config {

	@Bean
	public UserValidator userValidator() {
		return new UserValidator();
	}
}
