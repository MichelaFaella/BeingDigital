package it.unisa.darn.application.control.filter;

import it.unisa.darn.storage.entity.Admin;
import it.unisa.darn.storage.entity.Persona;
import org.springframework.stereotype.Component;

@Component
public class AdminFilter extends PersonaFilter {

  @Override
  protected boolean isClassValid(Persona persona) {
    return persona instanceof Admin;
  }
}
