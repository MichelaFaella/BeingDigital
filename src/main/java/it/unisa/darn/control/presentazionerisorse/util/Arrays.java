package it.unisa.darn.control.presentazionerisorse.util;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Arrays {

  public <T> List<T> shuffle(T... args) {
    List<T> result = java.util.Arrays.asList(args);
    Collections.shuffle(result);
    return result;
  }
}
