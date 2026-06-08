package br.com.nutriguacu.mig_recicla_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Mig-RECICLA - Nutriguaçu")
                        .version("1.0.0")
                        .description("Documentação oficial dos endpoints para o controle logístico de retorno de embalagens."));
    }
}