package com.letscode.starwars.network.usecase.gateway;

import com.letscode.starwars.network.usecase.domain.NegociacaoDomain;

public interface ConsolidarNegociacaoGateway {

  void consolidar(Long idRemetente, Long idDestinatario, NegociacaoDomain negociacaoDomain);

}
