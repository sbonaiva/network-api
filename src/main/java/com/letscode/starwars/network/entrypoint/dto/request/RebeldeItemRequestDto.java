package com.letscode.starwars.network.entrypoint.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.letscode.starwars.network.entrypoint.annotation.Item;
import com.letscode.starwars.network.entrypoint.deserializer.UpperCaseDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class RebeldeItemRequestDto {

  @JsonDeserialize(using = UpperCaseDeserializer.class)
  @Item(message = "Item inválido")
  @NotEmpty(message = "Item é obrigatório")
  private String item;

  @NotNull(message = "Quantidade é obrigatória")
  @PositiveOrZero(message = "Quantidade deve ser maior ou igual a zero")
  private Integer quantidade;

}
