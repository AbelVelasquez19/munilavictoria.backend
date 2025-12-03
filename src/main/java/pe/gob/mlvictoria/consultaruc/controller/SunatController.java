package pe.gob.mlvictoria.consultaruc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.consultaruc.dto.SunatResponseDTO;
import pe.gob.mlvictoria.consultaruc.service.SunatService;
import pe.gob.mlvictoria.pagolinea.dto.ApiResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sunat")
@RequiredArgsConstructor
public class SunatController {
    private final SunatService sunatService;

    @GetMapping("/consultar/{ruc}")
    public ResponseEntity<ApiResponse<SunatResponseDTO>>  consultarRuc(@PathVariable String ruc) {
        if(ruc == null || ruc.length() != 11 || !ruc.matches("\\d+")) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "RUC inválido",
                            null
                    )
            );
        }

        SunatResponseDTO response = sunatService.consultarRuc(ruc);

        if (response == null) {
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "Error al consultar RUC",
                            null
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta exitosa",
                        response
                )
        );
    }
}
