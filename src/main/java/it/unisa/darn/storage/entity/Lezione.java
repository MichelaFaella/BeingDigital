package it.unisa.darn.storage.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Lezione extends Argomento {

  public Lezione(String titolo, String corpo) {
    super(titolo, corpo);
  }
}
