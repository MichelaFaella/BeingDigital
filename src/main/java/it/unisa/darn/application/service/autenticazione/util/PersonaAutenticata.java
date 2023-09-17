package it.unisa.darn.application.service.autenticazione.util;

import it.unisa.darn.storage.entity.Persona;
import it.unisa.darn.storage.repository.PersonaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class PersonaAutenticata {

  private Long id;

  @Autowired
  private PersonaRepository personaRepository;

  public Optional<Persona> getPersona() {
    return id != null ? personaRepository.findById(id) : Optional.empty();
  }

  public void setPersona(Persona persona) {
    id = persona == null ? null : persona.getId();
  }
}
