package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.presentazionerisorse.PrelievoArgomentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/racconti")
public class RaccontiController {

  @Autowired
  private PrelievoArgomentoService prelievoArgomentoService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("racconti", prelievoArgomentoService.getAllRaccontiSortedByTitolo());
    return "presentazionerisorse/listaRacconti";
  }
}
