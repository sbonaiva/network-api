package com.letscode.starwars.network.dataprovider;

import com.letscode.starwars.network.dataprovider.entity.RebeldeEntity;
import com.letscode.starwars.network.dataprovider.exception.DataProviderException;
import com.letscode.starwars.network.dataprovider.mapper.RebeldeRepositoryMapper;
import com.letscode.starwars.network.dataprovider.repository.RebeldeRepository;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.gateway.AdicionarRebeldeGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdicionarRebeldeDataProvider implements AdicionarRebeldeGateway {

  private static final String ERRO_INESPERADO = "Erro inesperado ao adicionar Rebelde";

  private final RebeldeRepositoryMapper rebeldeRepositoryMapper;
  private final RebeldeRepository rebeldeRepository;

  @Override
  public RebeldeDomain adicionar(final RebeldeDomain rebeldeDomain) {

    try {
      final RebeldeEntity rebeldeParaPersistir = rebeldeRepositoryMapper.toEntity(rebeldeDomain);
      final RebeldeEntity rebeldePersistido = rebeldeRepository.saveAndFlush(rebeldeParaPersistir);
      return rebeldeRepositoryMapper.toDomain(rebeldePersistido);
    } catch (Exception e) {
      log.error(ERRO_INESPERADO, e);
      throw new DataProviderException(ERRO_INESPERADO, e);
    }
  }
}
