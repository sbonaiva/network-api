package com.letscode.starwars.network.usecase;

import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.gateway.BuscarRebeldeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarRebeldeUseCase {

  private final BuscarRebeldeGateway buscarRebeldeGateway;

  public RebeldeDomain executar(@NonNull final Long idRebelde) {
    return buscarRebeldeGateway.buscar(idRebelde);
  }
}
