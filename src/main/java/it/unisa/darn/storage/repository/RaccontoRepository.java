package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Racconto;

/**
 * Questa interfaccia rappresenta la repository di un racconto.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i dati dei racconti presenti nel DB.
 */

public interface RaccontoRepository extends ArgomentoGenericRepository<Racconto> {
}
