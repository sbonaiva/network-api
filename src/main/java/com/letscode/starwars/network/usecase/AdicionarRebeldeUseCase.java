package com.letscode.starwars.network.usecase;

import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.gateway.AdicionarRebeldeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdicionarRebeldeUseCase {

  private final AdicionarRebeldeGateway adicionarRebeldeGateway;

  @Transactional
  public RebeldeDomain executar(@NonNull final RebeldeDomain rebeldeDomain) {
    return adicionarRebeldeGateway.adicionar(rebeldeDomain);
  }
}
