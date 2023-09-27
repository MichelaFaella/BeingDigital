package it.unisa.darn.control.metainfo;

import it.unisa.darn.service.metainfo.VisualizzazioneRisorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/racconti")
public class RaccontiController {

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("racconti", visualizzazioneRisorseService.getAllRaccontiSortedByTitolo());
    return "metainfo/listaRacconti";
  }
}
