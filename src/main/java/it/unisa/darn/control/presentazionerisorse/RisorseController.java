package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.presentazionerisorse.PrelievoArgomentoService;
import it.unisa.darn.service.presentazionerisorse.PrelievoDomandaService;
import it.unisa.darn.service.presentazionerisorse.PrelievoGiocoService;
import it.unisa.darn.service.presentazionerisorse.PrelievoMetaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/risorse")
public class RisorseController {

  @Autowired
  private PrelievoArgomentoService prelievoArgomentoService;

  @Autowired
  private PrelievoMetaInfoService prelievoMetaInfoService;

  @Autowired
  private PrelievoGiocoService prelievoGiocoService;

  @Autowired
  private PrelievoDomandaService prelievoDomandaService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("lezioni",
        prelievoArgomentoService.getAllLezioniSortedByLivelloKeywordTitolo());
    model.addAttribute("racconti",
        prelievoArgomentoService.getAllRaccontiSortedByLivelloKeywordTitolo());
    model.addAttribute("metainfo",
        prelievoMetaInfoService.getAllMetaInfoSortedByLivelloKeyword());
    model.addAttribute("giochi",
        prelievoGiocoService.getAllGiochiSortedByLivelloKeyword());
    model.addAttribute("domande",
        prelievoDomandaService.getAllDomandeSortedByLivelloKeywordTesto());
    return "presentazionerisorse/risorse";
  }
}

