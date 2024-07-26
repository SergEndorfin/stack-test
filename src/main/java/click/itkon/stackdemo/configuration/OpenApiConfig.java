package click.itkon.stackdemo.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Stack REST API",
                version = "1.0",
                description = "API documentation for Stack"
        ),
        servers = @Server(url = "http://localhost:8080", description = "Local server"))
public class OpenApiConfig {
}
