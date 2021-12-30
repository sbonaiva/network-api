package com.letscode.starwars.network.entrypoint;

import com.letscode.starwars.network.entrypoint.dto.request.NegociacaoRequestDto;
import com.letscode.starwars.network.entrypoint.mapper.NegociacaoUseCaseMapper;
import com.letscode.starwars.network.usecase.NegociarItemUseCase;
import com.letscode.starwars.network.usecase.domain.NegociacaoDomain;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Negociação de itens entre rebeldes")
@ApiResponse(responseCode = "204", description = "Negociação com sucesso")
@ApiResponse(responseCode = "400", description = "Requisição inválida")
@ApiResponse(responseCode = "404", description = "Rebelde não encontrado")
@ApiResponse(responseCode = "412", description = "Erros de negócio")
@ApiResponse(responseCode = "500", description = "Erro inesperado")
@RestController
@RequestMapping("rebeldes")
@RequiredArgsConstructor
public class NegociarItemController {

  private final NegociacaoUseCaseMapper negociacaoUseCaseMapper;
  private final NegociarItemUseCase negociarItemUseCase;

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping("{idRemetente}/negociar/{idDestinatario}")
  public void negociar(@PathVariable("idRemetente") final Long idRemetente,
                       @PathVariable("idDestinatario") final Long idDestinatario,
                       @Valid @RequestBody final NegociacaoRequestDto negociacaoRequestDto) {

    final NegociacaoDomain negociacaoDomain = negociacaoUseCaseMapper.toDomain(negociacaoRequestDto);
    negociarItemUseCase.executar(idRemetente, idDestinatario, negociacaoDomain);
  }
}
