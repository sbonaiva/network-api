package com.letscode.starwars.network.entrypoint.annotation;

import com.letscode.starwars.network.usecase.enumeration.ItemTipo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ItemValidator implements ConstraintValidator<Item, String> {

  @Override
  public boolean isValid(final String valor,
                         final ConstraintValidatorContext contexto) {

    return Arrays.stream(ItemTipo.values())
            .anyMatch(item -> item.name().equalsIgnoreCase(valor));
  }
}
