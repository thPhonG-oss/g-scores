package com.phong.g_scores_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("G-Scores API")
                                .description("API documentation for G-Scores application"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local server")
                ));
    }
}
