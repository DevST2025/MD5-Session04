package ra.academy.validator;

import jakarta.validation.Constraint;

@Constraint(validatedBy = UsernameUniqueValidator.class)

public @interface UsernameUnique {
}
