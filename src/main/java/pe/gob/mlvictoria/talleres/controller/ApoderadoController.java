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
import pe.gob.mlvictoria.talleres.dto.apoderado.ApoderadoResponse;
import pe.gob.mlvictoria.talleres.dto.apoderado.GeneralRequest;
import pe.gob.mlvictoria.talleres.service.ApoderadoService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
@Slf4j
public class ApoderadoController {

    private final ApoderadoService apoderadoService;

    @PostMapping("/registrar-apoderado")
    public ResponseEntity<ApiResponse<ApoderadoResponse>> registrar(@RequestBody GeneralRequest dto, HttpServletRequest request) {
        try {
            ApoderadoResponse response = apoderadoService.registrarApoderado(dto, request);
            if (response == null) {
                return ResponseEntity.status(500).body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "Error al registrar apoderado",
                                null
                        )
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "success",
                            "Apoderado registrado exitosamente",
                            response
                    )
            );
        }catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "Error interno del servidor",
                            null
                    )
            );
        }

    }
}
