package com.letscode.starwars.network.usecase.gateway;

import com.letscode.starwars.network.usecase.domain.RebeldeDomain;

public interface BuscarRebeldeGateway {

  RebeldeDomain buscar(Long idRebelde);

}
