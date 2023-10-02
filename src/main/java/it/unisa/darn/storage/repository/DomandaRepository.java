package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.util.Livello;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Questa interfaccia rappresenta la repository di una domanda.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i dati delle domande presenti nel DB.
 */

public interface DomandaRepository extends JpaRepository<Domanda, Long> {

  List<Domanda> findByMetaInfo(MetaInfo metaInfo);

  long countByMetaInfoLivello(Livello livello);

  List<Domanda> findByMetaInfoLivello(Livello livello);

  void deleteByMetaInfo(MetaInfo metaInfo);
}
