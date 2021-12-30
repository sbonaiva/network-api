package com.letscode.starwars.network.entrypoint.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
public class NegociacaoRequestDto {

  @NotEmpty(message = "Oferta não pode estar vazia")
  private Set<NegociacaoItemRequestDto> oferta;

  @NotEmpty(message = "Procura não pode estar vazia")
  private Set<NegociacaoItemRequestDto> procura;

}
