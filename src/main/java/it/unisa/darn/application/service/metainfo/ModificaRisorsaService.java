package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Argomento;
import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.ArgomentoRepository;
import it.unisa.darn.storage.repository.DomandaRepository;
import it.unisa.darn.storage.repository.GiocoRepository;
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

  @Autowired
  private DomandaRepository domandaRepository;

  @Autowired
  private GiocoRepository giocoRepository;

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

  public boolean modificaMetaInfo(Long id, String keyword, Livello livello) {
    Optional<MetaInfo> optional = metaInfoRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    MetaInfo metaInfo = optional.get();

    if (keyword != null) {
      Optional<MetaInfo> optionalAltro = metaInfoRepository.findByKeyword(keyword);
      if (optionalAltro.isPresent() && !optionalAltro.get().equals(metaInfo)) {
        return false;
      }
      metaInfo.setKeyword(keyword);
    }

    if (livello != null) {
      metaInfo.setLivello(livello);
    }

    return true;
  }

  public boolean modificaDomanda(Long id, String testo, String corretta, String sbagliata1,
                                 String sbagliata2, String sbagliata3, Long metaInfoId) {
    Optional<Domanda> optional = domandaRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    Domanda domanda = optional.get();

    if (metaInfoId != null) {
      Optional<MetaInfo> optionalMetaInfo = metaInfoRepository.findById(metaInfoId);
      if (optionalMetaInfo.isEmpty()) {
        return false;
      }

      domanda.setMetaInfo(optionalMetaInfo.get());
    }

    if (testo != null) {
      domanda.setTesto(testo);
    }

    if (corretta != null) {
      domanda.setCorretta(corretta);
    }

    if (sbagliata1 != null) {
      domanda.setSbagliata1(sbagliata1);
    }

    if (sbagliata2 != null) {
      domanda.setSbagliata2(sbagliata2);
    }

    if (sbagliata3 != null) {
      domanda.setSbagliata3(sbagliata3);
    }

    return true;
  }

  public boolean modificaGioco(Long id, String nome, String path, Long metaInfoId) {
    Optional<Gioco> optional = giocoRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    Gioco gioco = optional.get();

    if (nome != null) {
      Optional<Gioco> optionalAltro = giocoRepository.findByNome(nome);
      if (optionalAltro.isPresent() && !optionalAltro.get().equals(gioco)) {
        return false;
      }
      gioco.setNome(nome);
    }

    if (path != null) {
      gioco.setPath(path);
    }

    if (metaInfoId != null) {
      Optional<MetaInfo> optionalMetaInfo = metaInfoRepository.findById(metaInfoId);
      if (optionalMetaInfo.isEmpty()) {
        return false;
      }

      gioco.setMetaInfo(optionalMetaInfo.get());
    }

    return true;
  }
}
