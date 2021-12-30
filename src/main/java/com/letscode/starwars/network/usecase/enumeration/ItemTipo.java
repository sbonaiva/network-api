package com.letscode.starwars.network.usecase.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ItemTipo {

  COMIDA(1),
  AGUA(2),
  MUNICAO(3),
  ARMA(4);

  private final Integer pontos;

}
