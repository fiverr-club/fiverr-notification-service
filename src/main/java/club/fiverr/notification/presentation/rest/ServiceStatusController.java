package club.fiverr.notification.presentation.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ServiceStatusController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("brand", "FIVERREX");
        body.put("service", "fiverr-notification-service");
        body.put("status", "UP");
        body.put("swaggerUi", "/swagger-ui/index.html");
        body.put("openApiDocs", "/v3/api-docs");
        return ResponseEntity.ok(body);
    }
}
