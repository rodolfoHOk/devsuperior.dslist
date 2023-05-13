package br.com.hiokdev.dslist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("DS List")
        .version("v1")
        .description("Game list API")
      )
      .tags(Arrays.asList(
        new Tag().name("Games lists").description("Manager Games Lists"),
        new Tag().name("Games").description("Manager Games")
      ))
    ;
  }

}
