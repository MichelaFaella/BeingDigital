package it.unisa.darn.service.presentazionerisorse;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.DomandaRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PrelievoDomandaService {

  @Autowired
  private DomandaRepository domandaRepository;

  public Optional<Domanda> getDomanda(Long id) {
    return domandaRepository.findById(id);
  }

  public List<Domanda> getAllDomandeSortedByLivelloKeywordTesto() {
    return domandaRepository.findAll().stream()
        .sorted(Comparator.comparing((Domanda domanda) -> domanda.getMetaInfo().getLivello())
            .thenComparing(domanda -> domanda.getMetaInfo().getKeyword())
            .thenComparing(Domanda::getTesto)).toList();
  }

  public List<Domanda> getDomandeRandom(Livello livello) {
    List<Domanda> domande = new ArrayList<>(domandaRepository.findByMetaInfoLivello(livello));
    Collections.shuffle(domande);
    return domande;
  }
}
