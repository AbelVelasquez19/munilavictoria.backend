package pe.gob.mlvictoria.complejo.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.complejo.dto.pago.*;
import pe.gob.mlvictoria.complejo.service.PagoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/complejo-pago")
@RequiredArgsConstructor
@Slf4j
public class ComPagoController {
    private final PagoService pagoService;
    private final ObjectMapper objectMapper;

    @PostMapping("/enviar-ticket-aprobado")
    public ResponseEntity<TicketAproResulResponse> enviarTicketAprobado(@RequestBody TicketAprobadoRequest dto) {
        TicketAprobadoResponse raw = pagoService.ticketAprobado(dto);

        List<TicketDetalleResponse> detalles = new ArrayList<>();

        try {
            if (raw.getDetallesJson() != null) {
                detalles = objectMapper.readValue(
                        raw.getDetallesJson(),
                        new TypeReference<List<TicketDetalleResponse>>() {}
                );
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        TicketAproResulResponse respuesta = new TicketAproResulResponse(
                raw.getStatus(),
                raw.getMessage(),
                raw.getIdReserva(),
                raw.getNombreAdministrado(),
                raw.getTipoDocumento(),
                raw.getNumeroDocumento(),
                raw.getCelular(),
                raw.getCorreo(),
                raw.getCodigoContribuyente(),
                raw.getComplejo(),
                raw.getCancha(),
                raw.getFechaReserva(),
                raw.getCantidadHoras(),
                raw.getMontoTotal(),
                raw.getPurchaseNumber(),
                raw.getEstadoNiubiz(),
                raw.getTarifaHora(),
                detalles,
                raw.getAuthRaw()

        );
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/enviar-ticket-rechazado")
    public ResponseEntity<BuscarTokenResponse> enviarTicketRechazado(@RequestBody BuscarTokenRequest dto){
        BuscarTokenResponse respuesta = pagoService.buscarTokenPago(dto);

        if (respuesta.getAuthRaw() != null && respuesta.getAuthRaw() instanceof String authRawString) {
            try {
                AuthRawResponse auth = objectMapper.readValue(authRawString, AuthRawResponse.class);
                respuesta.setAuthRaw(auth);

            } catch (Exception e) {
                throw new RuntimeException("Error al convertir authRaw en objeto AuthRaw", e);
            }
        }
        return ResponseEntity.ok(respuesta);
    }
}
