package com.letscode.starwars.network.integracao;

import com.letscode.starwars.network.TestContext;
import com.letscode.starwars.network.fixture.RebeldeFixture;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class AtualizarLocalizacaoControllerTest extends TestContext {

  private static final String URL_LOCALIZACAO = "/rebeldes/{idRebelde}/localizacao";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RebeldeFixture rebeldeFixture;

  @Test
  void deveAtualizarLocalizacao() throws Exception {

    final Long idRebelde = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(patch(URL_LOCALIZACAO, idRebelde)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/atualizar-localizacao-sucesso.json")))
            .andExpect(status().isNoContent());

    final RebeldeDomain rebeldeDomain = rebeldeFixture.buscarRebelde(idRebelde);

    assertEquals("Mandalore", rebeldeDomain.getLocalizacao().getGalaxia());
    assertEquals(48.85852, rebeldeDomain.getLocalizacao().getLatitude());
    assertEquals(2.29448, rebeldeDomain.getLocalizacao().getLongitude());
  }

  @Test
  void naoDeveAtualizarLocalizacaoQuandoRequisicaoInvalida() throws Exception {

    this.mockMvc.perform(patch(URL_LOCALIZACAO, 200)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/atualizar-localizacao-requisicao-invalida.json")))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/atualizar-localizacao-requisicao-invalida.json")));
  }

  @Test
  void naoDeveAtualizarLocalizacaoQuandoRebeldeNaoEncontrado() throws Exception {

    this.mockMvc.perform(patch(URL_LOCALIZACAO, 200)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/atualizar-localizacao-sucesso.json")))
            .andExpect(status().isNotFound())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/atualizar-localizacao-rebelde-nao-existe.json")));
  }
}
