package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Racconto;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.GiocoRepository;
import it.unisa.darn.storage.repository.LezioneRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RaccontoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RisorseService {

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  @Autowired
  private LezioneRepository lezioneRepository;

  @Autowired
  private RaccontoRepository raccontoRepository;

  @Autowired
  private GiocoRepository giocoRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  public List<MetaInfo> getAllMetaInfo() {
    return metaInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "livello"));
  }

  public List<Lezione> getAllLezioni() {
    return lezioneRepository.findAll(Sort.by(Sort.Direction.ASC, "titolo"));
  }

  public List<Racconto> getAllRacconti() {
    return raccontoRepository.findAll(Sort.by(Sort.Direction.ASC, "titolo"));
  }

  public List<Gioco> getAllGiochi() {
    return giocoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
  }

  public List<Domanda> getAllDomande() {
    return domandaRepository.findAll(Sort.by(Sort.Direction.ASC, "metaInfo"));
  }
}
