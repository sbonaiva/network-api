package com.letscode.starwars.network.usecase.domain;

import lombok.Data;

import java.util.Set;

@Data
public class RebeldeDomain {

  private static final int MINIMO_PARA_SER_TRAIDOR = 3;

  private Long id;
  private String nome;
  private Integer idade;
  private String genero;
  private RebeldeLocalizacaoDomain localizacao;
  private Set<RebeldeItemDomain> inventario;
  private Set<Long> traicoes;

  public boolean traidor() {
    return traicoes.size() >= MINIMO_PARA_SER_TRAIDOR;
  }

  public boolean naoPossuiItensNegociacao(final Set<NegociacaoItemDomain> itensNegociacao) {
    return ! itensNegociacao.stream()
            .allMatch(itemNegociacao -> contemNoInventario(itemNegociacao, inventario));
  }

  private boolean contemNoInventario(final NegociacaoItemDomain itemNegociacao,
                                     final Set<RebeldeItemDomain> inventario) {
    return inventario.stream()
            .anyMatch(itemInventario -> itemInventario.getItem().equals(itemNegociacao.getItem()) &&
                    itemInventario.getQuantidade() >= itemNegociacao.getQuantidade());
  }
}
