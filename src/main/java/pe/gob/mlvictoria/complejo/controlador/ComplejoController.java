package pe.gob.mlvictoria.complejo.controlador;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoRequest;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoResponse;
import pe.gob.mlvictoria.complejo.service.ComplejoService;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/complejo")
@RequiredArgsConstructor
@Slf4j
public class ComplejoController {
    private final ComplejoService complejoService;

    @PostMapping("/listar-complejo")
    public ResponseEntity<ApiResponse<List<ComplejoResponse>>> listarComplejo(@RequestBody ComplejoRequest dto) {
        List<ComplejoResponse> response = complejoService.ListarComplejo(dto);
        if (response.isEmpty()) {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            java.time.LocalDateTime.now(),
                            "fail",
                            "No se encontraron complejos",
                            response
                    )
            );
        }
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
