package it.unisa.darn.control.profilo;

import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.service.profilo.DatiUtentiService;
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
  private DatiUtentiService datiUtentiService;

  @GetMapping
  public String get(Model model) {
    Persona persona = personaAutenticata.getPersona().get();

    if (persona instanceof Admin) {
      model.addAttribute("admin", persona);
      model.addAttribute("listaUtenti", datiUtentiService.getAllUtenti());
      model.addAttribute("percentualeBase",
          datiUtentiService.getPercentualePerLivello(Livello.BASE));
      model.addAttribute("percentualeIntermedio",
          datiUtentiService.getPercentualePerLivello(Livello.INTERMEDIO));
      model.addAttribute("percentualeAvanzato",
          datiUtentiService.getPercentualePerLivello(Livello.AVANZATO));
      model.addAttribute("percentualeMaster",
          datiUtentiService.getPercentualePerLivello(Livello.MASTER));
      return "profilo/admin";
    } else {
      model.addAttribute("utente", persona);
      model.addAttribute("percentuale", datiUtentiService.getPercentualeCompletamento(
          (Utente) persona));
      return "profilo/utente";
    }
  }
}
