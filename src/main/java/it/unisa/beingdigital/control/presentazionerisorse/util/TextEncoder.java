package it.unisa.beingdigital.control.presentazionerisorse.util;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TextEncoder {

  public String htmlEncode(String string) {
    return string.lines().collect(Collectors.joining("<br>"));
  }
}
