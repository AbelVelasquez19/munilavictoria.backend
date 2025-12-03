package pe.gob.mlvictoria.complejo.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.complejo.dto.reserva.*;
import pe.gob.mlvictoria.complejo.service.ReservaService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reserva")
@RequiredArgsConstructor
@Slf4j
public class ReservaController {
    private final ReservaService reservaService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/registrar-reserva")
    public ResponseEntity<ReservaRegistrarResponse> registrarReserva(@RequestBody ReservaRegistrarRequest dto, HttpServletRequest req) {
        try {
            ReservaRegistrarResponse response = reservaService.registrarReserva(dto, req);
            return ResponseEntity.ok(response);
        }catch (BusinessException ex) {
            ReservaRegistrarResponse error = new ReservaRegistrarResponse();
            error.setStatus(0);
            error.setMessage(ex.getMessage());
            error.setIdReserva(0);
            return ResponseEntity.status(200).body(error);
        }
    }

    @PostMapping("/cancelar-reserva")
    public ReservaCancelarResponse cancelarReserva(@RequestBody ReservaCancelarRequest dto) {
        return reservaService.cancelarReserva(dto);
    }

    @PostMapping("/detalle-reserva")
    public ResponseEntity<ReservaResultadoResponse> detalleReserva(@RequestBody ReservaDetalleRequest dto) {
        ReservaDetalleResponse raw = reservaService.reservaDetalle(dto);
        List<ReservaHistorialResponse> detalles = new ArrayList<>();
        try {
            if (raw.getDetallesJson() != null) {
                detalles = objectMapper.readValue(
                        raw.getDetallesJson(),
                        new TypeReference<List<ReservaHistorialResponse>>() {}
                );
            }
        } catch (Exception e) {
            log.error("Error parseando JSON de historial → {}", e.getMessage());
        }

        ReservaResultadoResponse respuesta = new ReservaResultadoResponse(
                raw.getStatus(),
                raw.getMessage(),
                raw.getIdReserva(),
                raw.getCodigo(),
                raw.getNombreAdministrado(),
                raw.getTipoDocumento(),
                raw.getNumeroDocumento(),
                raw.getCelular(),
                raw.getCorreo(),
                raw.getComplejo(),
                raw.getCancha(),
                raw.getFechaReserva(),
                raw.getCantidadHoras(),
                raw.getMontoTotal(),
                raw.getTarifaHora(),
                detalles
        );

        return  ResponseEntity.ok(respuesta);
    }

    @GetMapping("/liberar-reserva-expirada")
    public ReservaHrsLibResponse liberarReservaExpirada() {
        return reservaService.liberarReservaExpirada();
    }
}
