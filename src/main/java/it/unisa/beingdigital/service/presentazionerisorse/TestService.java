package it.unisa.beingdigital.service.presentazionerisorse;

import it.unisa.beingdigital.service.profilo.DatiUtentiService;
import it.unisa.beingdigital.storage.entity.Domanda;
import it.unisa.beingdigital.storage.entity.Risposta;
import it.unisa.beingdigital.storage.entity.Utente;
import it.unisa.beingdigital.storage.entity.util.Livello;
import it.unisa.beingdigital.storage.repository.DomandaRepository;
import it.unisa.beingdigital.storage.repository.RispostaRepository;
import it.unisa.beingdigital.storage.repository.UtenteRepository;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
public class TestService {

  @Autowired
  private RispostaRepository rispostaRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  @Autowired
  private DatiUtentiService datiUtentiService;

  @Autowired
  private UtenteRepository utenteRepository;

  private void replaceRisposte(@NotNull List<Map.Entry<Long, String>> risposte,
                               @NotNull Utente utente) {
    rispostaRepository.deleteByUtente(utente);
    rispostaRepository.flush();

    List<Risposta> rispostaEntities = new ArrayList<>(risposte.size());

    for (Map.Entry<Long, String> risposta : risposte) {
      Domanda domanda =
          domandaRepository.findById(risposta.getKey()).orElseThrow(IllegalArgumentException::new);

      Risposta rispostaEntity = new Risposta(utente, domanda, null);

      if (domanda.getCorretta().equals(risposta.getValue())) {
        rispostaEntity.setIndiceSelezione(0);
      } else if (domanda.getSbagliata1().equals(risposta.getValue())) {
        rispostaEntity.setIndiceSelezione(1);
      } else if (domanda.getSbagliata2().equals(risposta.getValue())) {
        rispostaEntity.setIndiceSelezione(2);
      } else if (domanda.getSbagliata3().equals(risposta.getValue())) {
        rispostaEntity.setIndiceSelezione(3);
      } else {
        throw new IllegalArgumentException();
      }

      rispostaEntities.add(rispostaEntity);
    }

    rispostaRepository.saveAll(rispostaEntities);
  }

  private boolean aumentaLivello(@NotNull Utente utente) {
    if (datiUtentiService.getPercentualeCompletamento(utente) >= 100) {
      switch (utente.getLivello()) {
        case BASE -> utente.setLivello(Livello.INTERMEDIO);
        case INTERMEDIO -> utente.setLivello(Livello.AVANZATO);
        case AVANZATO -> utente.setLivello(Livello.MASTER);
        default -> throw new IllegalArgumentException();
      }
      rispostaRepository.deleteByUtente(utente);
      utenteRepository.save(utente);
      return true;
    }
    return false;
  }

  public boolean test(@NotNull List<Map.Entry<Long, String>> risposte, @NotNull Utente utente) {
    replaceRisposte(risposte, utente);
    return aumentaLivello(utente);
  }
}
