package pe.gob.mlvictoria.talleres.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;
import pe.gob.mlvictoria.talleres.dto.usuario.*;
import pe.gob.mlvictoria.talleres.service.UsuarioTallerService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
@Slf4j
public class UsuarioTallerController {

    private final UsuarioTallerService usuarioTallerService;

    @PostMapping("/listar-usuario-taller")
    public ResponseEntity<ApiResponse<List<UsuarioTallerResponse>>> enviarEnlaceRecuperacion(@RequestBody UsuarioTallerRequest dto) {
        List<UsuarioTallerResponse> response = usuarioTallerService.listarUsuarioTaller(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Enlace de recuperación enviado exitosamente",
                        response
                )
        );
    }

    @PostMapping("/registrar-usuario-taller")
    public ResponseEntity<ApiResponse<RegisUsuarioTallerResponse>> registrarUsuarioTaller(@RequestBody UsuarioTallerGeneralRequest dto, HttpServletRequest req) {
        RegisUsuarioTallerResponse response = usuarioTallerService.registrarUsuarioTaller(dto, req);
        if(response.getStatus().equals(0)){
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            response.getMessage(),
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        response.getMessage(),
                        response
                )
        );
    }

    @PostMapping("/obtener-usuario-taller")
    public ResponseEntity<ApiResponse<ObtUsuarioTallerResponse>> obtenerUsuarioTaller(@RequestBody ObtUsuarioTallerRequest dto){
        ObtUsuarioTallerResponse response = usuarioTallerService.obtenerUsuarioTaller(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Usuario obtenido exitosamente",
                        response
                )
        );
    }

    @PostMapping("/actualizar-usuario-taller")
    public ResponseEntity<ApiResponse<ActUsuarioTallerResponse>> actualizarUsuarioTaller(@RequestBody ActUsuarioTallerRequest dto) {
        ActUsuarioTallerResponse response = usuarioTallerService.actualizarUsuarioTaller(dto);
        if (response.getStatus().equals(0)) {
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            response.getMessage(),
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        response.getMessage(),
                        response
                )
        );
    }
}
