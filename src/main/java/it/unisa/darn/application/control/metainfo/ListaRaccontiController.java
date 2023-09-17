package it.unisa.darn.application.control.metainfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listaRacconti")
public class ListaRaccontiController {

  @GetMapping
  public String get() {
    return "listaRacconti";
  }
}
