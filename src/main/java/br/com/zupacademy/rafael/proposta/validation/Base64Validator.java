package br.com.zupacademy.rafael.proposta.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class Base64Validator implements ConstraintValidator<ValidadorBase64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {
            Base64.getDecoder().decode(value.getBytes());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
