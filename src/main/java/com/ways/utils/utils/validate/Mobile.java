package com.ways.utils.utils.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * User: leochen
 * Date: 11-12-8
 * Time: 下午11:31
 */

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
@Documented
public @interface Mobile {
    String message() default "手机号码不合法";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}