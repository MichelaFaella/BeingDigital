package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.repository.LezioneRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LezioneService {

  @Autowired
  private LezioneRepository lezioneRepository;

  public Optional<Lezione> getLezione(Long id) {
    return lezioneRepository.findById(id);
  }
}
