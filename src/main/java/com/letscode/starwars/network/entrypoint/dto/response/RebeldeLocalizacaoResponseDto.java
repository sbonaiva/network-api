package com.letscode.starwars.network.entrypoint.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RebeldeLocalizacaoResponseDto {

  private String galaxia;
  private Double latitude;
  private Double longitude;

}
