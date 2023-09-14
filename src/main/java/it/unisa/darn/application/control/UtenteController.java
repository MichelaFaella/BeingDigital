package it.unisa.darn.application.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/utente")
public class UtenteController {

  @GetMapping
  public String get() {
    return "utente";
  }

}
