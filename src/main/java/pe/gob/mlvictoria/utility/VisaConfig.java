package pe.gob.mlvictoria.utility;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "visa")
public class VisaConfig {
    private String merchantId;
    private String merchantIdComplejo;
    private String user;
    private String pwd;
    private Url url;
    private Defaults defaults;

    @Data
    public static class Url {
        private String security;
        private String session;
        private String js;
        private String authorization;
        private String urlFrontend;
    }

    @Data
    public static class Defaults {
        private String partnerIdCode;
        private String serviceLocationCityName;
        private String serviceLocationCountrySubdivisionCode;
        private String serviceLocationCountryCode;
        private String serviceLocationPostalCode;
    }
}
