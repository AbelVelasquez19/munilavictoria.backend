package pe.gob.mlvictoria.complejo.controlador;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaRequest;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaResponse;
import pe.gob.mlvictoria.complejo.service.CanchaService;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/canchas")
@RequiredArgsConstructor
@Slf4j
public class CanchaController {
    private final CanchaService canchaService;

    @PostMapping("/listar-canchas")
    public ResponseEntity<ApiResponse<List<CanchaResponse>>> listarCanchas(@RequestBody CanchaRequest dto) {
        List<CanchaResponse> response = canchaService.ListarCancha(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        java.time.LocalDateTime.now(),
                        "success",
                        "Listado de canchas exitoso",
                        response
                )
        );
    }
}
