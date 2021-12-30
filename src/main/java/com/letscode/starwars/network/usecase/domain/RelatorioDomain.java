package com.letscode.starwars.network.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class RelatorioDomain {

  private Long totalSoldados;
  private Double porcentagemDeTraidores;
  private Double porcentagemDeRebeldes;
  private Integer pontosPerdidosPorTraidores;
  private Map<String, Double> mediaDeRecursosPorRebelde;

}
