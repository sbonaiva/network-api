package com.letscode.starwars.network.entrypoint.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
@NoArgsConstructor
public class RebeldeRequestDto {

  @NotEmpty(message = "Nome é obrigatório")
  private String nome;

  @NotNull(message = "Idade é obrigatória")
  @Positive(message = "Idade deve ser um número positivo")
  private Integer idade;

  @NotEmpty(message = "Gênero é obrigatório")
  private String genero;

  @Valid
  @NotNull(message = "Localização é obrigatório")
  private RebeldeLocalizacaoRequestDto localizacao;

  @Valid
  @NotEmpty(message = "Inventário não pode estar vazio")
  private Set<RebeldeItemRequestDto> inventario;

}
