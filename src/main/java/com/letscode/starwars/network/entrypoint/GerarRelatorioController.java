package com.letscode.starwars.network.entrypoint;

import com.letscode.starwars.network.entrypoint.dto.response.RelatorioResponseDto;
import com.letscode.starwars.network.entrypoint.mapper.RelatorioUseCaseMapper;
import com.letscode.starwars.network.usecase.GerarRelatorioUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Gerar relatório de estatísticas da resistência")
@ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso")
@ApiResponse(responseCode = "500", description = "Erro inesperado")
@RestController
@RequestMapping("rebeldes")
@RequiredArgsConstructor
public class GerarRelatorioController {

  private final RelatorioUseCaseMapper relatorioUseCaseMapper;
  private final GerarRelatorioUseCase gerarRelatorioUseCase;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("relatorio")
  public RelatorioResponseDto gerar() {
    return relatorioUseCaseMapper.toDto(gerarRelatorioUseCase.executar());
  }
}
