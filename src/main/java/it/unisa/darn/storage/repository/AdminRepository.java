package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Admin;

/**
 * Questa interfaccia rappresenta la repository di un admin.
 * Viene implementata autonomamente da Spring in modo da consentire l'accesso
 * a i dati degli admin presenti nel DB.
 */

public interface AdminRepository extends PersonaGenericRepository<Admin> {
}
