package pe.gob.mlvictoria.consultasunarp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.consultasunarp.dto.ApiResponse;
import pe.gob.mlvictoria.consultasunarp.dto.SunarpTsirsarpRequestDTO;
import pe.gob.mlvictoria.consultasunarp.dto.SunarpTsirsarpResponseDTO;
import pe.gob.mlvictoria.consultasunarp.service.SunarpTsirsarpService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/sunarp")
@AllArgsConstructor
public class SunarpConsultaController {

    private final SunarpTsirsarpService sunarpService;

    @PostMapping("/consultar")
    public ResponseEntity<ApiResponse<List<SunarpTsirsarpResponseDTO>>> consultar(
            @RequestBody SunarpTsirsarpRequestDTO dto) {

        ApiResponse<List<SunarpTsirsarpResponseDTO>> response = sunarpService.consultarSunarp(dto);

        return ResponseEntity.ok(response);
    }
}
