package pe.gob.mlvictoria.complejo.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.complejo.dto.ApiResponse;
import pe.gob.mlvictoria.complejo.dto.tarifa.*;
import pe.gob.mlvictoria.complejo.service.TarifaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tarifa")
@RequiredArgsConstructor
@Slf4j
public class TarifaController {
    private final TarifaService tarifaService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/calcular")
    public ResponseEntity<TarifaResultadoResponse> calcular(@RequestBody CalcularTarifaRequest dto) {
        CalcularTarifaResponse raw = tarifaService.calcularTarifa(dto);

        List<TarifaDetalleResponse> detalles = new ArrayList<>();

        try {
            if (raw.getDetallesJson() != null) {
                detalles = objectMapper.readValue(
                        raw.getDetallesJson(),
                        new TypeReference<List<TarifaDetalleResponse>>() {}
                );
            }
        } catch (Exception e) {
            log.error("Error parseando JSON de detalles → {}", e.getMessage());
        }

        TarifaResultadoResponse respuesta = new TarifaResultadoResponse(
                raw.getStatus(),
                raw.getMessage(),
                raw.getTotalPagar(),
                raw.getHorasSeleccionadas(),
                detalles
        );

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/listar-tarifa")
    public ResponseEntity<ApiResponse<List<ListarTarrifaResponse>>> listar(@RequestBody ListarTarifaRequest dto) {
        List<ListarTarrifaResponse> tarifas = tarifaService.listarTarifa(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta de estado de cuenta exitosa",
                        tarifas
                )
        );
    }

    @PostMapping("/actualizar-estado-tarifa")
    public ResponseEntity<ApiResponse<String>> actualizarEstado(@RequestParam("idTarifa") Integer idTarifa,@RequestParam("estado") Integer estado) {
        int filasAfectadas = tarifaService.actualizarEstadoTarifa(idTarifa, estado);
        String mensaje = filasAfectadas > 0 ? "Estado actualizado correctamente" : "No se pudo actualizar el estado";
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        mensaje,
                        null
                )
        );
    }

    @PostMapping("/actualizar-tarifa")
    public ResponseEntity<ApiResponse<String>> actualizarTarifa(@RequestBody ActualizarTarifaRequest dto) {
        int filasAfectadas = tarifaService.actualizarTarifa(dto);
        String mensaje = filasAfectadas > 0 ? "Tarifa actualizada correctamente" : "No se pudo actualizar la tarifa";
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        mensaje,
                        null
                )
        );
    }
}
