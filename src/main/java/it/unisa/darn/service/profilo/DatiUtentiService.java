package it.unisa.darn.service.profilo;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
import it.unisa.darn.storage.repository.UtenteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DatiUtentiService {

  @Autowired
  private UtenteRepository utenteRepository;

  @Autowired
  private RispostaRepository rispostaRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  public List<Utente> getAllUtenti() {
    return utenteRepository.findAll();
  }

  public int getPercentualePerLivello(Livello livello) {
    long totaleUtenti = utenteRepository.count();
    long utentiLivello = utenteRepository.countByLivello(livello);

    return (int) (utentiLivello / (double) totaleUtenti * 100);
  }

  public int getPercentualeCompletamento(Utente utente) {
    long risposteEsatte = rispostaRepository.countByUtenteAndIndiceSelezione(utente, 0);
    long domandeTotali = domandaRepository.countByMetaInfoLivello(utente.getLivello());

    return (int) (risposteEsatte / (double) domandeTotali * 100);
  }
}
