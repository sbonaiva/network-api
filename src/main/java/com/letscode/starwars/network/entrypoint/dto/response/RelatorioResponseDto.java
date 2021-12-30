package com.letscode.starwars.network.entrypoint.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class RelatorioResponseDto {

  private Long totalSoldados;
  private Double porcentagemDeTraidores;
  private Double porcentagemDeRebeldes;
  private Integer pontosPerdidosPorTraidores;
  private Map<String, Double> mediaDeRecursosPorRebelde;

}
