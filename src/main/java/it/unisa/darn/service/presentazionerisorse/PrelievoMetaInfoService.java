package it.unisa.darn.service.presentazionerisorse;

import it.unisa.darn.storage.entity.Gioco;
import it.unisa.darn.storage.entity.MetaInfo;
import it.unisa.darn.storage.repository.GiocoRepository;
import it.unisa.darn.storage.repository.MetaInfoRepository;
import jakarta.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
@Validated
public class PrelievoMetaInfoService {

  @Autowired
  private MetaInfoRepository metaInfoRepository;

  @Autowired
  private GiocoRepository giocoRepository;

  public Optional<MetaInfo> getMetaInfo(@NotNull Long id) {
    return metaInfoRepository.findById(id);
  }

  public List<MetaInfo> getAllMetaInfoSortedByLivelloKeyword() {
    return metaInfoRepository.findAll().stream()
        .sorted(Comparator.comparing(MetaInfo::getLivello).thenComparing(MetaInfo::getKeyword))
        .toList();
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
}
