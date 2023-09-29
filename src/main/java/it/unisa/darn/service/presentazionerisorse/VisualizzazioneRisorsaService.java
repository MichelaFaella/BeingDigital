package it.unisa.darn.service.presentazionerisorse;

import it.unisa.darn.storage.entity.Argomento;
import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Racconto;
import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.GiocoRepository;
import it.unisa.darn.storage.repository.LezioneRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RaccontoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VisualizzazioneRisorsaService {

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  @Autowired
  private LezioneRepository lezioneRepository;

  @Autowired
  private RaccontoRepository raccontoRepository;

  @Autowired
  private ArgomentoRepository argomentoRepository;

  @Autowired
  private GiocoRepository giocoRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  public Optional<MetaInfo> getMetaInfo(Long id) {
    return metaInfoRepository.findById(id);
  }

  public Optional<Lezione> getLezione(Long id) {
    return lezioneRepository.findById(id);
  }

  public Optional<Racconto> getRacconto(Long id) {
    return raccontoRepository.findById(id);
  }

  public Optional<Argomento> getArgomento(Long id) {
    return argomentoRepository.findById(id);
  }

  public Optional<Gioco> getGioco(Long id) {
    return giocoRepository.findById(id);
  }

  public Optional<Domanda> getDomanda(Long id) {
    return domandaRepository.findById(id);
  }
}
