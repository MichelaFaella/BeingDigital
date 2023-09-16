package it.unisa.darn.application.service;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import it.unisa.darn.storage.repository.PersonaRepository;
import it.unisa.darn.storage.repository.UtenteRepository;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrazioneService {

  @Autowired
  private PersonaRepository personaRepository;

  @Autowired
  private UtenteRepository utenteRepository;

  @Autowired
  private PasswordEncryptor passwordEncryptor;

  public boolean registrazione(String nome, String cognome, String email, String password) {
    if (personaRepository.existsByEmail(email)) {
      return false;
    }

    Utente utente =
        new Utente(nome, cognome, email, passwordEncryptor.encryptPassword(password), Livello.BASE);

    utenteRepository.save(utente);
    return true;
  }
}
