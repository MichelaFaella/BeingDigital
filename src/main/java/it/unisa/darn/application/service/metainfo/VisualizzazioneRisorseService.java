package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Racconto;
import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.GiocoRepository;
import it.unisa.darn.storage.repository.LezioneRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RaccontoRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

  @Autowired
  private RispostaRepository rispostaRepository;

  public List<MetaInfo> getAllMetaInfoSortedByLivelloKeyword() {
    return metaInfoRepository.findAll().stream()
        .sorted(Comparator.comparing(MetaInfo::getLivello).thenComparing(MetaInfo::getKeyword))
        .toList();
  }

  public List<Lezione> getAllLezioniSortedByLivelloKeywordTitolo() {
    return lezioneRepository.findAll().stream()
        .sorted(Comparator.comparing((Lezione lezione) -> lezione.getMetaInfo().getLivello())
            .thenComparing(lezione -> lezione.getMetaInfo().getKeyword())
            .thenComparing(Lezione::getTitolo)).toList();
  }

  public List<Racconto> getAllRaccontiSortedByTitolo() {
    return raccontoRepository.findAll(Sort.by("titolo"));
  }

  public List<Racconto> getAllRaccontiSortedByLivelloKeywordTitolo() {
    return raccontoRepository.findAll().stream()
        .sorted(Comparator.comparing((Racconto racconto) -> racconto.getMetaInfo().getLivello())
            .thenComparing(racconto -> racconto.getMetaInfo().getKeyword())
            .thenComparing(Racconto::getTitolo)).toList();
  }

  public List<Gioco> getAllGiochiSortedByLivelloKeyword() {
    return giocoRepository.findAll().stream()
        .sorted(Comparator.comparing((Gioco gioco) -> gioco.getMetaInfo().getLivello())
            .thenComparing(gioco -> gioco.getMetaInfo().getKeyword())).toList();
  }

  public List<Domanda> getAllDomandeSortedByLivelloKeywordTesto() {
    return domandaRepository.findAll().stream()
        .sorted(Comparator.comparing((Domanda domanda) -> domanda.getMetaInfo().getLivello())
            .thenComparing(domanda -> domanda.getMetaInfo().getKeyword())
            .thenComparing(Domanda::getTesto)).toList();
  }

  public List<MetaInfo> getMetaInfoSenzaGioco(Long includeMetaInfoId) {
    Set<MetaInfo> metaInfoConGioco =
        giocoRepository.findAll().stream().map(Gioco::getMetaInfo)
            .filter(metaInfo -> !metaInfo.getId().equals(includeMetaInfoId))
            .collect(Collectors.toSet());

    Set<MetaInfo> metaInfo = new HashSet<>(metaInfoRepository.findAll());
    metaInfo.removeAll(metaInfoConGioco);

    return metaInfo.stream()
        .sorted(Comparator.comparing(MetaInfo::getLivello).thenComparing(MetaInfo::getKeyword))
        .toList();
  }

  public List<Map.Entry<MetaInfo, List<Lezione>>> getLezioniDaStudiare(Utente utente) {
    List<MetaInfo> metaInfoDaVedere;

    if (utente.getLivello() != Livello.MASTER) {
      Set<Domanda> domandeEsatte =
          rispostaRepository.findByUtente(utente).stream()
              .filter(risposta -> risposta.getIndiceSelezione() == 0).map(Risposta::getDomanda)
              .collect(Collectors.toSet());

      List<MetaInfo> metaInfoLivello =
          metaInfoRepository.findByLivello(utente.getLivello(), Sort.by("keyword"));
      metaInfoDaVedere = new ArrayList<>();
      for (MetaInfo metaInfo : metaInfoLivello) {
        Set<Domanda> domandeMetaInfo = new HashSet<>(domandaRepository.findByMetaInfo(metaInfo));
        if (domandeMetaInfo.isEmpty()) {
          metaInfoDaVedere.add(metaInfo);
          continue;
        }
        domandeMetaInfo.removeAll(domandeEsatte);
        if (!domandeMetaInfo.isEmpty()) {
          metaInfoDaVedere.add(metaInfo);
        }
      }
    } else {
      metaInfoDaVedere = getAllMetaInfoSortedByLivelloKeyword().stream()
          .filter(metaInfo -> metaInfo.getLivello() != Livello.CITTADINANZA_DIGITALE).toList();
    }

    return getLezioniPerMetaInfo(metaInfoDaVedere);
  }

  public List<Map.Entry<MetaInfo, List<Lezione>>> getLezioniCittadinanzaDigitale() {
    List<MetaInfo> metaInfo =
        metaInfoRepository.findByLivello(Livello.CITTADINANZA_DIGITALE, Sort.by("keyword"));

    return getLezioniPerMetaInfo(metaInfo);
  }

  private List<Map.Entry<MetaInfo, List<Lezione>>> getLezioniPerMetaInfo(List<MetaInfo> metaInfo) {
    List<Map.Entry<MetaInfo, List<Lezione>>> lezioniPerMetaInfo = new ArrayList<>();
    for (MetaInfo metaInfo1 : metaInfo) {
      List<Lezione> lezioni = lezioneRepository.findByMetaInfo(metaInfo1, Sort.by("titolo"));
      if (!lezioni.isEmpty()) {
        lezioniPerMetaInfo.add(new AbstractMap.SimpleEntry<>(metaInfo1, lezioni));
      }
    }

    return lezioniPerMetaInfo;
  }
}
