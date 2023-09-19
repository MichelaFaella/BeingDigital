package it.unisa.darn.application.control.profilo;

import it.unisa.darn.application.service.profilo.CancellazioneUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/cancellazioneUtente")
public class CancellazioneUtenteController {

  @Autowired
  private CancellazioneUtenteService cancellazioneUtenteService;

  @PostMapping
  public String post(@RequestParam Long id) {
    if (!cancellazioneUtenteService.cancellazioneUtente(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "redirect:/auth/areaPersonale";
  }
}
