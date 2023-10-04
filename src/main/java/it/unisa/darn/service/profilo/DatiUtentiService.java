package it.unisa.darn.service.profilo;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
import it.unisa.darn.storage.repository.UtenteRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Questa classe rappresenta il service per il calcolo di percentuali da mostrare nell'area
 * personale della persona.
 */

@Service
@Transactional(readOnly = true)
@Validated
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

  /**
   * Implementa la funzionalità di calcolo della percentuale di utenti presenti per livello.
   * Si assume che la corretta formulazione del livello sia stata controllata prima
   * di effettuare la chiamata.
   *
   * @param livello Il livello per cui calcolare la percentuale.
   * @return intero rappresentante la percentuale calcolata.
   * @throws jakarta.validation.ConstraintViolationException se il livello risulta null.
   */
  public int getPercentualeUtenti(@NotNull Livello livello) {
    long totaleUtenti = utenteRepository.count();
    long utentiLivello = utenteRepository.countByLivello(livello);

    return (int) (utentiLivello / (double) totaleUtenti * 100);
  }

  /**
   * Implementa la funzionalità di calcolo della percentuale di test superato dell'utente.
   * Si assume che la corretta formulazione dell'utente sia stata controllata prima
   * di effettuare la chiamata.
   *
   * @param utente L'utente per cui calcolare la percentuale.
   * @return intero rappresentante la percentuale calcolata.
   * @throws jakarta.validation.ConstraintViolationException se l'utente risulta null.
   */
  public int getPercentualeCompletamento(@NotNull Utente utente) {
    long risposteEsatte = rispostaRepository.countByUtenteAndIndiceSelezione(utente, 0);
    long domandeTotali = domandaRepository.countByMetaInfoLivello(utente.getLivello());

    return (int) (risposteEsatte / (double) domandeTotali * 100);
  }
}
