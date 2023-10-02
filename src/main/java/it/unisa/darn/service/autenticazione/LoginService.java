package it.unisa.darn.service.autenticazione;

import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.storage.entity.Persona;
import it.unisa.darn.storage.repository.PersonaRepository;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Questa classe rappresenta il service per il processo di autenticazione di una persona.
 */

@Service
@Transactional(readOnly = true)
@Validated
public class LoginService {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  @Autowired
  private PersonaRepository personaRepository;

  /**
   * Implementa la funzionalità del login di una persona
   *
   * @param email    Email della persona.
   * @param password Password della persona.
   * @return true se l'autenticazione è andata a buon fine, false altrimenti.
   * @throws jakarta.validation.ConstraintViolationException se l'email o la password risulta null.
   */
  public boolean login(@NotNull String email, @NotNull String password) {
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
