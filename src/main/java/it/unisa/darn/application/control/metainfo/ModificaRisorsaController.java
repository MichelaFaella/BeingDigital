package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.control.metainfo.form.ArgomentoForm;
import it.unisa.darn.application.control.metainfo.form.DomandaForm;
import it.unisa.darn.application.control.metainfo.form.GiocoForm;
import it.unisa.darn.application.control.metainfo.form.MetaInfoForm;
import it.unisa.darn.application.service.metainfo.ModificaRisorsaService;
import it.unisa.darn.application.service.metainfo.VisualizzazioneRisorsaService;
import it.unisa.darn.application.service.metainfo.VisualizzazioneRisorseService;
import it.unisa.darn.storage.entity.Argomento;
import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.MetaInfo;
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

    model.addAttribute("metaInfo",
        visualizzazioneRisorseService.getAllMetaInfoSortedByLivelloKeyword());

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

    return "redirect:/admin/risorse";
  }

  @GetMapping("/admin/modificaMetaInfo")
  public String modificaMetaInfoGet(@RequestParam Long id,
                                    @ModelAttribute MetaInfoForm metaInfoForm) {
    Optional<MetaInfo> optional = visualizzazioneRisorsaService.getMetaInfo(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    MetaInfo metaInfo = optional.get();
    metaInfoForm.setKeyword(metaInfo.getKeyword());
    metaInfoForm.setLivello(metaInfo.getLivello());
    return "metainfo/modificaMetaInfo";
  }

  @PostMapping("/admin/modificaMetaInfo")
  public String modificaMetaInfoPost(@RequestParam Long id,
                                     @ModelAttribute @Valid MetaInfoForm metaInfoForm,
                                     BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!modificaRisorsaService.modificaMetaInfo(id, metaInfoForm.getKeyword(),
        metaInfoForm.getLivello())) {
      model.addAttribute("keywordEsistente", true);
      return "metainfo/modificaMetaInfo";
    }

    return "redirect:/admin/risorse";
  }

  @GetMapping("/admin/modificaDomanda")
  public String modificaDomandaGet(@RequestParam Long id, @ModelAttribute DomandaForm domandaForm,
                                   Model model) {
    Optional<Domanda> optional = visualizzazioneRisorsaService.getDomanda(id);
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
        visualizzazioneRisorseService.getAllMetaInfoSortedByLivelloKeyword());

    return "metainfo/modificaDomanda";
  }

  @PostMapping("/admin/modificaDomanda")
  public String modificaDomandaPost(@RequestParam Long id,
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

  @GetMapping("/admin/modificaGioco")
  public String modificaGiocoGet(@RequestParam Long id, @ModelAttribute GiocoForm giocoForm,
                                 Model model) {
    Optional<Gioco> optional = visualizzazioneRisorsaService.getGioco(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Gioco gioco = optional.get();
    giocoForm.setNome(gioco.getNome());
    giocoForm.setPath(gioco.getPath());
    giocoForm.setMetaInfoId(gioco.getMetaInfo().getId());

    model.addAttribute("metaInfo", visualizzazioneRisorseService.getMetaInfoSenzaGioco(
        giocoForm.getMetaInfoId()));

    return "metainfo/modificaGioco";
  }

  @PostMapping("/admin/modificaGioco")
  public String modificaGiocoPost(@RequestParam Long id,
                                  @ModelAttribute @Valid GiocoForm giocoForm,
                                  BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!modificaRisorsaService.modificaGioco(id, giocoForm.getNome(), giocoForm.getPath(),
        giocoForm.getMetaInfoId())) {
      model.addAttribute("nomeEsistente", true);
      model.addAttribute("metaInfo", visualizzazioneRisorseService.getMetaInfoSenzaGioco(
          giocoForm.getMetaInfoId()));
      return "metainfo/modificaGioco";
    }

    return "redirect:/admin/risorse";
  }
}
