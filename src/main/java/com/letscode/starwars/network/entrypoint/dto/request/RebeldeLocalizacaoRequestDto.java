package com.letscode.starwars.network.entrypoint.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RebeldeLocalizacaoRequestDto {

  @NotEmpty(message = "Galáxia é obrigatório")
  private String galaxia;

  @NotNull(message = "Latitude é obrigatória")
  private Double latitude;

  @NotNull(message = "Longitude é obrigatória")
  private Double longitude;

}
