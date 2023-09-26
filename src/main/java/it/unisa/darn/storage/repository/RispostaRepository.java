package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.RispostaId;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RispostaRepository extends JpaRepository<Risposta, RispostaId> {

  List<Risposta> findByUtente(Utente utente);

  List<Risposta> findByUtente(Utente utente, Sort sort);

  List<Risposta> findByDomanda(Domanda domanda);

  long countByUtenteAndIndiceSelezione(Utente utente, int indiceSelezione);

  void deleteByUtente(Utente utente);

  void deleteByDomandaMetaInfo(MetaInfo metaInfo);

  void deleteByDomanda(Domanda domanda);
}
