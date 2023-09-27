package it.unisa.darn.control.profilo;

import it.unisa.darn.service.profilo.CancellazioneAccountService;
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
  private CancellazioneAccountService cancellazioneAccountService;

  @PostMapping
  public String post(@RequestParam Long id) {
    if (!cancellazioneAccountService.cancellazioneUtente(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "redirect:/auth/areaPersonale";
  }
}
