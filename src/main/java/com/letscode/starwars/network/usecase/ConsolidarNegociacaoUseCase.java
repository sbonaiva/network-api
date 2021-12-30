package com.letscode.starwars.network.usecase;

import com.letscode.starwars.network.usecase.domain.NegociacaoDomain;
import com.letscode.starwars.network.usecase.gateway.ConsolidarNegociacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsolidarNegociacaoUseCase {

  private final ConsolidarNegociacaoGateway consolidarNegociacaoGateway;

  public void executar(@NonNull final Long idRemetente,
                       @NonNull final Long idDestinatario,
                       @NonNull final NegociacaoDomain negociacaoDomain) {
    consolidarNegociacaoGateway.consolidar(idRemetente, idDestinatario, negociacaoDomain);
  }
}
