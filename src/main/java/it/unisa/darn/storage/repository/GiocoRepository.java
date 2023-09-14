package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.MetaInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiocoRepository extends JpaRepository<Gioco, Long> {

  Optional<Gioco> findByNome(String nome);

  Optional<Gioco> findByMetaInfo(MetaInfo metaInfo);
}
