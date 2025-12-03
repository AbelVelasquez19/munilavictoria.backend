package pe.gob.mlvictoria.talleres.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;
import pe.gob.mlvictoria.talleres.dto.restablecer.ActPassRequest;
import pe.gob.mlvictoria.talleres.dto.restablecer.BusUsuCorreoRequest;
import pe.gob.mlvictoria.talleres.dto.restablecer.RestablecerResponse;
import pe.gob.mlvictoria.talleres.dto.restablecer.TokenRequest;
import pe.gob.mlvictoria.talleres.service.RestablecerService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
@Slf4j
public class RestablecerController {

    private final RestablecerService restablecerService;

    @PostMapping("/enviar-enlace-recuperacion")
    public ResponseEntity<ApiResponse<RestablecerResponse>> enviarEnlaceRecuperacion(@RequestBody BusUsuCorreoRequest dto) {
            RestablecerResponse response = restablecerService.buscarUsuarioCorreo(dto);
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "success",
                            "Enlace de recuperación enviado exitosamente",
                            response
                    )
            );
    }

    @PostMapping("/validar-token")
    public ResponseEntity<ApiResponse<RestablecerResponse>> validarToken(@RequestBody TokenRequest dto){
        RestablecerResponse response = restablecerService.validarToken(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Token validado exitosamente",
                        response
                )
        );
    }

    @PostMapping("/actualizar-password")
    public ResponseEntity<ApiResponse<RestablecerResponse>> actualizarPassword(@RequestBody ActPassRequest dto) {
        RestablecerResponse response = restablecerService.actualizarPassword(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Contraseña actualizada correctamente",
                        response
                )
        );
    }
}
