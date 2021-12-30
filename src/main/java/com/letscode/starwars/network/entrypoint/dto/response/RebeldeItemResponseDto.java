package com.letscode.starwars.network.entrypoint.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RebeldeItemResponseDto {

  private String item;
  private Integer quantidade;

}
