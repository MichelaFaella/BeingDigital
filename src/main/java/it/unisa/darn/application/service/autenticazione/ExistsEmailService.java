package it.unisa.darn.application.service.autenticazione;

import it.unisa.darn.storage.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ExistsEmailService {

  @Autowired
  private PersonaRepository personaRepository;

  public boolean existsEmail(String email) {
    return personaRepository.existsByEmail(email);
  }
}
