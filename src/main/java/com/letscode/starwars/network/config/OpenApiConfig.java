package com.letscode.starwars.network.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
            .info(new Info().title("Star Wars Resistence Social Network API")
                    .description("Desafio da Let's Code")
                    .version("1.0.0"));
  }
}
