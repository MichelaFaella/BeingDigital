package it.unisa.darn.application.service.profilo;

import it.unisa.darn.storage.entity.Persona;
import it.unisa.darn.storage.repository.PersonaRepository;
import java.util.Optional;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModificaProfiloService {

  @Autowired
  private PersonaRepository personaRepository;

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  public boolean modificaProfilo(Long id, String nome, String cognome, String email,
                                 String password) {
    Optional<Persona> optional = personaRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    Persona persona = optional.get();

    if (email != null) {
      Optional<Persona> optionalAltro = personaRepository.findByEmail(email);
      if (optionalAltro.isPresent() && !optionalAltro.get().equals(persona)) {
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

    return true;
  }
}
