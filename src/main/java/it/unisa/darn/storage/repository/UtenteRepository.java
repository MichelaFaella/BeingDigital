package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import java.util.List;

public interface UtenteRepository extends PersonaGenericRepository<Utente> {

  List<Utente> findByLivello(Livello livello);
}
