package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.RispostaId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RispostaRepository extends JpaRepository<Risposta, RispostaId> {

  List<Risposta> findByUtente(Utente utente);

  List<Risposta> findByDomanda(Domanda domanda);

  long countByUtenteAndIndiceSelezione(Utente utente, int indiceSelezione);
}
