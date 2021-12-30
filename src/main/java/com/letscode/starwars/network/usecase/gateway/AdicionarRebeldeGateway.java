package com.letscode.starwars.network.usecase.gateway;

import com.letscode.starwars.network.usecase.domain.RebeldeDomain;

public interface AdicionarRebeldeGateway {

  RebeldeDomain adicionar(RebeldeDomain rebeldeDomain);

}
