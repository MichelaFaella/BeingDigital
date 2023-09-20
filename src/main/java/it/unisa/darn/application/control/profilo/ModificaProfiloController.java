package it.unisa.darn.application.control.profilo;

import it.unisa.darn.application.control.profilo.form.ModificaProfiloForm;
import it.unisa.darn.application.service.autenticazione.CheckPasswordService;
import it.unisa.darn.application.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.application.service.profilo.ModificaProfiloService;
import it.unisa.darn.storage.entity.Persona;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/auth/modificaProfilo")
public class ModificaProfiloController {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @Autowired
  private ModificaProfiloService modificaProfiloService;

  @Autowired
  private CheckPasswordService checkPasswordService;

  @GetMapping
  public String get(@ModelAttribute ModificaProfiloForm modificaProfiloForm) {
    Persona persona = personaAutenticata.getPersona().get();

    modificaProfiloForm.setNome(persona.getNome());
    modificaProfiloForm.setCognome(persona.getCognome());
    modificaProfiloForm.setEmail(persona.getEmail());
    return "profilo/modificaProfilo";
  }

  @PostMapping
  public String post(@ModelAttribute @Valid ModificaProfiloForm modificaProfiloForm,
                     BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Persona persona = personaAutenticata.getPersona().get();

    String passwordNuova = null;

    if (!modificaProfiloForm.getPasswordAttuale().isEmpty()) {
      if (!checkPasswordService.checkPassword(persona, modificaProfiloForm.getPasswordAttuale())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }

      if (!modificaProfiloForm.getPasswordNuova().isEmpty()) {
        passwordNuova = modificaProfiloForm.getPasswordNuova();
      }
    }

    if (!modificaProfiloService.modificaProfilo(persona.getId(), modificaProfiloForm.getNome(),
        modificaProfiloForm.getCognome(), modificaProfiloForm.getEmail(), passwordNuova)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return "redirect:/auth/areaPersonale";
  }
}
