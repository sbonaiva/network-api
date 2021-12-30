package com.letscode.starwars.network.dataprovider;

import com.letscode.starwars.network.dataprovider.exception.DataProviderException;
import com.letscode.starwars.network.dataprovider.repository.RebeldeRepository;
import com.letscode.starwars.network.usecase.domain.RelatorioDomain;
import com.letscode.starwars.network.usecase.enumeration.ItemTipo;
import com.letscode.starwars.network.usecase.gateway.GerarRelatorioGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class GerarRelatorioDataProvider implements GerarRelatorioGateway {

  private static final String ERRO_INESPERADO = "Erro inesperado ao gerar relatório";

  private final RebeldeRepository rebeldeRepository;

  @Override
  public RelatorioDomain gerar() {

    try {

      final long totalSoldados = rebeldeRepository.count();
      final long totalRebeldes = rebeldeRepository.countRebeldes();
      final long totalTraidores = rebeldeRepository.countTraidores();

      return RelatorioDomain.builder()
              .totalSoldados(totalSoldados)
              .porcentagemDeRebeldes(calcularPorcentagem(totalSoldados, totalRebeldes))
              .porcentagemDeTraidores(calcularPorcentagem(totalSoldados, totalTraidores))
              .mediaDeRecursosPorRebelde(calcularMediaRecursos(totalRebeldes))
              .pontosPerdidosPorTraidores(calcularPontosPerdidos())
              .build();

    } catch (final Exception e) {
      log.error(ERRO_INESPERADO, e);
      throw new DataProviderException(ERRO_INESPERADO, e);
    }
  }

  private Map<String, Double> calcularMediaRecursos(double totalRebeldes) {

    final Map<String, Double> mediaRecursos = new HashMap<>();

    Arrays.stream(ItemTipo.values()).forEach(item -> {

      final Integer recursosRebeldes = rebeldeRepository.countRecursosRebeldes(item.name());

      if (Objects.nonNull(recursosRebeldes)) {
        mediaRecursos.put(item.name().toLowerCase(), recursosRebeldes / totalRebeldes);
      } else {
        mediaRecursos.put(item.name().toLowerCase(), 0d);
      }
    });

    return mediaRecursos;
  }

  private Integer calcularPontosPerdidos() {

    final List<Integer> pontosPerdidos = new ArrayList<>();

    Arrays.stream(ItemTipo.values()).forEach(item -> {

      final Integer recursosTraidores = rebeldeRepository.countRecursosTraidores(item.name());

      if (Objects.nonNull(recursosTraidores)) {
        pontosPerdidos.add(recursosTraidores * item.getPontos());
      }
    });

    return pontosPerdidos.stream()
            .mapToInt(Integer::intValue)
            .sum();
  }

  private static double calcularPorcentagem(long total, long parcial) {
    return total == 0 ? 0 : ((parcial * 100d) / total);
  }
}
