package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.GiocoRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CancellazioneRisorsaService {

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  @Autowired
  private ArgomentoRepository argomentoRepository;

  @Autowired
  private GiocoRepository giocoRepository;


  public boolean cancellazioneMetainfo(Long id) {
    if (metaInfoRepository.existsById(id)) {
      return false;
    }
    metaInfoRepository.deleteById(id);
    return true;
  }

  public boolean cancellazioneArgomento(Long id) {
    if (argomentoRepository.existsById(id)) {
      return false;
    }
    argomentoRepository.deleteById(id);
    return true;
  }

  public boolean cancellazioneGioco(Long id) {
    if (giocoRepository.existsById(id)) {
      return false;
    }
    giocoRepository.deleteById(id);
    return true;
  }
}
