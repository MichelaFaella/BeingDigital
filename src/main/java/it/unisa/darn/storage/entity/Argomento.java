package it.unisa.darn.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public abstract class Argomento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include //include solo l'id
  private Long id;

  @Column(nullable = false)
  private String titolo;

  @Column(nullable = false, length = 65535)
  private String corpo;

  public Argomento(String titolo, String corpo) {
    this.titolo = titolo;
    this.corpo = corpo;
  }
}
