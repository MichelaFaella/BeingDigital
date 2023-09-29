package it.unisa.darn.control.gestionerisorse;

import it.unisa.darn.control.gestionerisorse.form.DomandaForm;
import it.unisa.darn.service.gestionerisorse.InserimentoRisorsaService;
import it.unisa.darn.service.presentazionerisorse.VisualizzazioneRisorseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/inserimentoDomanda")
public class InserimentoDomandaController {

  @Autowired
  private InserimentoRisorsaService inserimentoRisorsaService;

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @GetMapping
  public String get(@ModelAttribute DomandaForm domandaForm, Model model) {
    model.addAttribute("metaInfo",
        visualizzazioneRisorseService.getAllMetaInfoSortedByLivelloKeyword());

    return "gestionerisorse/modificaDomanda";
  }

  @PostMapping
  public String post(@ModelAttribute @Valid DomandaForm domandaForm,
                     BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!inserimentoRisorsaService.inserimentoDomanda(domandaForm.getTesto(),
        domandaForm.getCorretta(), domandaForm.getSbagliata1(), domandaForm.getSbagliata2(),
        domandaForm.getSbagliata3(),
        domandaForm.getMetaInfoId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return "redirect:/admin/risorse";
  }
}
