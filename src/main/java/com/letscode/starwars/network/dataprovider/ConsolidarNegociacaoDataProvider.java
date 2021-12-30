package com.letscode.starwars.network.dataprovider;

import com.letscode.starwars.network.dataprovider.entity.RebeldeEntity;
import com.letscode.starwars.network.dataprovider.entity.RebeldeItemEntity;
import com.letscode.starwars.network.dataprovider.exception.DataProviderException;
import com.letscode.starwars.network.dataprovider.mapper.RebeldeRepositoryMapper;
import com.letscode.starwars.network.dataprovider.repository.RebeldeRepository;
import com.letscode.starwars.network.usecase.domain.NegociacaoDomain;
import com.letscode.starwars.network.usecase.gateway.ConsolidarNegociacaoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsolidarNegociacaoDataProvider implements ConsolidarNegociacaoGateway {

  private static final String ERRO_INESPERADO = "Erro inesperado. Negociação cancelada.";

  private final RebeldeRepositoryMapper rebeldeRepositoryMapper;
  private final RebeldeRepository rebeldeRepository;

  @Override
  public void consolidar(final Long idRemetente,
                         final Long idDestinatario,
                         final NegociacaoDomain negociacaoDomain) {

    try {

      final Set<RebeldeItemEntity> oferta = rebeldeRepositoryMapper
              .toEntity(negociacaoDomain.getOferta());

      final Set<RebeldeItemEntity> procura = rebeldeRepositoryMapper
              .toEntity(negociacaoDomain.getProcura());

      atualizarInventario(idRemetente, procura, oferta);

      atualizarInventario(idDestinatario, oferta, procura);

    } catch (final Exception e) {
      log.error(ERRO_INESPERADO, e);
      throw new DataProviderException(ERRO_INESPERADO, e);
    }
  }

  private void atualizarInventario(final Long idRemetente,
                                   final Set<RebeldeItemEntity> adicao,
                                   final Set<RebeldeItemEntity> remocao) {

    final RebeldeEntity rebeldeEntity = rebeldeRepository.getById(idRemetente);

    adicao.forEach(itemAdicao -> adicionar(itemAdicao, rebeldeEntity.getInventario()));

    remocao.forEach(itemRemocao -> remover(itemRemocao, rebeldeEntity.getInventario()));

    rebeldeRepository.saveAndFlush(rebeldeEntity);
  }

  private void adicionar(final RebeldeItemEntity itemAdicao,
                         final Set<RebeldeItemEntity> inventario) {

    final AtomicBoolean existeItem = new AtomicBoolean(false);

    inventario.forEach(itemInventario -> {
      if (itemInventario.getItem().equalsIgnoreCase(itemAdicao.getItem())) {
        itemInventario.setQuantidade(itemInventario.getQuantidade() + itemAdicao.getQuantidade());
        existeItem.set(true);
      }
    });

    if (! existeItem.get()) {
      final RebeldeItemEntity novoItem = new RebeldeItemEntity();
      novoItem.setItem(itemAdicao.getItem());
      novoItem.setQuantidade(novoItem.getQuantidade());
      inventario.add(novoItem);
    }
  }

  private void remover(final RebeldeItemEntity itemRemocao,
                       final Set<RebeldeItemEntity> inventario) {

    inventario.forEach(itemInventario -> {
      if (itemInventario.getItem().equalsIgnoreCase(itemRemocao.getItem())) {
        itemInventario.setQuantidade(itemInventario.getQuantidade() - itemRemocao.getQuantidade());
      }
    });
  }
}
