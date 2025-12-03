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
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaRequest;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaResponse;
import pe.gob.mlvictoria.complejo.dto.tarifa.TarifaDetalleResponse;
import pe.gob.mlvictoria.complejo.dto.tarifa.TarifaResultadoResponse;
import pe.gob.mlvictoria.complejo.service.TarifaService;

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
}
