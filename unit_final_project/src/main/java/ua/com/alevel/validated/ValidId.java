package ua.com.alevel.validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {
        ValidIdConstraintValidator.class
})
@Documented
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidId {

    String message() default "must be number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
