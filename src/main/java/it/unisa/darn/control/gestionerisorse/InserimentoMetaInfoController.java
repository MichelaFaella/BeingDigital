package it.unisa.darn.control.gestionerisorse;

import it.unisa.darn.control.gestionerisorse.form.MetaInfoForm;
import it.unisa.darn.service.gestionerisorse.InserimentoRisorsaService;
import jakarta.validation.Valid;
import java.io.IOException;
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
@RequestMapping("/admin/inserimentoMetaInfo")
public class InserimentoMetaInfoController {

  @Autowired
  private InserimentoRisorsaService inserimentoRisorsaService;

  @GetMapping
  public String get(@ModelAttribute MetaInfoForm metaInfoForm) {
    return "gestionerisorse/modificaMetaInfo";
  }

  @PostMapping
  public String post(@ModelAttribute @Valid MetaInfoForm metaInfoForm,
                     BindingResult bindingResult, Model model) throws IOException {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (metaInfoForm.getIcona() == null || metaInfoForm.getIcona().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!inserimentoRisorsaService.inserimentoMetaInfo(metaInfoForm.getKeyword(),
        metaInfoForm.getLivello(), metaInfoForm.getIcona().getBytes())) {
      model.addAttribute("keywordEsistente", true);
      return "gestionerisorse/modificaMetaInfo";
    }

    return "redirect:/admin/risorse";
  }
}
