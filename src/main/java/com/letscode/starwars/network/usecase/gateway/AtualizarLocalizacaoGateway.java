package com.letscode.starwars.network.usecase.gateway;

import com.letscode.starwars.network.usecase.domain.RebeldeLocalizacaoDomain;

public interface AtualizarLocalizacaoGateway {

  void atualizar(Long idRebelde, RebeldeLocalizacaoDomain rebeldeLocalizacaoDomain);

}
