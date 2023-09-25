package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.control.metainfo.form.ArgomentoForm;
import it.unisa.darn.application.control.metainfo.form.DomandaForm;
import it.unisa.darn.application.control.metainfo.form.GiocoForm;
import it.unisa.darn.application.control.metainfo.form.MetaInfoForm;
import it.unisa.darn.application.service.metainfo.InserimentoRisorsaService;
import it.unisa.darn.application.service.metainfo.VisualizzazioneRisorseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class InserimentoRisorsaController {

  @Autowired
  private InserimentoRisorsaService inserimentoRisorsaService;

  @Autowired
  private VisualizzazioneRisorseService visualizzazioneRisorseService;

  @GetMapping("/admin/inserimentoArgomento")
  public String inserimentoArgomentoGet(@ModelAttribute ArgomentoForm argomentoForm, Model model) {
    model.addAttribute("metaInfo", visualizzazioneRisorseService.getAllMetaInfo());

    return "metainfo/modificaArgomento";
  }

  @PostMapping("/admin/inserimentoArgomento")
  public String inserimentoArgomentoPost(@RequestParam String tipo,
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
    return "redirect:/admin/visualizzazioneRisorse";
  }

  @GetMapping("/admin/inserimentoMetaInfo")
  public String inserimentoMetaInfoGet(@ModelAttribute MetaInfoForm metaInfoForm) {
    return "metainfo/modificaMetaInfo";
  }

  @PostMapping("/admin/inserimentoMetaInfo")
  public String inserimentoMetaInfoPost(@ModelAttribute @Valid MetaInfoForm metaInfoForm,
                                        BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!inserimentoRisorsaService.inserimentoMetaInfo(metaInfoForm.getKeyword(),
        metaInfoForm.getLivello())) {
      model.addAttribute("keywordEsistente", true);
      return "metainfo/modificaMetaInfo";
    }

    return "redirect:/admin/visualizzazioneRisorse";
  }

  @GetMapping("/admin/inserimentoDomanda")
  public String inserimentoDomandaGet(@ModelAttribute DomandaForm domandaForm, Model model) {
    model.addAttribute("metaInfo", visualizzazioneRisorseService.getAllMetaInfo());

    return "metainfo/modificaDomanda";
  }

  @PostMapping("/admin/inserimentoDomanda")
  public String inserimentoDomandaPost(@ModelAttribute @Valid DomandaForm domandaForm,
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

    return "redirect:/admin/visualizzazioneRisorse";
  }

  @GetMapping("/admin/inserimentoGioco")
  public String inserimentoGiocoGet(@ModelAttribute GiocoForm giocoForm, Model model) {
    model.addAttribute("metaInfo", visualizzazioneRisorseService.getAllMetaInfo());

    return "metainfo/modificaGioco";
  }

  @PostMapping("/admin/inserimentoGioco")
  public String inserimentoGiocoPost(@ModelAttribute @Valid GiocoForm giocoForm,
                                     BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!inserimentoRisorsaService.inserimentoGioco(giocoForm.getNome(), giocoForm.getPath(),
        giocoForm.getMetaInfoId())) {
      model.addAttribute("nomeEsistente", true);
      return "metainfo/modificaGioco";
    }

    return "redirect:/admin/visualizzazioneRisorse";
  }
}
