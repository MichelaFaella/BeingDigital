package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.application.service.metainfo.VisualizzazioneRisorseService;
import it.unisa.darn.storage.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/utente/listaLezioni")
public class ListaLezioniController {

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @GetMapping
  public String get(Model model) {
    Utente utente = (Utente) personaAutenticata.getPersona().get();

    model.addAttribute("lezioniPerMetaInfo",
        visualizzazioneRisorseService.getLezioniDaStudiare(utente));
    return "metainfo/listaLezioni";
  }
}
