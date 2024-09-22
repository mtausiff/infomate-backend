package com.chatbot.infomate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        contact = @io.swagger.v3.oas.annotations.info.Contact(
                name = "Infomate Developer Team",
                email = "contactus@infomate.com",
                url = "https://infomate.herokuapp.com"
        ),
        termsOfService = "Terms of Services",
        version = "v1.0.0",
        title = "Infomate Webservices",
        description = "<b>INFOMATE Application</b> services manage all of chat functionalities, and also include customizations.",
        license = @io.swagger.v3.oas.annotations.info.License(
                name = "Apache 2.0",
                url = "http://springdoc.org"
        )
    ),
    servers = {
        @io.swagger.v3.oas.annotations.servers.Server(
                description = "Test environment",
                url = "http://localhost:8091"
        ),
        @io.swagger.v3.oas.annotations.servers.Server(
                description = "Live environment",
                url = "https://infomate.herokuapp.com"
        )
    }
    /*security = {
        @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                name = "bearer_Auth"
        )
    }*/
)

/*@SecurityScheme(
    name = "bearer_Auth",
    description = "<b>JWT Bearer</b> access token required to access this API. <br/>" +
            "JWT access Token can be obtained by providing correct username and password to '/auth/token' API",
    bearerFormat = "JWT",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER
)*/


@Configuration
public class SwaggerConfig
{
    //swagger configuration done through application.yaml
}