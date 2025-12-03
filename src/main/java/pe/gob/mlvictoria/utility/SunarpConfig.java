package pe.gob.mlvictoria.utility;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sunarp")
public class SunarpConfig {
    private Url url;
    private String usuario;
    private String clave;

    @Data
    public static class Url {
        private String tsirsarp;
    }
}
