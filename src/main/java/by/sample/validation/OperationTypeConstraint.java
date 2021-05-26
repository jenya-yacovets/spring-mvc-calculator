package by.sample.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OperationTypeValidation.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationTypeConstraint {
    String message() default "Invalid operation type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
