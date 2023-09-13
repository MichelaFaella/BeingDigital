package it.unisa.darn.storage.entity;

import it.unisa.darn.storage.util.Livello;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Utente extends Persona {

  @Column(nullable = false, columnDefinition = "tinyint default 0")
  private Integer percentuale;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private Livello livello;

  public Utente(String nome, String cognome, String email, String password, Integer percentuale,
                Livello livello) {
    super(nome, cognome, email, password);
    this.percentuale = percentuale;
    this.livello = livello;
  }

}
