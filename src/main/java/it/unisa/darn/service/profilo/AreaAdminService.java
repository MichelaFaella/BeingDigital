package it.unisa.darn.service.profilo;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.UtenteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AreaAdminService {

  @Autowired
  private UtenteRepository utenteRepository;

  public List<Utente> getAllUtenti() {
    return utenteRepository.findAll();
  }

  public int getPercentualePerLivello(Livello livello) {
    long totaleUtenti = utenteRepository.count();
    long utentiLivello = utenteRepository.countByLivello(livello);

    return (int) (utentiLivello / (double) totaleUtenti * 100);
  }
}
