package it.unisa.darn.control.gestionerisorse;

import it.unisa.darn.control.gestionerisorse.form.GiocoForm;
import it.unisa.darn.service.gestionerisorse.InserimentoRisorsaService;
import it.unisa.darn.service.presentazionerisorse.PrelievoMetaInfoService;
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
@RequestMapping("/admin/inserimentoGioco")
public class InserimentoGiocoController {

  @Autowired
  private InserimentoRisorsaService inserimentoRisorsaService;

  @Autowired
  private PrelievoMetaInfoService prelievoMetaInfoService;

  @GetMapping
  public String get(@ModelAttribute GiocoForm giocoForm, Model model) {
    model.addAttribute("metaInfo", prelievoMetaInfoService.getMetaInfoSenzaGioco(null));

    return "gestionerisorse/modificaGioco";
  }

  @PostMapping
  public String post(@ModelAttribute @Valid GiocoForm giocoForm,
                     BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!inserimentoRisorsaService.inserimentoGioco(giocoForm.getNome(), giocoForm.getPath(),
        giocoForm.getMetaInfoId())) {
      model.addAttribute("nomeEsistente", true);
      model.addAttribute("metaInfo", prelievoMetaInfoService.getMetaInfoSenzaGioco(null));
      return "gestionerisorse/modificaGioco";
    }

    return "redirect:/admin/risorse";
  }
}
