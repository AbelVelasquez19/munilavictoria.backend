package pe.gob.mlvictoria.utility;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "reniec")
public class ReniecConfig {
    private Url url;
    private String ruc;

    @Data
    public static class Url {
        private String consulta;
        private String actualizar;
        private String sigmunDev;
        private String sigmunProd;
    }

}
