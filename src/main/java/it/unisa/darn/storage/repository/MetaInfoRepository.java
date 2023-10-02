package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.util.Livello;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Questa interfaccia rappresenta la repository di un meta-info.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i dati
 * delle meta-info presenti nel DB.
 */

public interface MetaInfoRepository extends JpaRepository<MetaInfo, Long> {

  Optional<MetaInfo> findByKeyword(String keyword);

  List<MetaInfo> findByLivello(Livello livello, Sort sort);

  boolean existsByKeyword(String keyword);
}
