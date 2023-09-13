package it.unisa.darn.storage.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Admin extends Persona {

  public Admin(String nome, String cognome, String email, String password) {
    super(nome, cognome, email, password);
  }

}
