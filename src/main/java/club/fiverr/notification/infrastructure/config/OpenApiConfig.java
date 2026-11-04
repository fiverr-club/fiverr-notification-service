package club.fiverr.notification.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notificationServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FIVERREX. — Notification Service API")
                        .version("1.0.0")
                        .description("FIVERREX Notification Service - Multi-Channel In-App, Email, and Push Notifications")
                        .contact(new Contact().name("FIVERREX Engineering")));
    }
}
