package com.alten.shop.utils.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;




@Configuration
public class ConfigSwagger {

    @Value("${server.port}")
    private String serverPort;
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OpenAPI spécification - Alten Shop")
                        .version("0.0.1")
                        .description("Open Documentation for CRUD Products")
                )
                .addServersItem( new Server()
                        .description("local environment")
                        .url("http://localhost:" + this.serverPort)
                );
    }
}
