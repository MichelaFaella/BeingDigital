package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.presentazionerisorse.VisualizzazioneRisorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cittadinanzaDigitale")
public class CittadinanzaDigitaleController {

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("lezioniPerMetaInfo",
        visualizzazioneRisorseService.getLezioniCittadinanzaDigitale());
    return "presentazionerisorse/listaLezioniCittadinanza";
  }
}
