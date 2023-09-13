package it.unisa.darn.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Domanda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include //include solo l'id
  private Long id;

  @Column(nullable = false)
  private String testo;

  @Column(nullable = false)
  private String corretta;

  @Column(nullable = false)
  private String sbagliata1;

  @Column(nullable = false)
  private String sbagliata2;

  @Column(nullable = false)
  private String sbagliata3;

  public Domanda(String testo, String corretta, String sbagliata1, String sbagliata2,
                 String sbagliata3) {
    this.testo = testo;
    this.corretta = corretta;
    this.sbagliata1 = sbagliata1;
    this.sbagliata2 = sbagliata2;
    this.sbagliata3 = sbagliata3;
  }
}
