package br.com.zupacademy.rafael.proposta.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = Base64Validator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidadorBase64 {
    String message() default "não está no formato base64";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
