package com.letscode.starwars.network.entrypoint;


import com.letscode.starwars.network.entrypoint.dto.request.RebeldeRequestDto;
import com.letscode.starwars.network.entrypoint.dto.response.RebeldeResponseDto;
import com.letscode.starwars.network.entrypoint.mapper.RebeldeUseCaseMapper;
import com.letscode.starwars.network.usecase.AdicionarRebeldeUseCase;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Cadastro de rebeldes")
@ApiResponse(responseCode = "201", description = "Rebelde cadastrado")
@ApiResponse(responseCode = "400", description = "Requisição inválida")
@ApiResponse(responseCode = "500", description = "Erro inesperado")
@RestController
@RequestMapping("rebeldes")
@RequiredArgsConstructor
public class AdicionarRebeldeController {

  private final RebeldeUseCaseMapper rebeldeUseCaseMapper;
  private final AdicionarRebeldeUseCase adicionarRebeldeUseCase;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public RebeldeResponseDto adicionar(@Valid @RequestBody final RebeldeRequestDto rebeldeRequestDto) {

    final RebeldeDomain adicionarRebelde = rebeldeUseCaseMapper.toDomain(rebeldeRequestDto);
    final RebeldeDomain rebeldeAdicionado = adicionarRebeldeUseCase.executar(adicionarRebelde);
    return rebeldeUseCaseMapper.toDto(rebeldeAdicionado);
  }
}
