package it.unisa.beingdigital.control.gestionerisorse.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArgomentoForm {

  @NotBlank
  @Size(max = 255)
  private String titolo;

  @NotBlank
  @Size(max = 65535)
  private String corpo;

  @MultipartFileConstraint(maxSize = 2097152, mimeType = "image/jpeg")
  private MultipartFile copertina;

  @NotNull
  private Long metaInfoId;
}
