package pe.gob.mlvictoria.complejo.controlador;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminGeneRequest;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminResponse;
import pe.gob.mlvictoria.complejo.service.ComAdminService;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;

@RestController
@RequestMapping("/api/complejo-admin")
@RequiredArgsConstructor
@Slf4j
public class ComAdminController {
    private final ComAdminService comAdminService;

    @PostMapping("/registrar-complejo-admin")
    public ResponseEntity<ApiResponse<ComAdminResponse>> registrarComAdmin(@RequestBody ComAdminGeneRequest dto, HttpServletRequest request) {
        ComAdminResponse response = comAdminService.registrarComAdmin(dto, request);
        if(response.getMessage().equals("error") && response.getIdAdministrado()== null) {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            java.time.LocalDateTime.now(),
                            "fail",
                            "Error al registrar administrado",
                            response
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        java.time.LocalDateTime.now(),
                        "success",
                        "Administrado se registrado exitosamente",
                        response
                )
        );
    }
}
