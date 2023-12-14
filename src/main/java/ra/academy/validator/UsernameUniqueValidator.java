package ra.academy.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
