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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VisualizzazioneRisorseService {

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
    return metaInfoRepository.findAll().stream().sorted(Comparator.comparing(MetaInfo::getLivello))
        .toList();
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

  public List<MetaInfo> getMetaInfoSenzaGioco(Long includeMetaInfoId) {
    Set<MetaInfo> metaInfoConGioco =
        giocoRepository.findAll().stream().map(Gioco::getMetaInfo)
            .filter(metaInfo -> !metaInfo.getId().equals(includeMetaInfoId))
            .collect(Collectors.toSet());

    Set<MetaInfo> metaInfo = new HashSet<>(metaInfoRepository.findAll());
    metaInfo.removeAll(metaInfoConGioco);

    return metaInfo.stream().sorted(Comparator.comparing(MetaInfo::getLivello)).toList();
  }
}
