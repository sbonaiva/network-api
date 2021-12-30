package com.letscode.starwars.network.usecase.domain;

import lombok.Data;

import java.util.Set;

@Data
public class NegociacaoDomain {

  private Set<NegociacaoItemDomain> oferta;
  private Set<NegociacaoItemDomain> procura;

  public boolean pontuacaoDiferenteOfertaProcura() {

    final int pontosOferta = this.oferta.stream()
            .mapToInt(item -> item.getItem().getPontos() * item.getQuantidade())
            .sum();

    final int pontosProcura = this.procura.stream()
            .mapToInt(item -> item.getItem().getPontos() * item.getQuantidade())
            .sum();

    return pontosOferta != pontosProcura;
  }
}
