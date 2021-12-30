package com.letscode.starwars.network.fixture;

import com.letscode.starwars.network.usecase.AdicionarRebeldeUseCase;
import com.letscode.starwars.network.usecase.BuscarRebeldeUseCase;
import com.letscode.starwars.network.usecase.ReportarTraidorUseCase;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.domain.RebeldeItemDomain;
import com.letscode.starwars.network.usecase.domain.RebeldeLocalizacaoDomain;
import com.letscode.starwars.network.usecase.domain.TraidorDomain;
import com.letscode.starwars.network.usecase.enumeration.ItemTipo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RebeldeFixture {

  private final ReportarTraidorUseCase reportarTraidorUseCase;
  private final AdicionarRebeldeUseCase adicionarRebeldeUseCase;
  private final BuscarRebeldeUseCase buscarRebeldeUseCase;

  public RebeldeDomain novoRebelde() {

    final RebeldeItemDomain rebeldeItemDomain = new RebeldeItemDomain();
    rebeldeItemDomain.setItem(ItemTipo.ARMA);
    rebeldeItemDomain.setQuantidade(10);

    final RebeldeLocalizacaoDomain rebeldeLocalizacaoDomain = new RebeldeLocalizacaoDomain();
    rebeldeLocalizacaoDomain.setGalaxia("xpto");
    rebeldeLocalizacaoDomain.setLatitude(-22.143766);
    rebeldeLocalizacaoDomain.setLongitude(-22.143766);

    final Set<RebeldeItemDomain> rebeldeItensDomain = new HashSet<>();
    rebeldeItensDomain.add(rebeldeItemDomain);

    final RebeldeDomain rebeldeDomain = new RebeldeDomain();
    rebeldeDomain.setNome("Rebelde");
    rebeldeDomain.setIdade(33);
    rebeldeDomain.setGenero("xpto");
    rebeldeDomain.setLocalizacao(rebeldeLocalizacaoDomain);
    rebeldeDomain.setInventario(rebeldeItensDomain);

    return adicionarRebeldeUseCase.executar(rebeldeDomain);
  }

  public RebeldeDomain novoRebelde(final ItemTipo item,
                                   final Integer quantidade) {

    final RebeldeItemDomain rebeldeItemDomain = new RebeldeItemDomain();
    rebeldeItemDomain.setItem(item);
    rebeldeItemDomain.setQuantidade(quantidade);

    final RebeldeLocalizacaoDomain rebeldeLocalizacaoDomain = new RebeldeLocalizacaoDomain();
    rebeldeLocalizacaoDomain.setGalaxia("xpto");
    rebeldeLocalizacaoDomain.setLatitude(-22.143766);
    rebeldeLocalizacaoDomain.setLongitude(-22.143766);

    final Set<RebeldeItemDomain> rebeldeItensDomain = new HashSet<>();
    rebeldeItensDomain.add(rebeldeItemDomain);

    final RebeldeDomain rebeldeDomain = new RebeldeDomain();
    rebeldeDomain.setNome("Rebelde");
    rebeldeDomain.setIdade(33);
    rebeldeDomain.setGenero("xpto");
    rebeldeDomain.setLocalizacao(rebeldeLocalizacaoDomain);
    rebeldeDomain.setInventario(rebeldeItensDomain);

    return adicionarRebeldeUseCase.executar(rebeldeDomain);
  }

  public RebeldeDomain buscarRebelde(final Long id) {
    return buscarRebeldeUseCase.executar(id);
  }

  public void reportarTraidor(final Long idReporter,
                              final Long idReportado) {
    final TraidorDomain traidorDomain = new TraidorDomain();
    traidorDomain.setIdReporter(idReporter);
    traidorDomain.setIdReportado(idReportado);
    reportarTraidorUseCase.executar(traidorDomain);
  }
}
