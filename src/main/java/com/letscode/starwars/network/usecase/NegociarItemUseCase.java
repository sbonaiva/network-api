package com.letscode.starwars.network.usecase;

import com.letscode.starwars.network.usecase.domain.NegociacaoDomain;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NegociarItemUseCase {

  private final BuscarRebeldeUseCase buscarRebeldeUseCase;
  private final ConsolidarNegociacaoUseCase consolidarNegociacaoUseCase;

  private static final String PONTUACAO_DIFERENTE_OFERTA_PROCURA = "Pontuação diferente entre itens. Negociação cancelada.";

  private static final String REMETENTE_TRAIDOR = "Remetente %s reportado como traidor. Negociação cancelada.";
  private static final String DESTINATARIO_TRAIDOR = "Destinatário %s reportado como traidor. Negociação cancelada.";

  private static final String REMETENTE_NAO_POSSUI_INVENTARIO = "Remetente %s não possui inventário suficiente. Negociação cancelada.";
  private static final String DESTINATARIO_NAO_POSSUI_INVENTARIO = "Destinatário %s não possui inventário suficiente. Negociação cancelada.";

  @Transactional
  public void executar(@NonNull final Long idRemetente,
                       @NonNull final Long idDestinatario,
                       @NonNull final NegociacaoDomain negociacaoDomain) {

    if (negociacaoDomain.pontuacaoDiferenteOfertaProcura()) {
      throw new BusinessException(PONTUACAO_DIFERENTE_OFERTA_PROCURA, HttpStatus.PRECONDITION_FAILED);
    }

    final RebeldeDomain remetente = buscarRebeldeUseCase.executar(idRemetente);

    if (remetente.traidor()) {
      throw new BusinessException(String.format(REMETENTE_TRAIDOR, idRemetente), HttpStatus.PRECONDITION_FAILED);
    }

    if (remetente.naoPossuiItensNegociacao(negociacaoDomain.getOferta())) {
      throw new BusinessException(String.format(REMETENTE_NAO_POSSUI_INVENTARIO, idDestinatario), HttpStatus.PRECONDITION_FAILED);
    }

    final RebeldeDomain destinatario = buscarRebeldeUseCase.executar(idDestinatario);

    if (destinatario.traidor()) {
      throw new BusinessException(String.format(DESTINATARIO_TRAIDOR, idDestinatario), HttpStatus.PRECONDITION_FAILED);
    }

    if (destinatario.naoPossuiItensNegociacao(negociacaoDomain.getProcura())) {
      throw new BusinessException(String.format(DESTINATARIO_NAO_POSSUI_INVENTARIO, idDestinatario), HttpStatus.PRECONDITION_FAILED);
    }

    consolidarNegociacaoUseCase.executar(idRemetente, idDestinatario, negociacaoDomain);
  }
}
