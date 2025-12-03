package pe.gob.mlvictoria.pagolinea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.config.JwtUtil;
import pe.gob.mlvictoria.pagolinea.dto.ApiResponse;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.service.EstadoCuentaService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/estado-cuenta")
public class EstadoCuentaController {

    @Autowired
    private EstadoCuentaService estadoCuentaService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/listar")
    public ResponseEntity<ApiResponse<List<EstadoCuentaResponseDTO>>> listarEstadoCuenta(@RequestHeader("Authorization") String authHeader, @RequestBody EstadoCuentaRequestDTO requestDTO) {

        // Extraer token del header
        String token = authHeader.replace("Bearer ", "");
        // Obtener el código del contribuyente autenticado
        String codigoContribuyente = jwtUtil.extractUsername(token);
        requestDTO.setCodigo(codigoContribuyente);

        log.info("==> Entrando a listarEstadoCuenta con código: {}", requestDTO.getCodigo());

        List<EstadoCuentaResponseDTO> resultado = estadoCuentaService.listarEstadoCuenta(requestDTO);
        if (resultado == null || resultado.isEmpty()) {
            return ResponseEntity.status(404).body(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "fail",
                        "No se encontraron registros para la consulta.",
                        null
                )
            );
        }
        return ResponseEntity.ok(
            new ApiResponse<>(
                LocalDateTime.now(),
                "success",
                "Consulta de estado de cuenta exitosa",
                resultado
            )
        );
    }

    @PostMapping("/imprimir")
    public ResponseEntity<byte[]> imprimirEstadoCuentaPDF(@RequestHeader("Authorization") String authHeader, @RequestBody ImprimirEstadoCuentaRequestDTO requestDTO) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String codigoContribuyente = jwtUtil.extractUsername(token);
            requestDTO.setCodigo(codigoContribuyente);

            byte[] pdf = estadoCuentaService.imprimirEstadoCuenta(requestDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition
                    .inline()
                    .filename("estado-cuenta.pdf")
                    .build());

            return ResponseEntity.ok().headers(headers).body(pdf);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
