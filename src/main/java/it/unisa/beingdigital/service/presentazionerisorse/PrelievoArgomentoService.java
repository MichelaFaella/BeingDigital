package it.unisa.beingdigital.service.presentazionerisorse;

import it.unisa.beingdigital.storage.entity.Argomento;
import it.unisa.beingdigital.storage.entity.Domanda;
import it.unisa.beingdigital.storage.entity.Lezione;
import it.unisa.beingdigital.storage.entity.MetaInfo;
import it.unisa.beingdigital.storage.entity.Racconto;
import it.unisa.beingdigital.storage.entity.Risposta;
import it.unisa.beingdigital.storage.entity.Utente;
import it.unisa.beingdigital.storage.entity.util.Livello;
import it.unisa.beingdigital.storage.repository.ArgomentoRepository;
import it.unisa.beingdigital.storage.repository.DomandaRepository;
import it.unisa.beingdigital.storage.repository.LezioneRepository;
import it.unisa.beingdigital.storage.repository.MetaInfoRepository;
import it.unisa.beingdigital.storage.repository.RaccontoRepository;
import it.unisa.beingdigital.storage.repository.RispostaRepository;
import jakarta.validation.constraints.NotNull;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
@Validated
public class PrelievoArgomentoService {

  @Autowired
  private LezioneRepository lezioneRepository;

  @Autowired
  private RaccontoRepository raccontoRepository;

  @Autowired
  private ArgomentoRepository argomentoRepository;

  @Autowired
  private RispostaRepository rispostaRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  @Autowired
  private PrelievoMetaInfoService prelievoMetaInfoService;

  public Optional<Lezione> getLezione(@NotNull Long id) {
    return lezioneRepository.findById(id);
  }

  public Optional<Racconto> getRacconto(@NotNull Long id) {
    return raccontoRepository.findById(id);
  }

  public Optional<Argomento> getArgomento(@NotNull Long id) {
    return argomentoRepository.findById(id);
  }

  public List<Lezione> getAllLezioniSortedByLivelloKeywordTitolo() {
    return lezioneRepository.findAll().stream()
        .sorted(Comparator.comparing((Lezione lezione) -> lezione.getMetaInfo().getLivello())
            .thenComparing(lezione -> lezione.getMetaInfo().getKeyword())
            .thenComparing(Lezione::getTitolo)).toList();
  }

  public List<Racconto> getRaccontiSortedByTitolo(@NotNull Livello livello) {
    if (livello == Livello.MASTER) {
      return raccontoRepository.findAll(Sort.by("titolo")).stream()
          .filter(racconto -> racconto.getMetaInfo().getLivello() != Livello.CITTADINANZA_DIGITALE)
          .toList();
    } else {
      return raccontoRepository.findByMetaInfoLivello(livello, Sort.by("titolo"));
    }
  }

  public List<Racconto> getAllRaccontiSortedByLivelloKeywordTitolo() {
    return raccontoRepository.findAll().stream()
        .sorted(Comparator.comparing((Racconto racconto) -> racconto.getMetaInfo().getLivello())
            .thenComparing(racconto -> racconto.getMetaInfo().getKeyword())
            .thenComparing(Racconto::getTitolo)).toList();
  }

  public List<
      Map.Entry<MetaInfo, List<Lezione>>
      > getLezioniDaStudiarePerMetaInfoSortedByLivelloKeywordTitolo(@NotNull Utente utente) {
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
      metaInfoDaVedere = prelievoMetaInfoService.getAllMetaInfoSortedByLivelloKeyword().stream()
          .filter(metaInfo -> metaInfo.getLivello() != Livello.CITTADINANZA_DIGITALE).toList();
    }

    return getLezioniPerMetaInfoSortedByTitolo(metaInfoDaVedere);
  }

  public List<
      Map.Entry<MetaInfo, List<Lezione>>> getLezioniPerMetaInfoSortedByLivelloKeywordTitolo(
      @NotNull Livello livello) {
    List<MetaInfo> metaInfos;
    if (livello == Livello.MASTER) {
      metaInfos = prelievoMetaInfoService.getAllMetaInfoSortedByLivelloKeyword().stream()
          .filter(metaInfo -> metaInfo.getLivello() != Livello.CITTADINANZA_DIGITALE).toList();
    } else {
      metaInfos = metaInfoRepository.findByLivello(livello, Sort.by("keyword"));
    }

    return getLezioniPerMetaInfoSortedByTitolo(metaInfos);
  }

  private List<Map.Entry<MetaInfo, List<Lezione>>> getLezioniPerMetaInfoSortedByTitolo(
      @NotNull List<MetaInfo> metaInfo) {
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