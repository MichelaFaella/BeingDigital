package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.service.metainfo.VisualizzazioneRisorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/visualizzazioneRisorse")
public class VisualizzazioneRisorseController {

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("lezioni", visualizzazioneRisorseService.getAllLezioni());
    model.addAttribute("racconti", visualizzazioneRisorseService.getAllRacconti());
    model.addAttribute("metainfo", visualizzazioneRisorseService.getAllMetaInfo());
    model.addAttribute("giochi", visualizzazioneRisorseService.getAllGiochi());
    model.addAttribute("domande", visualizzazioneRisorseService.getAllDomande());
    return "metainfo/risorse";
  }
}

