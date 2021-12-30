package com.letscode.starwars.network.integracao;

import com.letscode.starwars.network.TestContext;
import com.letscode.starwars.network.fixture.RebeldeFixture;
import com.letscode.starwars.network.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class ReportarTraidorControllerTest extends TestContext {

  private static final String URL_REPORTAR = "/rebeldes/{idReporter}/reportar/{idReportado}";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RebeldeFixture rebeldeFixture;

  @Test
  void deveReportarRebelde() throws Exception {

    final Long idReporter = rebeldeFixture.novoRebelde().getId();
    final Long idReportado = rebeldeFixture.novoRebelde().getId();

    assertTrue(rebeldeFixture.buscarRebelde(idReportado).getTraicoes().isEmpty());

    this.mockMvc.perform(post(URL_REPORTAR, idReporter, idReportado)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

    assertFalse(rebeldeFixture.buscarRebelde(idReportado).getTraicoes().isEmpty());
  }

  @Test
  void naoDeveReportarQuandoReporterNaoExistir() throws Exception {

    final Long idReportado = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(post(URL_REPORTAR, 200, idReportado)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/reportar-rebelde-reporter-nao-existe.json")));
  }

  @Test
  void naoDeveReportarQuandoReportadoNaoExistir() throws Exception {

    final Long idReporter = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(post(URL_REPORTAR, idReporter, 200)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/reportar-rebelde-reportado-nao-existe.json")));
  }
}
