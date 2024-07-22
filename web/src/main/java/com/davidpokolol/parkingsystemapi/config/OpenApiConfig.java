package com.davidpokolol.parkingsystemapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "David Pokol",
                        email = "davidpokolol@gmail.com"
                ),
                description =
                        "This API designed to handle Vehicles, Parking Garages, and Parking Records.",
                title = "Parking System API",
                version = "v1.0"

        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9000"
                )
        }
)
public class OpenApiConfig {
}
