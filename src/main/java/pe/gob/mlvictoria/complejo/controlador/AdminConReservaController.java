package pe.gob.mlvictoria.complejo.controlador;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.complejo.dto.adminreserva.AdminConReservaRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.AdminConReservaResponse;
import pe.gob.mlvictoria.complejo.dto.adminreserva.HorarioMasivaRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.HorarioMasivaResponsive;
import pe.gob.mlvictoria.complejo.service.AdminConReservaService;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/complejo-admin-reserva")
@RequiredArgsConstructor
@Slf4j
public class AdminConReservaController {

    private final AdminConReservaService adminConReservaService;

    @PostMapping("/listar-reservas")
    public ResponseEntity<ApiResponse<List<AdminConReservaResponse>>> listarReservasDeportivos(@RequestBody AdminConReservaRequest request) {
        List<AdminConReservaResponse> response = adminConReservaService.listarReservasDeportivo(request);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Login exitoso",
                        response
                )
        );
    }

    @PostMapping("/generar-horario-masivo")
    public ResponseEntity<ApiResponse<HorarioMasivaResponsive>> generarHorarioMasiva(@RequestBody HorarioMasivaRequest request, HttpServletRequest req) {
        HorarioMasivaResponsive response = adminConReservaService.generarHorarioMasiva(request,req);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Horarios generados exitosamente",
                        response
                )
        );
    }
}
