package it.unisa.darn.storage.entity;

import it.unisa.darn.storage.entity.util.Livello;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Questa classe rappresenta un meta-info.
 * Una è una macro categoria che indica un preciso argomento.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MetaInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String keyword;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Livello livello;

  public MetaInfo(String keyword, Livello livello) {
    this.keyword = keyword;
    this.livello = livello;
  }
}
