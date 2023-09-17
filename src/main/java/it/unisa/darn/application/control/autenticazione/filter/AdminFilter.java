package it.unisa.darn.application.control.autenticazione.filter;

import it.unisa.darn.storage.entity.Admin;
import it.unisa.darn.storage.entity.Persona;
import org.springframework.stereotype.Component;

@Component
public class AdminFilter extends AuthFilter {

  @Override
  protected boolean isClassValid(Persona persona) {
    return persona instanceof Admin;
  }
}
