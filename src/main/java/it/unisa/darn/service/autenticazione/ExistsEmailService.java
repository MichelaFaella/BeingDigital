package it.unisa.darn.service.autenticazione;

import it.unisa.darn.storage.repository.PersonaRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
@Validated
public class ExistsEmailService {

  @Autowired
  private PersonaRepository personaRepository;

  public boolean existsEmail(@NotNull String email) {
    return personaRepository.existsByEmail(email);
  }
}
