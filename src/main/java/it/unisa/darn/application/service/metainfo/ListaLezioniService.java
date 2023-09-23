package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.LezioneRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ListaLezioniService {

  @Autowired
  private RispostaRepository rispostaRepository;

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  @Autowired
  private LezioneRepository lezioneRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  public Map<MetaInfo, List<Lezione>> getLezioniDaStudiare(Utente utente) {
    Set<Domanda> domandeEsatte =
        rispostaRepository.findByUtente(utente).stream()
            .filter(risposta -> risposta.getIndiceSelezione() == 0).map(Risposta::getDomanda)
            .collect(Collectors.toSet());

    List<MetaInfo> metaInfoLivello = metaInfoRepository.findByLivello(utente.getLivello());
    List<MetaInfo> metaInfoSbagliate = new ArrayList<>();
    for (MetaInfo metaInfo : metaInfoLivello) {
      Set<Domanda> domandeMetaInfo = new HashSet<>(domandaRepository.findByMetaInfo(metaInfo));
      if (domandeMetaInfo.isEmpty()) {
        metaInfoSbagliate.add(metaInfo);
        continue;
      }
      domandeMetaInfo.removeAll(domandeEsatte);
      if (!domandeMetaInfo.isEmpty()) {
        metaInfoSbagliate.add(metaInfo);
      }
    }

    Map<MetaInfo, List<Lezione>> lezioniPerMetaInfo = new HashMap<>();
    for (MetaInfo metaInfo : metaInfoSbagliate) {
      List<Lezione> lezioni = lezioneRepository.findByMetaInfo(metaInfo);
      if (!lezioni.isEmpty()) {
        lezioniPerMetaInfo.put(metaInfo, lezioni);
      }
    }

    return lezioniPerMetaInfo;
  }
}
