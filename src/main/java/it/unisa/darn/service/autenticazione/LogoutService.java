package it.unisa.darn.service.autenticazione;

import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  public void logout() {
    personaAutenticata.setPersona(null);
  }
}
