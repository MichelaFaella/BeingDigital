package it.unisa.darn.service.profilo;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AreaUtenteService {

  @Autowired
  private RispostaRepository rispostaRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  public int getPercentualeCompletamento(Utente utente) {
    long risposteEsatte = rispostaRepository.countByUtenteAndIndiceSelezione(utente, 0);
    long domandeTotali = domandaRepository.countByMetaInfoLivello(utente.getLivello());

    return (int) (risposteEsatte / (double) domandeTotali * 100);
  }
}
