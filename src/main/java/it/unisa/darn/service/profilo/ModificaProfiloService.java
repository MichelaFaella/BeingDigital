package it.unisa.darn.service.profilo;

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
 * Questa classe rappresenta il service per la modifica di un account di una persona.
 */

@Service
@Transactional
@Validated
public class ModificaProfiloService {

  @Autowired
  private PersonaRepository personaRepository;

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  /**
   * Implementa la funzionalità di cancellazione di un account di una persona.
   * Si assume che la corretta formulazione dei paramentri sia stata controllata prima
   * di effettuare la chiamata.
   *
   * @param persona  L'account della persona da modificare.
   * @param nome     Il nome modificato.
   * @param cognome  Il cognome modificato.
   * @param email    L'email modificata.
   * @param password La password modificata.
   * @return true se la modifica è andata a buon fine, false altrimenti.
   * @throws jakarta.validation.ConstraintViolationException se la persona risulta null.
   */

  public boolean modificaProfilo(@NotNull Persona persona, String nome, String cognome,
                                 String email, String password) {
    if (email != null) {
      Optional<Persona> optionalAltro = personaRepository.findByEmail(email);
      if (optionalAltro.isPresent() && !optionalAltro.get().getId().equals(persona.getId())) {
        return false;
      }
      persona.setEmail(email);
    }

    if (nome != null) {
      persona.setNome(nome);
    }

    if (cognome != null) {
      persona.setCognome(cognome);
    }

    if (password != null) {
      persona.setPassword(passwordEncryptor.encryptPassword(password));
    }

    personaRepository.save(persona);
    return true;
  }
}
