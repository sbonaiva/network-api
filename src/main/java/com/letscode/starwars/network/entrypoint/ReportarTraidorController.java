package com.letscode.starwars.network.entrypoint;

import com.letscode.starwars.network.entrypoint.mapper.RebeldeUseCaseMapper;
import com.letscode.starwars.network.usecase.ReportarTraidorUseCase;
import com.letscode.starwars.network.usecase.domain.TraidorDomain;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Reportar rebelde como traidor")
@ApiResponse(responseCode = "204", description = "Rebelde reportado")
@ApiResponse(responseCode = "400", description = "Requisição inválida")
@ApiResponse(responseCode = "404", description = "Rebelde não encontrado")
@ApiResponse(responseCode = "500", description = "Erro inesperado")
@RestController
@RequestMapping("rebeldes")
@RequiredArgsConstructor
public class ReportarTraidorController {

  private final RebeldeUseCaseMapper rebeldeUseCaseMapper;
  private final ReportarTraidorUseCase reportarTraidorUseCase;

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping("{idReporter}/reportar/{idReportado}")
  public void reportar(@PathVariable("idReporter") final Long idReporter,
                       @PathVariable("idReportado") final Long idReportado) {

    final TraidorDomain traidorDomain = rebeldeUseCaseMapper.toDomain(idReporter, idReportado);
    reportarTraidorUseCase.executar(traidorDomain);
  }
}
