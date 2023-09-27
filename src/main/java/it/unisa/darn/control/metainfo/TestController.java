package it.unisa.darn.control.metainfo;

import it.unisa.darn.control.metainfo.form.RispostaFormsWrapper;
import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.service.metainfo.TestService;
import it.unisa.darn.service.metainfo.VisualizzazioneRisorseService;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import jakarta.validation.Valid;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/utente/test")
public class TestController {

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @Autowired
  private TestService testService;

  @GetMapping
  public String get(Model model) {
    Utente utente = (Utente) personaAutenticata.getPersona().get();

    if (utente.getLivello().equals(Livello.MASTER)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    model.addAttribute("domande",
        visualizzazioneRisorseService.getDomandeRandom(utente.getLivello()));
    model.addAttribute("livello", utente.getLivello());
    return "metainfo/test";
  }

  @PostMapping
  public String post(@Valid RispostaFormsWrapper rispostaFormsWrapper) {
    Utente utente = (Utente) personaAutenticata.getPersona().get();

    if (utente.getLivello().equals(Livello.MASTER)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    List<Map.Entry<Long, String>> risposte =
        rispostaFormsWrapper.getRispostaFormList().stream().map(
                rispostaForm -> (Map.Entry<Long, String>) new AbstractMap.SimpleEntry<>(
                    rispostaForm.getIdDomanda(),
                    rispostaForm.getRisposta()))
            .toList();

    try {
      if (testService.test(risposte, utente)) {
        return "redirect:/auth/areaPersonale";
      }
      return "redirect:/utente/risposte";
    } catch (IllegalArgumentException exception) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
