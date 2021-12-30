package com.letscode.starwars.network.usecase.domain;

import com.letscode.starwars.network.usecase.enumeration.ItemTipo;
import lombok.Data;

@Data
public class NegociacaoItemDomain {

  private ItemTipo item;
  private Integer quantidade;

}
