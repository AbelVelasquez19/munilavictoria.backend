package pe.gob.mlvictoria.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        var httpClient = org.apache.hc.client5.http.impl.classic.HttpClients.createDefault();
        var factory = new org.springframework.http.client.HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(5000); // 5s para conectar
        factory.setConnectionRequestTimeout(5000); // 5s para obtener conexión
        factory.setReadTimeout(15000); // 15s para leer respuesta

        RestTemplate rt = new RestTemplate(factory);
        rt.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return rt;
    }
}
