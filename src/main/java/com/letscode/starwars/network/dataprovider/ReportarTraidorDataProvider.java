package com.letscode.starwars.network.dataprovider;

import com.letscode.starwars.network.dataprovider.entity.RebeldeEntity;
import com.letscode.starwars.network.dataprovider.exception.DataProviderException;
import com.letscode.starwars.network.dataprovider.repository.RebeldeRepository;
import com.letscode.starwars.network.usecase.domain.TraidorDomain;
import com.letscode.starwars.network.usecase.gateway.ReportarTraidorGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportarTraidorDataProvider implements ReportarTraidorGateway {

  private static final String REPORTER_NAO_ENCONTRADO = "Rebelde reporter %s não encontrado";
  private static final String REPORTADO_NAO_ENCONTRADO = "Rebelde reportado %s não encontrado";
  private static final String ERRO_INESPERADO = "Erro inesperado ao reportar Rebelde";

  private final RebeldeRepository rebeldeRepository;

  @Override
  public void reportar(final TraidorDomain traidorDomain) {

    try {

      final boolean reporterExiste = rebeldeRepository.existsById(traidorDomain.getIdReporter());

      if (!reporterExiste) {
        throw new DataProviderException(String.format(REPORTER_NAO_ENCONTRADO, traidorDomain.getIdReporter()), HttpStatus.NOT_FOUND);
      }

      final RebeldeEntity rebeldeReportado = rebeldeRepository.findById(traidorDomain.getIdReportado())
              .orElseThrow(() -> new DataProviderException(String.format(REPORTADO_NAO_ENCONTRADO, traidorDomain.getIdReportado()), HttpStatus.NOT_FOUND));

      rebeldeReportado.adicionarTraicao(traidorDomain.getIdReporter());

      rebeldeRepository.saveAndFlush(rebeldeReportado);

    } catch (final DataProviderException e) {
      throw e;
    } catch (final Exception e) {
      log.error(ERRO_INESPERADO, e);
      throw new DataProviderException(ERRO_INESPERADO, e);
    }
  }
}
