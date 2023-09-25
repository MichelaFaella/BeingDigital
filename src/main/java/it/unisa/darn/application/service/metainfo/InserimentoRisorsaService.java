package it.unisa.darn.application.service.metainfo;

import it.unisa.darn.storage.entity.Domanda;
import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.Lezione;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.entity.Racconto;
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
public class InserimentoRisorsaService {

  @Autowired
  private ArgomentoRepository argomentoRepository;

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  @Autowired
  private DomandaRepository domandaRepository;

  @Autowired
  private GiocoRepository giocoRepository;

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

  public boolean inserimentoDomanda(String testo, String corretta, String sbagliata1,
                                    String sbagliata2, String sbagliata3, Long metaInfoId) {
    Optional<MetaInfo> optional = metaInfoRepository.findById(metaInfoId);
    if (optional.isEmpty()) {
      return false;
    }

    Domanda domanda =
        new Domanda(testo, corretta, sbagliata1, sbagliata2, sbagliata3, optional.get());
    domandaRepository.save(domanda);
    return true;
  }

  public boolean inserimentoGioco(String nome, String path, Long metaInfoId) {
    Optional<MetaInfo> optional = metaInfoRepository.findById(metaInfoId);
    if (optional.isEmpty()) {
      return false;
    }

    if (giocoRepository.existsByNome(nome)) {
      return false;
    }

    Gioco gioco = new Gioco(nome, path, optional.get());
    giocoRepository.save(gioco);
    return true;
  }
}
