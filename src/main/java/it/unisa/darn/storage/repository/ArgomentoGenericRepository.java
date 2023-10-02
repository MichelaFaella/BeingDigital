package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Argomento;
import it.unisa.darn.storage.entity.MetaInfo;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Questa interfaccia rappresenta la repository di un argomento di tipo generico.
 */

@NoRepositoryBean
public interface ArgomentoGenericRepository<T extends Argomento> extends JpaRepository<T, Long> {

  List<T> findByMetaInfo(MetaInfo metaInfo, Sort sort);

  void deleteByMetaInfo(MetaInfo metaInfo);
}
