package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.repository.RispostaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RisposteService {

  @Autowired
  private RispostaRepository rispostaRepository;

  public List<Risposta> getRisposteSortedByKeywordTesto(Utente utente) {
    return rispostaRepository.findByUtente(utente,
        Sort.by("domandaMetaInfoKeyword", "domandaTesto"));
  }
}
