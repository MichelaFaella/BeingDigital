package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.service.metainfo.RaccontoService;
import it.unisa.darn.storage.entity.Racconto;
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
@RequestMapping("/racconto")
public class RaccontoController {

  @Autowired
  private RaccontoService raccontoService;

  @GetMapping
  public String get(@RequestParam Long id, Model model) {
    Optional<Racconto> optional = raccontoService.getRacconto(id);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    model.addAttribute("argomento", optional.get());
    return "metainfo/argomento";
  }
}
