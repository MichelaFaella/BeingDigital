package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Risposta;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.repository.LezioneRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import it.unisa.darn.storage.repository.RispostaRepository;
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

  public Map<MetaInfo, List<Lezione>> getLezioniDaStudiare(Utente utente) {
    Set<MetaInfo> metaInfoEsatte =
        rispostaRepository.findByUtente(utente).stream()
            .filter(risposta -> risposta.getIndiceSelezione() == 0).map(Risposta::getDomanda)
            .map(Domanda::getMetaInfo).collect(Collectors.toSet());

    Set<MetaInfo> metaInfoTotali =
        new HashSet<>(metaInfoRepository.findByLivello(utente.getLivello()));
    metaInfoTotali.removeAll(metaInfoEsatte);

    Map<MetaInfo, List<Lezione>> lezioniPerMetaInfo = new HashMap<>();
    for (MetaInfo metaInfo : metaInfoTotali) {
      lezioniPerMetaInfo.put(metaInfo, lezioneRepository.findByMetaInfo(metaInfo));
    }

    return lezioniPerMetaInfo;
  }
}
