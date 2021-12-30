package com.letscode.starwars.network.integracao;

import com.letscode.starwars.network.TestContext;
import com.letscode.starwars.network.fixture.RebeldeFixture;
import com.letscode.starwars.network.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class GerarRelatorioControllerTest extends TestContext {

  private static final String URL_RELATORIO = "/rebeldes/relatorio";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RebeldeFixture rebeldeFixture;

  @Test
  void deveGerarRelatorioComSucesso() throws Exception {

    final Long primeiroRebelde = rebeldeFixture.novoRebelde().getId();
    final Long segundoRebelde = rebeldeFixture.novoRebelde().getId();
    final Long terceiroRebelde = rebeldeFixture.novoRebelde().getId();
    final Long idTraidor = rebeldeFixture.novoRebelde().getId();

    rebeldeFixture.reportarTraidor(primeiroRebelde, idTraidor);
    rebeldeFixture.reportarTraidor(segundoRebelde, idTraidor);
    rebeldeFixture.reportarTraidor(terceiroRebelde, idTraidor);

    this.mockMvc.perform(get(URL_RELATORIO)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/gerar-relatorio-sucesso.json")));
  }
}
