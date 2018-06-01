package com.zent.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zent.dto.UserDTO;

@Component("userDTOValidator")
public class UserDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO userDTO = (UserDTO) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "gender", "user.gender.empty");
		ValidationUtils.rejectIfEmpty(errors, "address", "user.address.empty");
		if (userDTO.getId() == null) {
			if (userDTO.getImage().getSize() <= 0) {
				errors.rejectValue("avata", "user.avata.empty");
			}
		}
	}

}
