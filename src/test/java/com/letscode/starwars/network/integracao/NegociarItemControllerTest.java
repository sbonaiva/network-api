package com.letscode.starwars.network.integracao;

import com.letscode.starwars.network.TestContext;
import com.letscode.starwars.network.fixture.RebeldeFixture;
import com.letscode.starwars.network.usecase.enumeration.ItemTipo;
import com.letscode.starwars.network.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class NegociarItemControllerTest extends TestContext {

  private static final String URL_NEGOCIACAO = "/rebeldes/{idRemetente}/negociar/{idDestinatario}";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RebeldeFixture rebeldeFixture;

  @Test
  void deveNegociarItens() throws Exception {

    final Long idRemetente = rebeldeFixture.novoRebelde().getId();
    final Long idDestinatario = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(post(URL_NEGOCIACAO, idRemetente, idDestinatario)
                    .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtils.arquivoParaString("/request/negociar-item-sucesso.json")))
            .andExpect(status().isNoContent());
  }

  @Test
  void naoDeveNegociarComPontuacaoDiferente() throws Exception {

    final Long idRemetente = rebeldeFixture.novoRebelde().getId();
    final Long idDestinatario = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(post(URL_NEGOCIACAO, idRemetente, idDestinatario)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/negociar-item-pontuacao-diferente.json")))
            .andExpect(status().isPreconditionFailed())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/negociar-item-pontuacao-diferente.json")));
  }

  @Test
  void naoDeveNegociarComRemetenteInexistente() throws Exception {

    final Long idDestinatario = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(post(URL_NEGOCIACAO, 400, idDestinatario)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/negociar-item-sucesso.json")))
            .andExpect(status().isNotFound())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/negociar-item-remetente-nao-existe.json")));
  }

  @Test
  void naoDeveNegociarComDestinatarioInexistente() throws Exception {

    final Long idRemetente = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(post(URL_NEGOCIACAO, idRemetente, 400)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/negociar-item-sucesso.json")))
            .andExpect(status().isNotFound())
            .andExpect(content().json(JsonUtils.arquivoParaString("/response/negociar-item-destinatario-nao-existe.json")));
  }

  @Test
  void naoDeveNegociarComRemetenteTraidor() throws Exception {

    final Long primeiroRebelde = rebeldeFixture.novoRebelde().getId();
    final Long segundoRebelde = rebeldeFixture.novoRebelde().getId();
    final Long terceiroRebelde = rebeldeFixture.novoRebelde().getId();

    final Long idRemetente = rebeldeFixture.novoRebelde().getId();
    final Long idDestinatario = rebeldeFixture.novoRebelde().getId();

    rebeldeFixture.reportarTraidor(primeiroRebelde, idRemetente);
    rebeldeFixture.reportarTraidor(segundoRebelde, idRemetente);
    rebeldeFixture.reportarTraidor(terceiroRebelde, idRemetente);

    this.mockMvc.perform(post(URL_NEGOCIACAO, idRemetente, idDestinatario)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/negociar-item-sucesso.json")))
            .andExpect(status().isPreconditionFailed())
            .andExpect(jsonPath("$.mensagem", equalTo(String.format("Remetente %s reportado como traidor. Negociação cancelada.", idRemetente))));
  }

  @Test
  void naoDeveNegociarComDestinatarioTraidor() throws Exception {

    final Long primeiroRebelde = rebeldeFixture.novoRebelde().getId();
    final Long segundoRebelde = rebeldeFixture.novoRebelde().getId();
    final Long terceiroRebelde = rebeldeFixture.novoRebelde().getId();

    final Long idRemetente = rebeldeFixture.novoRebelde().getId();
    final Long idDestinatario = rebeldeFixture.novoRebelde().getId();

    rebeldeFixture.reportarTraidor(primeiroRebelde, idDestinatario);
    rebeldeFixture.reportarTraidor(segundoRebelde, idDestinatario);
    rebeldeFixture.reportarTraidor(terceiroRebelde, idDestinatario);

    this.mockMvc.perform(post(URL_NEGOCIACAO, idRemetente, idDestinatario)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/negociar-item-sucesso.json")))
            .andExpect(status().isPreconditionFailed())
            .andExpect(jsonPath("$.mensagem", equalTo(String.format("Destinatário %s reportado como traidor. Negociação cancelada.", idDestinatario))));
  }

  @Test
  void naoDeveNegociarComRemetenteSemInventario() throws Exception {

    final Long idRemetente = rebeldeFixture.novoRebelde(ItemTipo.COMIDA, 1).getId();
    final Long idDestinatario = rebeldeFixture.novoRebelde().getId();

    this.mockMvc.perform(post(URL_NEGOCIACAO, idRemetente, idDestinatario)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/negociar-item-sucesso.json")))
            .andExpect(status().isPreconditionFailed())
            .andExpect(jsonPath("$.mensagem", equalTo(String.format("Remetente %s não possui inventário suficiente. Negociação cancelada.", idDestinatario))));
  }

  @Test
  void naoDeveNegociarComDestinatarioSemInventario() throws Exception {

    final Long idRemetente = rebeldeFixture.novoRebelde().getId();
    final Long idDestinatario = rebeldeFixture.novoRebelde(ItemTipo.COMIDA, 1).getId();

    this.mockMvc.perform(post(URL_NEGOCIACAO, idRemetente, idDestinatario)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.arquivoParaString("/request/negociar-item-sucesso.json")))
            .andExpect(status().isPreconditionFailed())
            .andExpect(jsonPath("$.mensagem", equalTo(String.format("Destinatário %s não possui inventário suficiente. Negociação cancelada.", idDestinatario))));
  }
}