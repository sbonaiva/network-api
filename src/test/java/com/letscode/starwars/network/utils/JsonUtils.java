package com.letscode.starwars.network.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class JsonUtils {

  public static String arquivoParaString(final String nomeArquivo) {

    try (final InputStream is = new ClassPathResource("/json/".concat(nomeArquivo)).getInputStream()) {
      return IOUtils.toString(is, StandardCharsets.UTF_8);
    } catch (final Exception e) {
      throw new AssertionError(String.format("Falha ao ler arquivo %s", nomeArquivo));
    }
  }
}
