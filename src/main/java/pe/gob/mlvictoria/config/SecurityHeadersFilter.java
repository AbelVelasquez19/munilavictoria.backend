package pe.gob.mlvictoria.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityHeadersFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        // Prevención de Clickjacking
        res.setHeader("X-Frame-Options", "DENY");

        // Prevención de interpretación errónea de tipos MIME
        res.setHeader("X-Content-Type-Options", "nosniff");

        // Control de cacheo para evitar exposición de datos sensibles
        res.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setHeader("Expires", "0");

        // Opcional: añadir Content-Security-Policy básico
        res.setHeader("Content-Security-Policy", "default-src 'self'; frame-ancestors 'none';");

        chain.doFilter(request, response);
    }
}
