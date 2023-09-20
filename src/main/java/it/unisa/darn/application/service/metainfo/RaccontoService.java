package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Racconto;
import it.unisa.darn.storage.repository.RaccontoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RaccontoService {

  @Autowired
  private RaccontoRepository raccontoRepository;

  public Optional<Racconto> getRacconto(Long id) {
    return raccontoRepository.findById(id);
  }
}
