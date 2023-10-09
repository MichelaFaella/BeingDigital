package it.unisa.beingdigital.control.gestionerisorse.form;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MultipartFileValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartFileConstraint {

  String message() default "File troppo grande o del formato errato";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  long maxSize();

  String mimeType();
}
