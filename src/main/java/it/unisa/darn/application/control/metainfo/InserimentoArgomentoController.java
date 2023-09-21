package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.control.metainfo.form.ArgomentoForm;
import it.unisa.darn.application.service.metainfo.InserimentoArgomentoService;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/inserimentoArgomento")
public class InserimentoArgomentoController {

  @Autowired
  private InserimentoArgomentoService inserimentoArgomentoService;

  @PostMapping
  public String post(@RequestParam String tipo, @ModelAttribute @Valid ArgomentoForm argomentoForm,
                     BindingResult bindingResult) throws IOException {
    if (bindingResult.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (argomentoForm.getCopertina() == null || argomentoForm.getCopertina().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    boolean result = switch (tipo) {
      case "lezione" -> inserimentoArgomentoService.inserimentoLezione(argomentoForm.getTitolo(),
          argomentoForm.getCorpo(), argomentoForm.getCopertina().getBytes(),
          argomentoForm.getMetaInfoId());
      case "racconto" -> inserimentoArgomentoService.inserimentoRacconto(argomentoForm.getTitolo(),
          argomentoForm.getCorpo(), argomentoForm.getCopertina().getBytes(),
          argomentoForm.getMetaInfoId());
      default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    };

    if (!result) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "redirect:/admin/risorse";
  }
}
