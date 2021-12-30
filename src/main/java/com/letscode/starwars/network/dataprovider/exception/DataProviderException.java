package com.letscode.starwars.network.dataprovider.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataProviderException extends RuntimeException {

  private final HttpStatus httpStatus;

  public DataProviderException(final String message,
                               final HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public DataProviderException(final String message,
                               final Throwable cause) {
    super(message, cause);
    this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
