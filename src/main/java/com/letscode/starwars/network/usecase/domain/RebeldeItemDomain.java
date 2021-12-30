package com.letscode.starwars.network.usecase.domain;

import com.letscode.starwars.network.usecase.enumeration.ItemTipo;
import lombok.Data;

@Data
public class RebeldeItemDomain {

  private ItemTipo item;
  private Integer quantidade;

}
