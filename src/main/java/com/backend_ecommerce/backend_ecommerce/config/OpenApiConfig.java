package com.backend_ecommerce.backend_ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(
            new Info()
            .title("E-commerce API")
            .version("1.0.0")
            .description("API para gerenciamento de produtos e pedidos em um sistema de e-commerce")
        );
    }
}