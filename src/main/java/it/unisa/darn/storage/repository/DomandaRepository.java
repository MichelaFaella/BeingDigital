package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.MetaInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomandaRepository extends JpaRepository<Domanda, Long> {

  List<Domanda> findByMetaInfo(MetaInfo metaInfo);
}
