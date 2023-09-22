package it.unisa.darn.application.control.metainfo;

//te la metto qui poi sposti tu

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/utente/effettuaTest")
public class EffettuaTestController {

  @GetMapping
  public String get() {
    return "metainfo/test";
  }
}
