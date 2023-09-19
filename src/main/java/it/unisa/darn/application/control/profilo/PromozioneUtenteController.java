package it.unisa.darn.application.control.profilo;

import it.unisa.darn.application.service.profilo.PromozioneUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/promozioneUtente")
public class PromozioneUtenteController {

  @Autowired
  private PromozioneUtenteService promozioneUtenteService;

  @PostMapping
  public String post(@RequestParam Long id) {
    if (!promozioneUtenteService.promozioneUtente(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "redirect:/auth/areaPersonale";
  }
}
