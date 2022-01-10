package ua.com.alevel.validated;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidIdConstraintValidator implements ConstraintValidator<ValidId, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("ValidIdConstraintValidator.isValid");
        try {
            long id = Long.parseLong(String.valueOf(o));
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
