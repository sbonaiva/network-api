package com.letscode.starwars.network.entrypoint.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.letscode.starwars.network.dataprovider.exception.DataProviderException;
import com.letscode.starwars.network.usecase.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErroResponseDto> handleBusinessException(final BusinessException exception) {
    return criarResponse(exception.getMessage(), exception.getHttpStatus());
  }

  @ExceptionHandler(DataProviderException.class)
  public ResponseEntity<ErroResponseDto> handleDataProviderException(final DataProviderException exception) {
    return criarResponse(exception.getMessage(), exception.getHttpStatus());
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ErroResponseDto> handleBindException(final BindException exception) {

    final List<String> campos = exception.getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());

    return criarResponse("Requisição inválida", HttpStatus.BAD_REQUEST, campos);
  }

  private ResponseEntity<ErroResponseDto> criarResponse(final String mensagem,
                                                        final HttpStatus httpStatus) {
    return criarResponse(mensagem, httpStatus, Collections.emptyList());
  }

  private ResponseEntity<ErroResponseDto> criarResponse(final String mensagem,
                                                        final HttpStatus httpStatus,
                                                        final List<String> campos) {
    return new ResponseEntity<>(ErroResponseDto.builder()
            .mensagem(mensagem)
            .codigo(httpStatus.value())
            .erro(httpStatus.getReasonPhrase())
            .campos(campos)
            .build(),
            httpStatus);
  }

  @Getter
  @Builder
  @AllArgsConstructor
  private static class ErroResponseDto {

    private String mensagem;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> campos = new ArrayList<>();
    private Integer codigo;
    private String erro;

  }
}
