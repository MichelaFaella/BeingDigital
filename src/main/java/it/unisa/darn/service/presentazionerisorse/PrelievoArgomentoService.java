package it.unisa.darn.service.presentazionerisorse;

import it.unisa.darn.storage.entity.Argomento;
import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Racconto;
import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.LezioneRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RaccontoRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
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

  public List<Racconto> getAllRaccontiSortedByTitolo() {
    return raccontoRepository.findAll(Sort.by("titolo"));
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
      Map.Entry<MetaInfo, List<Lezione>>> getLezioniCittadinanzaPerMetaInfoSortedByKeywordTitolo() {
    List<MetaInfo> metaInfo =
        metaInfoRepository.findByLivello(Livello.CITTADINANZA_DIGITALE, Sort.by("keyword"));

    return getLezioniPerMetaInfoSortedByTitolo(metaInfo);
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
