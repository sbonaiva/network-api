package com.letscode.starwars.network.usecase;

import com.letscode.starwars.network.usecase.domain.TraidorDomain;
import com.letscode.starwars.network.usecase.gateway.ReportarTraidorGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportarTraidorUseCase {

  private final ReportarTraidorGateway reportarTraidorGateway;

  @Transactional
  public void executar(@NonNull final TraidorDomain traidorDomain) {
    reportarTraidorGateway.reportar(traidorDomain);
  }
}
