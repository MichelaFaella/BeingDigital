package it.unisa.darn.application.control.autenticazione;

import it.unisa.darn.application.control.autenticazione.form.RegistrazioneForm;
import it.unisa.darn.application.service.autenticazione.RegistrazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

  @Autowired
  private RegistrazioneService registrazioneService;

  @PostMapping
  public String post(@ModelAttribute @Valid RegistrazioneForm registrazioneForm,
                     BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!registrazioneService.registrazione(registrazioneForm.getNome(),
        registrazioneForm.getCognome(), registrazioneForm.getEmail(),
        registrazioneForm.getPassword())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "redirect:/login?registrazione=true";
  }
}
