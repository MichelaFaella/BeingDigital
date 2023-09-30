package it.unisa.darn.service.presentazionerisorse;

import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.repository.GiocoRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PrelievoGiocoService {

  @Autowired
  private GiocoRepository giocoRepository;

  public Optional<Gioco> getGioco(Long id) {
    return giocoRepository.findById(id);
  }

  public List<Gioco> getAllGiochiSortedByLivelloKeyword() {
    return giocoRepository.findAll().stream()
        .sorted(Comparator.comparing((Gioco gioco) -> gioco.getMetaInfo().getLivello())
            .thenComparing(gioco -> gioco.getMetaInfo().getKeyword())).toList();
  }
}
