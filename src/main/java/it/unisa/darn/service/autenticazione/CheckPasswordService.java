package it.unisa.darn.service.autenticazione;

import it.unisa.darn.storage.entity.Persona;
import jakarta.validation.constraints.NotNull;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CheckPasswordService {

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  public boolean checkPassword(@NotNull Persona persona, String password) {
    return passwordEncryptor.checkPassword(password, persona.getPassword());
  }
}
