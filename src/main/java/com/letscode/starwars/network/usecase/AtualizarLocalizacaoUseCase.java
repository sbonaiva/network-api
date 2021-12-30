package com.letscode.starwars.network.usecase;

import com.letscode.starwars.network.usecase.domain.RebeldeLocalizacaoDomain;
import com.letscode.starwars.network.usecase.gateway.AtualizarLocalizacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarLocalizacaoUseCase {

  private final AtualizarLocalizacaoGateway atualizarLocalizacaoGateway;

  @Transactional
  public void executar(@NonNull final Long idRebelde,
                       @NonNull final RebeldeLocalizacaoDomain rebeldeLocalizacaoDomain) {
    atualizarLocalizacaoGateway.atualizar(idRebelde, rebeldeLocalizacaoDomain);
  }
}
