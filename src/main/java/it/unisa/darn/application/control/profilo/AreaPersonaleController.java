package it.unisa.darn.application.control.profilo;

import it.unisa.darn.application.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.application.service.profilo.AreaUtenteService;
import it.unisa.darn.storage.entity.Admin;
import it.unisa.darn.storage.entity.Persona;
import it.unisa.darn.storage.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/areaPersonale")
public class AreaPersonaleController {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @Autowired
  private AreaUtenteService areaUtenteService;

  @GetMapping
  public String get(Model model) {
    Persona persona = personaAutenticata.getPersona().get();

    if (persona instanceof Admin) {
      model.addAttribute("admin", persona);
      return "admin";
    } else {
      model.addAttribute("utente", persona);
      model.addAttribute("percentuale", areaUtenteService.getPercentualeCompletamento(
          (Utente) persona));
      return "utente";
    }
  }
}
