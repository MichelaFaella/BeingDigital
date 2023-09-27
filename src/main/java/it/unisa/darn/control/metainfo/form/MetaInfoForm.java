package it.unisa.darn.control.metainfo.form;

import it.unisa.darn.storage.entity.util.Livello;
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
public class MetaInfoForm {

  @NotBlank
  @Size(max = 255)
  private String keyword;

  @NotNull
  private Livello livello;
}
