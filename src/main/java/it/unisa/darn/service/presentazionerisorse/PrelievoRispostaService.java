package it.unisa.darn.service.presentazionerisorse;

import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.repository.RispostaRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
@Validated
public class PrelievoRispostaService {

  @Autowired
  private RispostaRepository rispostaRepository;

  public List<Risposta> getRisposteSortedByKeywordTesto(@NotNull Utente utente) {
    return rispostaRepository.findByUtente(utente,
        Sort.by("domandaMetaInfoKeyword", "domandaTesto"));
  }
}
