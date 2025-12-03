package pe.gob.mlvictoria.utility;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "complejo")
public class ComplejoConfig {
    private Url url;

    @Data
    public static class Url {
        private String urlFrontend;
    }
}
