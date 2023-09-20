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
                                 String passwordAttuale, String passwordNuova) {
    Optional<Persona> optional = personaRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    Persona persona = optional.get();
    Optional<Persona> optionalAltro = personaRepository.findByEmail(email);
    if (optionalAltro.isPresent() && !optionalAltro.get().equals(persona)) {
      return false;
    }

    if (passwordAttuale != null && passwordNuova == null) {
      return false;
    } else if (passwordAttuale != null) {
      if (!passwordEncryptor.checkPassword(passwordAttuale, persona.getPassword())) {
        return false;
      }
      persona.setPassword(passwordEncryptor.encryptPassword(passwordNuova));
    }

    persona.setEmail(email);
    persona.setNome(nome);
    persona.setCognome(cognome);
    return true;
  }
}
