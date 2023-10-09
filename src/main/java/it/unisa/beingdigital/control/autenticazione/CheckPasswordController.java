package it.unisa.beingdigital.control.autenticazione;

import it.unisa.beingdigital.service.autenticazione.CheckPasswordService;
import it.unisa.beingdigital.service.autenticazione.util.PersonaAutenticata;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth/checkPassword")
public class CheckPasswordController {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @Autowired
  private CheckPasswordService checkPasswordService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> post(@RequestParam String password) {
    boolean result =
        checkPasswordService.checkPassword(personaAutenticata.getPersona().get(), password);

    return ResponseEntity.ok(Collections.singletonMap("result", result));
  }
}
