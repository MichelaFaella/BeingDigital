package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.presentazionerisorse.PrelievoArgomentoService;
import it.unisa.darn.storage.entity.Lezione;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/auth/lezione")
public class LezioneController {

  @Autowired
  private PrelievoArgomentoService prelievoArgomentoService;

  @GetMapping
  public String get(@RequestParam Long id, Model model) {
    Optional<Lezione> optional = prelievoArgomentoService.getLezione(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    model.addAttribute("argomento", optional.get());
    return "presentazionerisorse/argomento";
  }
}
