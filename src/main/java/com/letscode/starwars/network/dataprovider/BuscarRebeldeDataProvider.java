package com.letscode.starwars.network.dataprovider;

import com.letscode.starwars.network.dataprovider.exception.DataProviderException;
import com.letscode.starwars.network.dataprovider.mapper.RebeldeRepositoryMapper;
import com.letscode.starwars.network.dataprovider.repository.RebeldeRepository;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.gateway.BuscarRebeldeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarRebeldeDataProvider implements BuscarRebeldeGateway {

  private static final String REBELDE_NAO_ENCONTRADO = "Rebelde %s não encontrado";

  private final RebeldeRepositoryMapper rebeldeRepositoryMapper;
  private final RebeldeRepository rebeldeRepository;

  @Override
  public RebeldeDomain buscar(final Long idRebelde) {

    return rebeldeRepository.findById(idRebelde)
            .map(rebeldeRepositoryMapper::toDomain)
            .orElseThrow(() -> new DataProviderException(String.format(REBELDE_NAO_ENCONTRADO, idRebelde), HttpStatus.NOT_FOUND));
  }
}
