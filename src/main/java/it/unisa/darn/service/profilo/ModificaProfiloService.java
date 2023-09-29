package it.unisa.darn.service.profilo;

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

  public boolean modificaProfilo(Persona persona, String nome, String cognome, String email,
                                 String password) {
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
