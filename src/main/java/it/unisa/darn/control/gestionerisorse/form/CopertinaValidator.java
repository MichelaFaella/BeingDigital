package it.unisa.darn.control.gestionerisorse.form;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class CopertinaValidator implements ConstraintValidator<CopertinaConstraint, MultipartFile> {

  @Override
  public void initialize(CopertinaConstraint constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(MultipartFile multipartFile,
                         ConstraintValidatorContext constraintValidatorContext) {
    if (multipartFile == null || multipartFile.isEmpty()) {
      return true;
    }

    if (multipartFile.getSize() > 2097152 || !"image/jpeg".equals(multipartFile.getContentType())) {
      return false;
    }

    return true;
  }
}
