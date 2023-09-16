package it.unisa.darn.storage.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Racconto extends Argomento {

  public Racconto(String titolo, String corpo, byte[] copertina, MetaInfo metaInfo) {
    super(titolo, corpo, copertina, metaInfo);
  }
}
