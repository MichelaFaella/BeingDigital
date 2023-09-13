package it.unisa.darn.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Gioco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nome;

  @Column(nullable = false)
  private String path;

  @OneToOne
  @JoinColumn(nullable = false)
  private MetaInfo metaInfo;

  public Gioco(String nome, String path, MetaInfo metaInfo) {
    this.nome = nome;
    this.path = path;
    this.metaInfo = metaInfo;
  }
}
