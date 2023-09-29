package it.unisa.darn.control.gestionerisorse;

import it.unisa.darn.control.gestionerisorse.form.MetaInfoForm;
import it.unisa.darn.service.gestionerisorse.ModificaRisorsaService;
import it.unisa.darn.service.presentazionerisorse.VisualizzazioneRisorsaService;
import it.unisa.darn.storage.entity.MetaInfo;
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
@RequestMapping("/admin/modificaMetaInfo")
public class ModificaMetaInfoController {

  @Autowired
  private ModificaRisorsaService modificaRisorsaService;

  @Autowired
  private VisualizzazioneRisorsaService visualizzazioneRisorsaService;

  @GetMapping
  public String get(@RequestParam Long id,
                    @ModelAttribute MetaInfoForm metaInfoForm) {
    Optional<MetaInfo> optional = visualizzazioneRisorsaService.getMetaInfo(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    MetaInfo metaInfo = optional.get();
    metaInfoForm.setKeyword(metaInfo.getKeyword());
    metaInfoForm.setLivello(metaInfo.getLivello());
    return "gestionerisorse/modificaMetaInfo";
  }

  @PostMapping
  public String post(@RequestParam Long id,
                     @ModelAttribute @Valid MetaInfoForm metaInfoForm,
                     BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!modificaRisorsaService.modificaMetaInfo(id, metaInfoForm.getKeyword(),
        metaInfoForm.getLivello())) {
      model.addAttribute("keywordEsistente", true);
      return "gestionerisorse/modificaMetaInfo";
    }

    return "redirect:/admin/risorse";
  }
}
