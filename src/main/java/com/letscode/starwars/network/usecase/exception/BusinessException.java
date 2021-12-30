package com.letscode.starwars.network.usecase.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

  private final HttpStatus httpStatus;

  public BusinessException(final String message,
                           final HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
