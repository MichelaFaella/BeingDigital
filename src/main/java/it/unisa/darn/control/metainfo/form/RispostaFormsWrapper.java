package it.unisa.darn.control.metainfo.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
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
public class RispostaFormsWrapper {

  @NotEmpty
  private List<@Valid RispostaForm> rispostaFormList;
}
