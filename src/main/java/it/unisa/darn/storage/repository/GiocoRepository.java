package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.MetaInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Questa interfaccia rappresenta la repository di un gioco.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i
 * dati dei giochi presenti nel DB.
 */

public interface GiocoRepository extends JpaRepository<Gioco, Long> {

  Optional<Gioco> findByNome(String nome);

  Optional<Gioco> findByMetaInfo(MetaInfo metaInfo);

  void deleteByMetaInfo(MetaInfo metaInfo);

  boolean existsByNome(String nome);

  boolean existsByMetaInfo(MetaInfo metaInfo);
}
