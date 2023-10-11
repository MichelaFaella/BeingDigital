package it.unisa.beingdigital.control.presentazionerisorse;

import it.unisa.beingdigital.service.autenticazione.util.PersonaAutenticata;
import it.unisa.beingdigital.service.presentazionerisorse.PrelievoGiocoService;
import it.unisa.beingdigital.storage.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Questa classe rappresenta il controller per visualizzare i giochi inerenti alla privacy.
 */

@Controller
@RequestMapping("/utente/giochiPrivacy")
public class GiochiPrivacyController {

  @Autowired
  private PrelievoGiocoService prelievoGiocoService;

  @Autowired
  private PersonaAutenticata personaAutenticata;

  /**
   * Implementa il get per la visualizzazione dei giochi di privacy.
   *
   * @param model Model da passare alla view.
   */
  @GetMapping
  public String get(Model model) {
    Utente utente = (Utente) personaAutenticata.getPersona().get();

    model.addAttribute("giochi", prelievoGiocoService.getGiochiSortedByNome(utente.getLivello()));
    model.addAttribute("tipo", "privacy");
    return "presentazionerisorse/giochi";
  }
}
