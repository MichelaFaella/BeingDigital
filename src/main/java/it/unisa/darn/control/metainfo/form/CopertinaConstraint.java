package it.unisa.darn.control.metainfo.form;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CopertinaValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CopertinaConstraint {

  String message() default "Copertina troppo grande o del formato errato";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
