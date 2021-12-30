package com.letscode.starwars.network.dataprovider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBL_REBELDE")
@Data
@NoArgsConstructor
public class RebeldeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceRebelde")
  @SequenceGenerator(name = "sequenceRebelde", sequenceName = "SEQ_REBELDE")
  @Column(name = "ID_REBELDE", nullable = false)
  private Long id;

  @Column(name = "NOME", nullable = false)
  private String nome;

  @Column(name = "IDADE", nullable = false)
  private Integer idade;

  @Column(name = "GENERO", nullable = false)
  private String genero;

  @Column(name = "GALAXIA", nullable = false)
  private String galaxia;

  @Column(name = "LATITUDE", nullable = false)
  private Double latitude;

  @Column(name = "LONGITUDE", nullable = false)
  private Double longitude;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "ID_REBELDE", nullable = false)
  private Set<RebeldeItemEntity> inventario = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "ID_REBELDE_REPORTADO", nullable = false)
  private Set<RebeldeTraicaoEntity> traicoes = new HashSet<>();

  public void adicionarTraicao(final Long idReporter) {
    final RebeldeTraicaoEntity rebeldeTraicaoEntity = new RebeldeTraicaoEntity();
    rebeldeTraicaoEntity.setIdRebeldeReporter(idReporter);
    this.traicoes.add(rebeldeTraicaoEntity);
  }
}
