package it.unisa.darn.application.control.metainfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/utente/test")
public class TestController {

  @GetMapping
  public String get() {
    return "metainfo/test";
  }
}
