package it.unisa.darn.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class Argomento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String titolo;

  @Column(nullable = false, length = 65535)
  private String corpo;

  @ManyToOne
  @JoinColumn(nullable = false)
  private MetaInfo metaInfo;

  protected Argomento(String titolo, String corpo, MetaInfo metaInfo) {
    this.titolo = titolo;
    this.corpo = corpo;
    this.metaInfo = metaInfo;
  }
}
