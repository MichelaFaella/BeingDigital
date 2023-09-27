package it.unisa.darn.service.autenticazione;

import it.unisa.darn.storage.entity.Persona;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckPasswordService {

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  public boolean checkPassword(Persona persona, String password) {
    return passwordEncryptor.checkPassword(password, persona.getPassword());
  }
}