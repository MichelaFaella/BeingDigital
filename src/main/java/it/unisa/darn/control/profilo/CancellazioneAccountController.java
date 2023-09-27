package it.unisa.darn.control.profilo;

import it.unisa.darn.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.service.profilo.CancellazioneAccountService;
import it.unisa.darn.storage.entity.Admin;
import it.unisa.darn.storage.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/cancellazioneAccount")
public class CancellazioneAccountController {

  @Autowired
  private CancellazioneAccountService cancellazioneAccountService;

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @PostMapping
  public String post() {
    Persona persona = personaAutenticata.getPersona().get();

    if (persona instanceof Admin) {
      cancellazioneAccountService.cancellazioneAdmin(persona.getId());
    } else {
      cancellazioneAccountService.cancellazioneUtente(persona.getId());
    }

    return "redirect:/logout";
  }
}
