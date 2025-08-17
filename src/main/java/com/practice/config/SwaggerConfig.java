package com.practice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http://localhost:8080"
                )
        })
public class SwaggerConfig {

}