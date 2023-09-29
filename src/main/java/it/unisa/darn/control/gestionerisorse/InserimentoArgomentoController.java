package it.unisa.darn.control.gestionerisorse;

import it.unisa.darn.control.gestionerisorse.form.ArgomentoForm;
import it.unisa.darn.service.gestionerisorse.InserimentoRisorsaService;
import it.unisa.darn.service.presentazionerisorse.VisualizzazioneRisorseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/inserimentoArgomento")
public class InserimentoArgomentoController {

  @Autowired
  private InserimentoRisorsaService inserimentoRisorsaService;

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @GetMapping
  public String get(@ModelAttribute ArgomentoForm argomentoForm, Model model) {
    model.addAttribute("metaInfo",
        visualizzazioneRisorseService.getAllMetaInfoSortedByLivelloKeyword());

    return "gestionerisorse/modificaArgomento";
  }

  @PostMapping
  public String post(@RequestParam String tipo,
                     @ModelAttribute @Valid ArgomentoForm argomentoForm,
                     BindingResult bindingResult) throws IOException {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (argomentoForm.getCopertina() == null || argomentoForm.getCopertina().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    boolean result = switch (tipo) {
      case "lezione" -> inserimentoRisorsaService.inserimentoLezione(argomentoForm.getTitolo(),
          argomentoForm.getCorpo(), argomentoForm.getCopertina().getBytes(),
          argomentoForm.getMetaInfoId());
      case "racconto" -> inserimentoRisorsaService.inserimentoRacconto(argomentoForm.getTitolo(),
          argomentoForm.getCorpo(), argomentoForm.getCopertina().getBytes(),
          argomentoForm.getMetaInfoId());
      default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    };

    if (!result) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "redirect:/admin/risorse";
  }
}
