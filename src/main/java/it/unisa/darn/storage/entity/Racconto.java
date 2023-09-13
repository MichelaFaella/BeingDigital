package it.unisa.darn.storage.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Racconto extends Argomento {

  public Racconto(String titolo, String corpo) {
    super(titolo, corpo);
  }
}
