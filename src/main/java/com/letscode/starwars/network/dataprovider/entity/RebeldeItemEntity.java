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
@Table(name = "TBL_REBELDE_INVENTARIO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "item")
public class RebeldeItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceRebeldeInventario")
  @SequenceGenerator(name = "sequenceRebeldeInventario", sequenceName = "SEQ_REBELDE_INVENTARIO")
  @Column(name = "ID_REBELDE_ITEM", nullable = false)
  private Long id;

  @Column(name = "ITEM", nullable = false)
  private String item;

  @Column(name = "QUANTIDADE", nullable = false)
  private Integer quantidade;

}
