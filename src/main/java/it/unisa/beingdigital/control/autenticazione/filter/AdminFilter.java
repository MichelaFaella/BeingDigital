package it.unisa.beingdigital.control.autenticazione.filter;

import it.unisa.beingdigital.storage.entity.Admin;
import it.unisa.beingdigital.storage.entity.Persona;
import org.springframework.stereotype.Component;

@Component
public class AdminFilter extends AuthFilter {

  @Override
  protected boolean isClassValid(Persona persona) {
    return persona instanceof Admin;
  }
}
