package it.unisa.darn.application.service.profilo;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.repository.RispostaRepository;
import it.unisa.darn.storage.repository.UtenteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CancellazioneUtenteService {

  @Autowired
  private UtenteRepository utenteRepository;

  @Autowired
  private RispostaRepository rispostaRepository;

  public boolean cancellazioneUtente(Long id) {
    Optional<Utente> optional = utenteRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    Utente utente = optional.get();
    rispostaRepository.deleteByUtente(utente);
    utenteRepository.delete(utente);
    return true;
  }
}
