package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.util.Livello;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaInfoRepository extends JpaRepository<MetaInfo, Long> {

  Optional<MetaInfo> findByKeyword(String keyword);

  List<MetaInfo> findByLivello(Livello livello, Sort sort);

  boolean existsByKeyword(String keyword);
}
