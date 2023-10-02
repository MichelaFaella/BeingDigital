package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Argomento;

/**
 * Questa interfaccia rappresenta la repository di un argomento.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i dati degli argomenti presenti nel DB.
 */

public interface ArgomentoRepository extends ArgomentoGenericRepository<Argomento> {
}
