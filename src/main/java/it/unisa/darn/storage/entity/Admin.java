package it.unisa.darn.storage.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Admin extends Persona {

  public Admin(String nome, String cognome, String email, String password) {
    super(nome, cognome, email, password);
  }
}
