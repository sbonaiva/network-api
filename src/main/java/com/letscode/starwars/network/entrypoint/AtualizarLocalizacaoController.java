package com.letscode.starwars.network.entrypoint;

import com.letscode.starwars.network.entrypoint.dto.request.RebeldeLocalizacaoRequestDto;
import com.letscode.starwars.network.entrypoint.mapper.RebeldeUseCaseMapper;
import com.letscode.starwars.network.usecase.AtualizarLocalizacaoUseCase;
import com.letscode.starwars.network.usecase.domain.RebeldeLocalizacaoDomain;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Atualizar localização de rebeldes")
@ApiResponse(responseCode = "204", description = "Localização atualizada")
@ApiResponse(responseCode = "400", description = "Requisição inválida")
@ApiResponse(responseCode = "404", description = "Rebelde não encontrado")
@ApiResponse(responseCode = "500", description = "Erro inesperado")
@RestController
@RequestMapping("rebeldes")
@RequiredArgsConstructor
public class AtualizarLocalizacaoController {

  private final RebeldeUseCaseMapper rebeldeUseCaseMapper;
  private final AtualizarLocalizacaoUseCase atualizarLocalizacaoUseCase;

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PatchMapping("{idRebelde}/localizacao")
  public void atualizar(@PathVariable("idRebelde") final Long idRebelde,
                        @Valid @RequestBody final RebeldeLocalizacaoRequestDto rebeldeLocalizacaoRequestDto) {

    final RebeldeLocalizacaoDomain rebeldeLocalizacaoDomain = rebeldeUseCaseMapper.toDomain(rebeldeLocalizacaoRequestDto);
    atualizarLocalizacaoUseCase.executar(idRebelde, rebeldeLocalizacaoDomain);
  }
}
