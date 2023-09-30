package it.unisa.darn.control.gestionerisorse;

import it.unisa.darn.control.gestionerisorse.form.GiocoForm;
import it.unisa.darn.service.gestionerisorse.ModificaRisorsaService;
import it.unisa.darn.service.presentazionerisorse.PrelievoGiocoService;
import it.unisa.darn.service.presentazionerisorse.PrelievoMetaInfoService;
import it.unisa.darn.storage.entity.Gioco;
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
@RequestMapping("/admin/modificaGioco")
public class ModificaGiocoController {

  @Autowired
  private ModificaRisorsaService modificaRisorsaService;

  @Autowired
  private PrelievoMetaInfoService prelievoMetaInfoService;

  @Autowired
  private PrelievoGiocoService prelievoGiocoService;

  @GetMapping
  public String get(@RequestParam Long id, @ModelAttribute GiocoForm giocoForm,
                    Model model) {
    Optional<Gioco> optional = prelievoGiocoService.getGioco(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Gioco gioco = optional.get();
    giocoForm.setNome(gioco.getNome());
    giocoForm.setPath(gioco.getPath());
    giocoForm.setMetaInfoId(gioco.getMetaInfo().getId());

    model.addAttribute("metaInfo", prelievoMetaInfoService.getMetaInfoSenzaGioco(
        giocoForm.getMetaInfoId()));

    return "gestionerisorse/modificaGioco";
  }

  @PostMapping
  public String post(@RequestParam Long id,
                     @ModelAttribute @Valid GiocoForm giocoForm,
                     BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!modificaRisorsaService.modificaGioco(id, giocoForm.getNome(), giocoForm.getPath(),
        giocoForm.getMetaInfoId())) {
      model.addAttribute("nomeEsistente", true);
      model.addAttribute("metaInfo", prelievoMetaInfoService.getMetaInfoSenzaGioco(
          giocoForm.getMetaInfoId()));
      return "gestionerisorse/modificaGioco";
    }

    return "redirect:/admin/risorse";
  }
}
