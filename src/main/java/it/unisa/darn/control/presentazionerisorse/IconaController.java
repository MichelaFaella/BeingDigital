package it.unisa.darn.control.presentazionerisorse;

import it.unisa.darn.service.presentazionerisorse.PrelievoMetaInfoService;
import it.unisa.darn.storage.entity.MetaInfo;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/icona")
public class IconaController {

  @Autowired
  private PrelievoMetaInfoService prelievoMetaInfoService;

  @GetMapping
  public void get(@RequestParam Long idMetaInfo, HttpServletResponse response) throws IOException {
    Optional<MetaInfo> optional = prelievoMetaInfoService.getMetaInfo(idMetaInfo);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    response.setContentType("image/png");
    response.getOutputStream().write(optional.get().getIcona());
  }
}
