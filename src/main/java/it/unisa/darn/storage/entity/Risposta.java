package it.unisa.darn.storage.entity;

import it.unisa.darn.storage.entity.util.RispostaId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@IdClass(RispostaId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Risposta {

  @Id
  @ManyToOne
  private Utente utente;

  @Id
  @ManyToOne
  private Domanda domanda;

  @Column(nullable = false, columnDefinition = "tinyint")
  private Integer indiceSelezione;
}
