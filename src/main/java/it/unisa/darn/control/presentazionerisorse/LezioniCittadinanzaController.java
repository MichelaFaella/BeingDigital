package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.presentazionerisorse.PrelievoArgomentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lezioniCittadinanza")
public class LezioniCittadinanzaController {

  @Autowired
  private PrelievoArgomentoService prelievoArgomentoService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("lezioniPerMetaInfo",
        prelievoArgomentoService.getLezioniCittadinanzaPerMetaInfoSortedByKeywordTitolo());
    model.addAttribute("tipo", "cittadinanza");
    return "presentazionerisorse/listaLezioni";
  }
}
