package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.presentazionerisorse.PrelievoGiocoService;
import it.unisa.darn.storage.entity.util.Livello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/giochiCittadinanza")
public class GiochiCittadinanzaController {

  @Autowired
  private PrelievoGiocoService prelievoGiocoService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("giochi",
        prelievoGiocoService.getGiochiSortedByNome(Livello.CITTADINANZA_DIGITALE));
    model.addAttribute("tipo", "cittadinanza");
    return "presentazionerisorse/giochi";
  }
}
