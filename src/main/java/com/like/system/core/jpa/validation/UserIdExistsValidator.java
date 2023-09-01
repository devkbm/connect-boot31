package com.like.system.core.jpa.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.like.system.user.application.port.out.SystemUserSelectPort;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Component
public class UserIdExistsValidator implements ConstraintValidator<UserIdExists, SystemUserId> {
	
	private SystemUserSelectPort userService;
	
	public UserIdExistsValidator(SystemUserSelectPort userService) {
		this.userService = userService;
	}
	
	@Override
	public void initialize(UserIdExists constraintAnnotation) {	
	}
	
	@Override
	public boolean isValid(SystemUserId value, ConstraintValidatorContext context) {

		SystemUser user = this.userService.select(value.getOrganizationCode(), value.getUserId());
		
		if (user == null) {
			context.buildConstraintViolationWithTemplate("test message")
				   .addConstraintViolation();
			return false;			
		}
		
		return true;
	}	
}
