package it.unisa.darn.application.control.autenticazione.filter;

import it.unisa.darn.application.service.autenticazione.util.PersonaAutenticata;
import it.unisa.darn.storage.entity.Persona;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaFilter implements Filter {

  @Autowired
  private PersonaAutenticata personaAutenticata;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    Optional<Persona> optional = personaAutenticata.getPersona();
    if (optional.isPresent() && isClassValid(optional.get())) {
      filterChain.doFilter(servletRequest, servletResponse);
    }
    ((HttpServletResponse) servletResponse).sendRedirect(
        "/login?risorsa=" + ((HttpServletRequest) servletRequest).getRequestURI());
  }

  protected boolean isClassValid(Persona persona) {
    return true;
  }
}
