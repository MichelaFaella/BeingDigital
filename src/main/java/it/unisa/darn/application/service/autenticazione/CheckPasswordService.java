package it.unisa.darn.application.service.autenticazione;

import it.unisa.darn.storage.entity.Persona;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CheckPasswordService {

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  public boolean checkPassword(Persona persona, String password) {
    return passwordEncryptor.checkPassword(password, persona.getPassword());
  }
}
