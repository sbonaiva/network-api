package com.letscode.starwars.network.integracao;

import com.letscode.starwars.network.TestContext;
import com.letscode.starwars.network.utils.JsonUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class AdicionarRebeldeControllerTest extends TestContext {

  private static final String URL_CADASTRO = "/rebeldes";

  @Autowired
  private MockMvc mockMvc;

  @MethodSource("cenarios")
  @ParameterizedTest
  void deveValidarCenarios(final String arquivoRequest,
                           final String arquivoResponse,
                           final HttpStatus httpStatus) throws Exception {

    this.mockMvc.perform(post(URL_CADASTRO)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/".concat(arquivoRequest))))
            .andExpect(status().is(httpStatus.value()))
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/".concat(arquivoResponse))));
  }

  private static Stream<Arguments> cenarios() {
    return Stream.of(
            Arguments.of("adicionar-rebelde-sucesso.json", "adicionar-rebelde-sucesso.json", HttpStatus.CREATED),
            Arguments.of("adicionar-rebelde-sem-nome.json", "adicionar-rebelde-sem-nome.json", HttpStatus.BAD_REQUEST),
            Arguments.of("adicionar-rebelde-sem-idade.json", "adicionar-rebelde-sem-idade.json", HttpStatus.BAD_REQUEST),
            Arguments.of("adicionar-rebelde-sem-genero.json", "adicionar-rebelde-sem-genero.json", HttpStatus.BAD_REQUEST),
            Arguments.of("adicionar-rebelde-sem-localizacao.json", "adicionar-rebelde-sem-localizacao.json", HttpStatus.BAD_REQUEST),
            Arguments.of("adicionar-rebelde-localizacao-parcial.json", "adicionar-rebelde-localizacao-parcial.json", HttpStatus.BAD_REQUEST),
            Arguments.of("adicionar-rebelde-inventario-vazio.json", "adicionar-rebelde-inventario-vazio.json", HttpStatus.BAD_REQUEST)
    );
  }
}
