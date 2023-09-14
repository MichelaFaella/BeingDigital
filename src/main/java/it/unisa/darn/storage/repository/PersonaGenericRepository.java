package it.unisa.darn.storage.repository;

import it.unisa.darn.storage.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonaGenericRepository<T extends Persona> extends JpaRepository<T, Long> {

  Optional<T> findByEmail(String email);
}
