package com.bank.profile.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI configuration for the profile microservice.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI profileOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Profile Service API")
                        .description("REST API for managing customer profiles")
                        .version("v1")
                        .contact(new Contact().name("Profile Team").email("support@example.com"))
                        .license(new License().name("Apache 2.0")));
    }
}
