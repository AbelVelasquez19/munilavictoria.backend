package pe.gob.mlvictoria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pe.gob.mlvictoria.exception.cofig.JwtAuthEntryPoint;
import pe.gob.mlvictoria.utility.*;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final VisaConfig visaConfig;
    private final ReniecConfig reniecConfig;
    private final SigmunConfig sigmunConfig;
    private final TalleresConfig talleresConfig;
    private final ComplejoConfig complejoConfig;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter,
                            JwtAuthEntryPoint jwtAuthEntryPoint,
                             VisaConfig visaConfig,
                             ReniecConfig reniecConfig,
                             SigmunConfig sigmunConfig,
                             TalleresConfig talleresConfig,
                             ComplejoConfig complejoConfig
    ) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.visaConfig = visaConfig;
        this.reniecConfig = reniecConfig;
        this.sigmunConfig = sigmunConfig;
        this.talleresConfig = talleresConfig;
        this.complejoConfig = complejoConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/contribuyente/login",
                                "/api/contribuyente/recuperar-contrasena",
                                "/api/contribuyente/enviar-codigo-recuperacion",
                                "/api/contribuyente/enviar-nueva-contrasena",
                                "/api/pago-linia/autorizacion-transaccion",
                                "/api/reniec/consultar",
                                "/api/sunarp/consultar",
                                "/api/sigmun/login",
                                "/api/sigmun/notificaciones/imprimir",
                                "/api/sunat/consultar/*",

                                "/api/talleres/registrar-apoderado",
                                "/api/talleres/enviar-enlace-recuperacion",
                                "/api/talleres/validar-token",
                                "/api/talleres/actualizar-password",
                                "/api/talleres/login-talleres",

                                "/api/complejo/listar-complejo",
                                "/api/canchas/listar-canchas",
                                "/api/complejo-admin/registrar-complejo-admin",
                                "/api/horarios/listar-horarios-canchas",
                                "/api/tarifa/calcular",
                                "/api/reserva/registrar-reserva",
                                "/api/reserva/cancelar-reserva",
                                "/api/reserva/detalle-reserva",
                                "/api/reserva/liberar-reserva-expirada",

                                "/api/niubiz/generate-session-token",
                                "/api/niubiz/response-form",
                                "/api/niubiz/visa-numero-compra",
                                "/api/niubiz/logs-complejo",
                                "/api/complejo-pago/enviar-ticket-aprobado",
                                "/api/complejo-pago/enviar-ticket-rechazado",
                                "/api/complejo-admin/login-complejo"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                                                visaConfig.getUrl().getUrlFrontend(),
                                                reniecConfig.getUrl().getSigmunDev(),
                                                reniecConfig.getUrl().getSigmunProd(),
                                                sigmunConfig.getUrl().getUrlFrontendDev(),
                                                sigmunConfig.getUrl().getUrlFrontendProd(),
                                                sigmunConfig.getUrl().getUrlBackendDev(),
                                                talleresConfig.getUrl().getUrlFrontend(),
                                                complejoConfig.getUrl().getUrlFrontend()
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
