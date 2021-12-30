package com.letscode.starwars.network.entrypoint.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class RebeldeResponseDto {

  private Long id;
  private String nome;
  private Integer idade;
  private String genero;
  private RebeldeLocalizacaoResponseDto localizacao;
  private Set<RebeldeItemResponseDto> inventario;
  private Set<Long> traicoes;

}
