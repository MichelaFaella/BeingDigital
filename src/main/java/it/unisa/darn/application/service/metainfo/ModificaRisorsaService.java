package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Argomento;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModificaRisorsaService {

  @Autowired
  private ArgomentoRepository argomentoRepository;

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  public boolean modificaArgomento(Long id, String titolo, String corpo, byte[] copertina,
                                   Long metaInfoId) {
    Optional<Argomento> optionalArgomento = argomentoRepository.findById(id);
    if (optionalArgomento.isEmpty()) {
      return false;
    }

    Argomento argomento = optionalArgomento.get();

    if (metaInfoId != null) {
      Optional<MetaInfo> optionalMetaInfo = metaInfoRepository.findById(metaInfoId);
      if (optionalMetaInfo.isEmpty()) {
        return false;
      }

      argomento.setMetaInfo(optionalMetaInfo.get());
    }

    if (titolo != null) {
      argomento.setTitolo(titolo);
    }

    if (corpo != null) {
      argomento.setCorpo(corpo);
    }

    if (copertina != null) {
      argomento.setCopertina(copertina);
    }

    return true;
  }
}
