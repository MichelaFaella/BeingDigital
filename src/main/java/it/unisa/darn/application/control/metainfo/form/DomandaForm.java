package it.unisa.darn.application.control.metainfo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DomandaForm {

  @NotBlank
  @Size(max = 255)
  private String testo;

  @NotBlank
  @Size(max = 255)
  private String corretta;

  @NotBlank
  @Size(max = 255)
  private String sbagliata1;

  @NotBlank
  @Size(max = 255)
  private String sbagliata2;

  @NotBlank
  @Size(max = 255)
  private String sbagliata3;

  @NotNull
  private Long metaInfoId;
}
