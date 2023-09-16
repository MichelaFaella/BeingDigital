package it.unisa.darn.application.control;

import it.unisa.darn.application.control.form.LoginForm;
import it.unisa.darn.application.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private LoginService loginService;

  @GetMapping
  public String get(@ModelAttribute LoginForm loginForm) {
    return "login";
  }

  @PostMapping
  public String post(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult,
                     @RequestParam(required = false) String risorsa, Model model) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!loginService.login(loginForm.getEmail(), loginForm.getPassword())) {
      model.addAttribute("error", true);
      return "login";
    }

    if (risorsa != null && !risorsa.isBlank()) {
      return "redirect:" + risorsa;
    }
    return "redirect:/areaPersonale";
  }
}
