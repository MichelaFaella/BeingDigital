package it.unisa.darn.control.gestionerisorse;

import it.unisa.darn.control.gestionerisorse.form.DomandaForm;
import it.unisa.darn.service.gestionerisorse.ModificaRisorsaService;
import it.unisa.darn.service.presentazionerisorse.PrelievoDomandaService;
import it.unisa.darn.service.presentazionerisorse.PrelievoMetaInfoService;
import it.unisa.darn.storage.entity.Domanda;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/modificaDomanda")
public class ModificaDomandaController {

  @Autowired
  private ModificaRisorsaService modificaRisorsaService;

  @Autowired
  private PrelievoDomandaService prelievoDomandaService;

  @Autowired
  private PrelievoMetaInfoService prelievoMetaInfoService;

  @GetMapping
  public String get(@RequestParam Long id, @ModelAttribute DomandaForm domandaForm,
                    Model model) {
    Optional<Domanda> optional = prelievoDomandaService.getDomanda(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Domanda domanda = optional.get();
    domandaForm.setTesto(domanda.getTesto());
    domandaForm.setCorretta(domanda.getCorretta());
    domandaForm.setSbagliata1(domanda.getSbagliata1());
    domandaForm.setSbagliata2(domanda.getSbagliata2());
    domandaForm.setSbagliata3(domanda.getSbagliata3());
    domandaForm.setMetaInfoId(domanda.getMetaInfo().getId());

    model.addAttribute("metaInfo",
        prelievoMetaInfoService.getMetaInfoSortedByLivelloKeywordWithoutCittadinanza());

    return "gestionerisorse/modificaDomanda";
  }

  @PostMapping
  public String post(@RequestParam Long id,
                     @ModelAttribute @Valid DomandaForm domandaForm,
                     BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!modificaRisorsaService.modificaDomanda(id, domandaForm.getTesto(),
        domandaForm.getCorretta(), domandaForm.getSbagliata1(), domandaForm.getSbagliata2(),
        domandaForm.getSbagliata3(),
        domandaForm.getMetaInfoId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return "redirect:/admin/risorse";
  }
}
