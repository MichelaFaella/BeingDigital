package it.unisa.darn.storage.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Racconto extends Argomento {

  public Racconto(String titolo, String corpo, MetaInfo metaInfo) {
    super(titolo, corpo, metaInfo);
  }
}
