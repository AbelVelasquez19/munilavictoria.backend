package pe.gob.mlvictoria.utility;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    public String decodeRecibos(String json) {
        if (json == null || json.trim().isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        String[] codificados = json.split(",");

        for (String base64 : codificados) {
            if (!base64.trim().isEmpty()) {
                try {
                    String decoded = new String(java.util.Base64.getDecoder().decode(base64.trim()), java.nio.charset.StandardCharsets.UTF_8);
                    sb.append(decoded).append(",");
                } catch (IllegalArgumentException e) {
                    // Si hay un base64 inválido, puedes ignorarlo o registrar error
                    System.err.println("Error decodificando: " + base64);
                }
            }
        }

        // Quitar la última coma
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        // Reemplazar , por *,* y envolver con *
        return "*" + sb.toString().replace(",", "*,*") + "*";
    }

    public String safe(String val) {
        return val == null ? "" : val;
    }

    public String extractClientIp(HttpServletRequest request) {
        String[] headers = {
                "X-Forwarded-For",
                "X-Real-IP",
                "CF-Connecting-IP",
                "True-Client-IP",
                "Forwarded"
        };

        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }

}
