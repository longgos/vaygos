package com.ways.utils.utils.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: leochen Date: 11-12-8 Time: 下午11:39
 */
public class MobileValidator implements ConstraintValidator<Mobile, Object> {

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (null == value) {
			return true;
		}
		String mobile = "^1\\d{10}";
		Pattern regex = Pattern.compile(mobile);
		Matcher m = regex.matcher(value.toString());
		return m.matches();
	}

	@Override
	public void initialize(Mobile constraintAnnotation) {

	}
}