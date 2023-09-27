package it.unisa.darn.service.autenticazione;

import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.storage.entity.Persona;
import it.unisa.darn.storage.repository.PersonaRepository;
import java.util.Optional;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoginService {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  @Autowired
  private PersonaRepository personaRepository;

  public boolean login(String email, String password) {
    Optional<Persona> optional = personaRepository.findByEmail(email);
    if (optional.isEmpty()) {
      return false;
    }

    Persona persona = optional.get();
    if (passwordEncryptor.checkPassword(password, persona.getPassword())) {
      personaAutenticata.setPersona(persona);
      return true;
    }
    return false;
  }
}
