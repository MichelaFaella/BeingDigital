package it.unisa.darn.control.profilo;

import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.service.profilo.AreaAdminService;
import it.unisa.darn.service.profilo.AreaUtenteService;
import it.unisa.darn.storage.entity.Admin;
import it.unisa.darn.storage.entity.Persona;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
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

  @Autowired
  private AreaAdminService areaAdminService;

  @GetMapping
  public String get(Model model) {
    Persona persona = personaAutenticata.getPersona().get();

    if (persona instanceof Admin) {
      model.addAttribute("admin", persona);
      model.addAttribute("listaUtenti", areaAdminService.getAllUtenti());
      model.addAttribute("percentualeBase",
          areaAdminService.getPercentualePerLivello(Livello.BASE));
      model.addAttribute("percentualeIntermedio",
          areaAdminService.getPercentualePerLivello(Livello.INTERMEDIO));
      model.addAttribute("percentualeAvanzato",
          areaAdminService.getPercentualePerLivello(Livello.AVANZATO));
      model.addAttribute("percentualeMaster",
          areaAdminService.getPercentualePerLivello(Livello.MASTER));
      return "profilo/admin";
    } else {
      model.addAttribute("utente", persona);
      model.addAttribute("percentuale", areaUtenteService.getPercentualeCompletamento(
          (Utente) persona));
      return "profilo/utente";
    }
  }
}
