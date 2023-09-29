package it.unisa.darn.service.gestionerisorse;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.GiocoRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
import java.util.Optional;
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

  @Autowired
  private DomandaRepository domandaRepository;

  @Autowired
  private RispostaRepository rispostaRepository;

  public boolean cancellazioneMetaInfo(Long id) {
    Optional<MetaInfo> optional = metaInfoRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    MetaInfo metaInfo = optional.get();
    giocoRepository.deleteByMetaInfo(metaInfo);
    argomentoRepository.deleteByMetaInfo(metaInfo);
    domandaRepository.deleteByMetaInfo(metaInfo);
    rispostaRepository.deleteByDomandaMetaInfo(metaInfo);
    metaInfoRepository.delete(metaInfo);
    return true;
  }

  public boolean cancellazioneArgomento(Long id) {
    if (!argomentoRepository.existsById(id)) {
      return false;
    }

    argomentoRepository.deleteById(id);
    return true;
  }

  public boolean cancellazioneGioco(Long id) {
    if (!giocoRepository.existsById(id)) {
      return false;
    }

    giocoRepository.deleteById(id);
    return true;
  }

  public boolean cancellazioneDomanda(Long id) {
    Optional<Domanda> optional = domandaRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    Domanda domanda = optional.get();
    rispostaRepository.deleteByDomanda(domanda);
    domandaRepository.delete(domanda);
    return true;
  }
}
