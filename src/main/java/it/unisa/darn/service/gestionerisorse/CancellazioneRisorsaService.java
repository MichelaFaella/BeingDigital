package it.unisa.darn.service.gestionerisorse;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.GiocoRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
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

  public boolean cancellazioneMetaInfo(@NotNull Long id) {
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

  public boolean cancellazioneArgomento(@NotNull Long id) {
    if (!argomentoRepository.existsById(id)) {
      return false;
    }

    argomentoRepository.deleteById(id);
    return true;
  }

  public boolean cancellazioneGioco(@NotNull Long id) {
    if (!giocoRepository.existsById(id)) {
      return false;
    }

    giocoRepository.deleteById(id);
    return true;
  }

  public boolean cancellazioneDomanda(@NotNull Long id) {
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
