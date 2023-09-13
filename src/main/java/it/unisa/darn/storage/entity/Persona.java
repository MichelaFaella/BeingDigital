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
public abstract class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include //include solo l'id
  private Long id;

  @Column(nullable = false, length = 30)
  private String nome;

  @Column(nullable = false, length = 30)
  private String cognome;

  @Column(nullable = false, length = 319, unique = true)
  private String email;

  @Column(nullable = false, columnDefinition = "char(48)")
  private String password;

  protected Persona(String nome, String cognome, String email, String password) {
    this.nome = nome;
    this.cognome = cognome;
    this.email = email;
    this.password = password;
  }


}
