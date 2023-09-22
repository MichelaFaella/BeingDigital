package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.control.metainfo.form.ArgomentoForm;
import it.unisa.darn.application.service.metainfo.ModificaRisorsaService;
import it.unisa.darn.application.service.metainfo.VisualizzazioneRisorsaService;
import it.unisa.darn.application.service.metainfo.VisualizzazioneRisorseService;
import it.unisa.darn.storage.entity.Argomento;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ModificaRisorsaController {

  @Autowired
  private ModificaRisorsaService modificaRisorsaService;

  @Autowired
  private VisualizzazioneRisorsaService visualizzazioneRisorsaService;

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @GetMapping("/admin/modificaArgomento")
  public String modificaArgomentoGet(@RequestParam Long id,
                                     @ModelAttribute ArgomentoForm argomentoForm, Model model) {
    Optional<Argomento> optional = visualizzazioneRisorsaService.getArgomento(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Argomento argomento = optional.get();
    argomentoForm.setTitolo(argomento.getTitolo());
    argomentoForm.setCorpo(argomento.getCorpo());
    argomentoForm.setMetaInfoId(argomento.getMetaInfo().getId());

    model.addAttribute("metaInfo", visualizzazioneRisorseService.getAllMetaInfo());

    return "metainfo/modificaArgomento";
  }

  @PostMapping("/admin/modificaArgomento")
  public String modificaArgomentoPost(@RequestParam Long id,
                                      @ModelAttribute @Valid ArgomentoForm argomentoForm,
                                      BindingResult bindingResult) throws IOException {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    byte[] copertina = null;
    if (argomentoForm.getCopertina() != null && !argomentoForm.getCopertina().isEmpty()) {
      copertina = argomentoForm.getCopertina().getBytes();
    }

    if (!modificaRisorsaService.modificaArgomento(id, argomentoForm.getTitolo(),
        argomentoForm.getCorpo(), copertina,
        argomentoForm.getMetaInfoId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return "redirect:/admin/visualizzazioneRisorse";
  }

  @GetMapping("/admin/modificaMetaInfo")
  public String modificaMetaInfoGet() {
    return "metainfo/modificaMetaInfo";
  }
}
