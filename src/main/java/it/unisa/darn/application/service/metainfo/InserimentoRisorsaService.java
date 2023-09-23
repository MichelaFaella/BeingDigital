package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Racconto;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InserimentoRisorsaService {

  @Autowired
  private ArgomentoRepository argomentoRepository;

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  public boolean inserimentoLezione(String titolo, String corpo, byte[] copertina,
                                    Long metaInfoId) {
    Optional<MetaInfo> optional = metaInfoRepository.findById(metaInfoId);
    if (optional.isEmpty()) {
      return false;
    }

    Lezione lezione = new Lezione(titolo, corpo, copertina, optional.get());
    argomentoRepository.save(lezione);
    return true;
  }

  public boolean inserimentoRacconto(String titolo, String corpo, byte[] copertina,
                                     Long metaInfoId) {
    Optional<MetaInfo> optional = metaInfoRepository.findById(metaInfoId);
    if (optional.isEmpty()) {
      return false;
    }

    Racconto racconto = new Racconto(titolo, corpo, copertina, optional.get());
    argomentoRepository.save(racconto);
    return true;
  }

  public boolean inserimentoMetaInfo(String keyword, Livello livello) {
    if (metaInfoRepository.existsByKeyword(keyword)) {
      return false;
    }

    MetaInfo metaInfo = new MetaInfo(keyword, livello);
    metaInfoRepository.save(metaInfo);
    return true;
  }
}
