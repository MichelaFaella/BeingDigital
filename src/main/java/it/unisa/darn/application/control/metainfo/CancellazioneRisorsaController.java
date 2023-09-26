package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.service.metainfo.CancellazioneRisorsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/cancellazioneRisorsa")
public class CancellazioneRisorsaController {

  @Autowired
  private CancellazioneRisorsaService cancellazioneRisorsaService;

  @PostMapping
  public String post(@RequestParam String risorsa, @RequestParam Long id) {
    boolean result = switch (risorsa) {
      case "metainfo" -> cancellazioneRisorsaService.cancellazioneMetaInfo(id);
      case "gioco" -> cancellazioneRisorsaService.cancellazioneGioco(id);
      case "argomento" -> cancellazioneRisorsaService.cancellazioneArgomento(id);
      case "domanda" -> cancellazioneRisorsaService.cancellazioneDomanda(id);
      default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    };

    if (!result) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "redirect:/admin/risorse";
  }
}
