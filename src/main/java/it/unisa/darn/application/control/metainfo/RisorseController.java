package it.unisa.darn.application.control.metainfo;

import it.unisa.darn.application.service.metainfo.RisorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/risorse")
public class RisorseController {

  @Autowired
  private RisorseService risorseService;

  @GetMapping
  public String get(Model model) {
    model.addAttribute("lezioni", risorseService.getAllLezioni());
    model.addAttribute("racconti", risorseService.getAllRacconti());
    model.addAttribute("metainfo", risorseService.getAllMetaInfo());
    model.addAttribute("giochi", risorseService.getAllGiochi());
    return "metainfo/risorse";
  }
}

