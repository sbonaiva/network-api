package com.letscode.starwars.network.usecase;

import com.letscode.starwars.network.usecase.domain.RelatorioDomain;
import com.letscode.starwars.network.usecase.gateway.GerarRelatorioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GerarRelatorioUseCase {

  private final GerarRelatorioGateway gerarRelatorioGateway;

  public RelatorioDomain executar() {
   return gerarRelatorioGateway.gerar();
  }
}
