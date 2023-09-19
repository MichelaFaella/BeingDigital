package it.unisa.darn.application.control.autenticazione;

import it.unisa.darn.application.service.autenticazione.ExistsEmailService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/existsEmail")
public class ExistsEmailController {

  @Autowired
  private ExistsEmailService existsEmailService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> post(@RequestParam String email) {
    boolean result = existsEmailService.existsEmail(email);

    return ResponseEntity.ok(Collections.singletonMap("result", result));
  }
}
