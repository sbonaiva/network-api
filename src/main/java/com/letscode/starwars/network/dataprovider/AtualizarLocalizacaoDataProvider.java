package com.letscode.starwars.network.dataprovider;

import com.letscode.starwars.network.dataprovider.entity.RebeldeEntity;
import com.letscode.starwars.network.dataprovider.exception.DataProviderException;
import com.letscode.starwars.network.dataprovider.mapper.RebeldeRepositoryMapper;
import com.letscode.starwars.network.dataprovider.repository.RebeldeRepository;
import com.letscode.starwars.network.usecase.domain.RebeldeLocalizacaoDomain;
import com.letscode.starwars.network.usecase.gateway.AtualizarLocalizacaoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtualizarLocalizacaoDataProvider implements AtualizarLocalizacaoGateway {

  private static final String REBELDE_NAO_ENCONTRADO = "Rebelde %s não encontrado";
  private static final String ERRO_INESPERADO = "Erro inesperado ao atualizar localização do Rebelde";

  private final RebeldeRepositoryMapper rebeldeRepositoryMapper;
  private final RebeldeRepository rebeldeRepository;

  @Override
  public void atualizar(final Long idRebelde,
                        final RebeldeLocalizacaoDomain rebeldeLocalizacaoDomain) {

    try {

      final RebeldeEntity rebeldePersistido = rebeldeRepository.findById(idRebelde)
              .orElseThrow(() -> new DataProviderException(String.format(REBELDE_NAO_ENCONTRADO, idRebelde), HttpStatus.NOT_FOUND));

      final RebeldeEntity rebeldeParaPersistir = rebeldeRepositoryMapper.toEntity(rebeldePersistido, rebeldeLocalizacaoDomain);

      rebeldeRepository.saveAndFlush(rebeldeParaPersistir);

    } catch (DataProviderException e) {
      throw e;
    } catch (Exception e) {
      log.error(ERRO_INESPERADO, e);
      throw new DataProviderException(ERRO_INESPERADO, e);
    }
  }
}
