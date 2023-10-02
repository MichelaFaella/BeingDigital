package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Lezione;

/**
 * Questa interfaccia rappresenta la repository di una lezione.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i dati
 * delle lezioni presenti nel DB.
 */

public interface LezioneRepository extends ArgomentoGenericRepository<Lezione> {
}
