package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.service.presentazionerisorse.PrelievoRispostaService;
import it.unisa.darn.storage.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/utente/risposte")
public class RisposteController {

  @Autowired
  private PrelievoRispostaService prelievoRispostaService;

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @GetMapping
  public String get(Model model) {
    Utente utente = (Utente) personaAutenticata.getPersona().get();

    model.addAttribute("risposte", prelievoRispostaService.getRisposteSortedByKeywordTesto(utente));
    return "presentazionerisorse/risposte";
  }
}
