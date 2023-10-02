package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Persona;

/**
 * Questa interfaccia rappresenta la repository di una persona.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso a i dati delle persone
 * presenti nel DB.
 */

public interface PersonaRepository extends PersonaGenericRepository<Persona> {
}
