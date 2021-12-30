package com.letscode.starwars.network.dataprovider.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REBELDE_TRAICAO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "idRebeldeReporter")
public class RebeldeTraicaoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceRebeldeTraicao")
  @SequenceGenerator(name = "sequenceRebeldeTraicao", sequenceName = "SEQ_REBELDE_TRAICAO")
  @Column(name = "ID_REBELDE_TRAICAO", nullable = false)
  private Long id;

  @Column(name = "ID_REBELDE_REPORTER", nullable = false)
  private Long idRebeldeReporter;

}
