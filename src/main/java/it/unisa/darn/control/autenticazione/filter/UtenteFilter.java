package it.unisa.darn.control.autenticazione.filter;

import it.unisa.darn.storage.entity.Persona;
import it.unisa.darn.storage.entity.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteFilter extends AuthFilter {

  @Override
  protected boolean isClassValid(Persona persona) {
    return persona instanceof Utente;
  }
}
