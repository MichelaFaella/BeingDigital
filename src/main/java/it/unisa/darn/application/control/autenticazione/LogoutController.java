package it.unisa.darn.application.control.autenticazione;

import it.unisa.darn.application.service.autenticazione.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

  @Autowired
  private LogoutService logoutService;

  @GetMapping
  public String get() {
    logoutService.logout();
    return "redirect:/";
  }
}
