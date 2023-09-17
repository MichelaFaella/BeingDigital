package it.unisa.darn.application.control.profilo;

import it.unisa.darn.application.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.storage.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/areaPersonale")
public class AreaPersonaleController {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @GetMapping
  public String get() {
    if (personaAutenticata.getPersona().get() instanceof Admin) {
      return "admin";
    }
    return "utente";
  }
}
