package it.unisa.darn.service.profilo;

import it.unisa.darn.storage.entity.Admin;
import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.repository.AdminRepository;
import it.unisa.darn.storage.repository.UtenteRepository;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
public class PromozioneUtenteService {

  @Autowired
  private UtenteRepository utenteRepository;

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private CancellazioneAccountService cancellazioneAccountService;

  public boolean promozioneUtente(@NotNull Long id) {
    Optional<Utente> optional = utenteRepository.findById(id);
    if (optional.isEmpty()) {
      return false;
    }

    Utente utente = optional.get();
    cancellazioneAccountService.cancellazioneUtente(id);
    utenteRepository.flush();
    Admin admin = new Admin(utente.getNome(), utente.getCognome(), utente.getEmail(),
        utente.getPassword());
    adminRepository.save(admin);
    return true;
  }
}
