package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Utente;
import it.unisa.darn.storage.entity.util.Livello;
import java.util.List;

/**
 * Questa interfaccia rappresenta la repository di un utente.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i dati degli
 * utenti presenti nel DB.
 */

public interface UtenteRepository extends PersonaGenericRepository<Utente> {

  List<Utente> findByLivello(Livello livello);

  long countByLivello(Livello livello);
}
