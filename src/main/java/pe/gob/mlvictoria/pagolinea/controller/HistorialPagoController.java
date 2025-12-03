package pe.gob.mlvictoria.pagolinea.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.config.JwtUtil;
import pe.gob.mlvictoria.pagolinea.dto.ApiResponse;
import pe.gob.mlvictoria.pagolinea.dto.historialpago.HistorialPagoResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.service.HistorialPagoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/historial-pago")
@AllArgsConstructor
public class HistorialPagoController {

    @Autowired
    private HistorialPagoService historialPagosService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/annos-recibo-pagados")
    public ResponseEntity<ApiResponse<List<HistorialPagoResponseDTO>>> annosRecibosPagados(@RequestBody DatosContriRequestDTO requestDTO){
        List<HistorialPagoResponseDTO> resultado = historialPagosService.annosRecibosPagados(requestDTO);
        if(resultado==null){
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

    @PostMapping("/imprimir-recibo")
    public ResponseEntity<byte[]> imprimirRecibo(@RequestHeader("Authorization") String authHeader, @RequestBody ImprimirReciboRequestDTO requestDTO) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String codigoContribuyente = jwtUtil.extractUsername(token);
            requestDTO.setCodigo(codigoContribuyente);

            byte[] pdf = historialPagosService.imprimirRecibo(requestDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition
                    .inline()
                    .filename("recibo.pdf")
                    .build());
            return ResponseEntity.ok().headers(headers).body(pdf);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
