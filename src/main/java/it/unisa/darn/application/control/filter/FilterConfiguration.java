package it.unisa.darn.application.control.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

  @Autowired
  private PersonaFilter personaFilter;

  @Autowired
  private UtenteFilter utenteFilter;

  @Autowired
  private AdminFilter adminFilter;

  @Bean
  public FilterRegistrationBean<PersonaFilter> filterRegistrationBeanPersonaFilter() {
    FilterRegistrationBean<PersonaFilter> registrationBean
        = new FilterRegistrationBean<>();

    registrationBean.setFilter(personaFilter);
    registrationBean.addUrlPatterns("/areaPersonale");

    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<UtenteFilter> filterRegistrationBeanUtenteFilter() {
    FilterRegistrationBean<UtenteFilter> registrationBean
        = new FilterRegistrationBean<>();

    registrationBean.setFilter(utenteFilter);
    registrationBean.addUrlPatterns("");

    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<AdminFilter> filterRegistrationBeanAdminFilter() {
    FilterRegistrationBean<AdminFilter> registrationBean
        = new FilterRegistrationBean<>();

    registrationBean.setFilter(adminFilter);
    registrationBean.addUrlPatterns("");

    return registrationBean;
  }
}
