package it.unisa.darn.service.presentazionerisorse;

import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.GiocoRepository;
import jakarta.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
@Validated
public class PrelievoGiocoService {

  @Autowired
  private GiocoRepository giocoRepository;

  public Optional<Gioco> getGioco(@NotNull Long id) {
    return giocoRepository.findById(id);
  }

  public List<Gioco> getAllGiochiSortedByLivelloKeyword() {
    return giocoRepository.findAll().stream()
        .sorted(Comparator.comparing((Gioco gioco) -> gioco.getMetaInfo().getLivello())
            .thenComparing(gioco -> gioco.getMetaInfo().getKeyword())).toList();
  }

  public List<Gioco> getGiochiSortedByNome(@NotNull Livello livello) {
    if (livello == Livello.MASTER) {
      return giocoRepository.findAll(Sort.by("nome")).stream()
          .filter(gioco -> gioco.getMetaInfo().getLivello() != Livello.CITTADINANZA_DIGITALE)
          .toList();
    } else {
      return giocoRepository.findByMetaInfoLivello(livello, Sort.by("nome"));
    }
  }
}
