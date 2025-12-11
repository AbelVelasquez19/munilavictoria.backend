package pe.gob.mlvictoria.pagolinea.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailTemplateService {
    public String buildTemplate(String templateName, Map<String, String> variables) {
        try {
            InputStream is = getClass().getResourceAsStream("/templates/" + templateName);
            if (is == null) {
                throw new IllegalArgumentException("Plantilla no encontrada: " + templateName);
            }

            String template = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            for (Map.Entry<String, String> entry : variables.entrySet()) {
                template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
            return template;

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la plantilla de correo: " + templateName, e);
        }
    }

}
