package it.unisa.darn.storage.entity;

import it.unisa.darn.storage.util.Livello;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class MetaInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include //include solo l'id
  private Long id;

  @Column(nullable = false, unique = true)
  private String keyword;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private Livello livello;

  public MetaInfo(String keyword, Livello livello) {
    this.keyword = keyword;
    this.livello = livello;
  }
}
