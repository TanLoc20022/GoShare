package com.example.GoShare.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    /**
     * Configures the OpenAPI documentation for the Authentication Service with JWT
     * authentication.
     * This method defines the security scheme to be used for authentication and
     * adds it to the OpenAPI specification.
     * 
     * @return the OpenAPI object with custom authentication settings.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Set the title for the API documentation
                .info(new Info().title("JavaInUse Authentication Service"))

                // Add a security requirement specifying the use of a security scheme named
                .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScheme"))

                // Define the security schemes (authentication methods) used by the API
                .components(new Components().addSecuritySchemes("JavaInUseSecurityScheme",
                        new SecurityScheme()
                                .name("JavaInUseSecurityScheme")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("Bearer")
                                .bearerFormat("JWT")));
    }

}
