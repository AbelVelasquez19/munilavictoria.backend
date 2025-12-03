package pe.gob.mlvictoria.fiscalizacioncontrol.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.ApiResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.distrito.DistritoResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado.CalificaContriRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado.CalificaContriResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarcontribuyente.ContribuyenteRequest;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarcontribuyente.ContribuyenteResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.tipourbanizacion.TipoUrbaniazacionResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.tipovia.TipoViaResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasContriRequest;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasContriResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasContriTotalResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.service.CalificaContriService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sigmun")
@Slf4j
public class CalificaContriController {

    @Autowired
    private CalificaContriService calificaContriService;

    @PostMapping("/listar-califica")
    public ResponseEntity<ApiResponse<List<CalificaContriResponseDTO>>> listaCalificaContribuyente(@RequestBody  CalificaContriRequestDTO dto){
        List<CalificaContriResponseDTO> resultado = calificaContriService.listaCalificaContribuyente(dto);
        if(resultado==null || resultado.isEmpty()){
            return ResponseEntity.status(404).body(
              new ApiResponse<>(
                      LocalDateTime.now(),
                      "fail",
                      "No se encontro registros para la consulta",
                      null
              )
            );
        }
        boolean allNull = resultado.stream().allMatch(r -> r == null || (
            r.getCodigo()==null && r.getNombres()==null
        ));

        if(allNull){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }

        return ResponseEntity.ok(
          new ApiResponse<>(
                  LocalDateTime.now(),
                  "success",
                  "Consulta  exitosa",
                  resultado
          )
        );

    }

    @PostMapping("/total-califica")
    public ResponseEntity<ApiResponse<List<CalificaContriResponseDTO>>> totalCalificaContribuyente(@RequestBody  CalificaContriRequestDTO dto){
        List<CalificaContriResponseDTO> resultado = calificaContriService.listaCalificaContribuyente(dto);
        if(resultado==null || resultado.isEmpty()){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        boolean allNull = resultado.stream().allMatch(r -> r == null || (
                r.getTotal()==null
        ));

        if(allNull){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta  exitosa",
                        resultado
                )
        );

    }

    @PostMapping("/listar-distrito-contri")
    public ResponseEntity<ApiResponse<List<DistritoResponse>>> listarDistrito(@RequestBody String query) {
        List<DistritoResponse> resultado = calificaContriService.listarDistrito(query);
        if (resultado == null || resultado.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta exitosa",
                        resultado
                )
        );
    }

    @PostMapping("/listar-tipo-urbanizacion")
    public ResponseEntity<ApiResponse<List<TipoUrbaniazacionResponse>>> listarTipoUrbanizacion(@RequestBody String query) {
        List<TipoUrbaniazacionResponse> resultado = calificaContriService.listarTipoUrbanizacion(query);
        if (resultado == null || resultado.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta exitosa",
                        resultado
                )
        );
    }

    @PostMapping("/listar-tipo-via")
    public ResponseEntity<ApiResponse<List<TipoViaResponse>>> listarTipoVia(@RequestBody String query) {
        List<TipoViaResponse> resultado = calificaContriService.listarTipoVia(query);
        if (resultado == null || resultado.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta exitosa",
                        resultado
                )
        );
    }

    @PostMapping("/listar-vias-contri")
    public ResponseEntity<ApiResponse<List<ViasContriResponse>>> listaViasContri(@RequestBody ViasContriRequest dto){
        List<ViasContriResponse> resultado = calificaContriService.listarViasContri(dto);
        if(resultado==null || resultado.isEmpty()){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        boolean allNull = resultado.stream().allMatch(r -> r == null || (
                r.getCodVia()==null
        ));

        if(allNull){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta  exitosa",
                        resultado
                )
        );

    }

    @PostMapping("/total-vias-contri")
    public ResponseEntity<ApiResponse<List<ViasContriTotalResponse>>> totalViasContri(@RequestBody  ViasContriRequest dto){
        List<ViasContriTotalResponse> resultado = calificaContriService.listarViasContriTotal(dto);
        if(resultado==null || resultado.isEmpty()){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        boolean allNull = resultado.stream().allMatch(r -> r == null || (
                r.getTotal() == null
        ));

        if(allNull){
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "No se encontro registros para la consulta",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta  exitosa",
                        resultado
                )
        );

    }

    @PostMapping("/registrar-contribuyente")
    public ResponseEntity<ApiResponse<ContribuyenteResponse>> registrarContribuyente(@RequestBody ContribuyenteRequest dto, HttpServletRequest req) {
        try {
            dto.setEstacion(extractClientIp(req));
            if (dto.getDatosFiscales() != null) {
                Map<String, String> df = dto.getDatosFiscales();

                //Asignar dinámicamente a los campos reales del DTO
                dto.setNumero(df.getOrDefault("numero", ""));
                dto.setNumero2(df.getOrDefault("numero2", ""));
                dto.setLetra1(df.getOrDefault("letra1", ""));
                dto.setLetra2(df.getOrDefault("letra2", ""));
                dto.setDepartam(df.getOrDefault("departam", ""));
                dto.setManzana(df.getOrDefault("manzana", ""));
                dto.setLote(df.getOrDefault("lote", ""));
                dto.setNombreEdificio(df.getOrDefault("nombreEdificio", ""));
                dto.setBlock(df.getOrDefault("block", ""));
                dto.setInterior(df.getOrDefault("interior", ""));
                dto.setTienda(df.getOrDefault("tienda", ""));
                dto.setPiso(df.getOrDefault("piso", ""));
                dto.setStand(df.getOrDefault("stand", ""));
                dto.setOficina(df.getOrDefault("oficina", ""));
                dto.setSubMz(df.getOrDefault("subMz", ""));
                dto.setSubLote(df.getOrDefault("subLote", ""));
                dto.setSotano(df.getOrDefault("sotano", ""));
                dto.setMezzan(df.getOrDefault("mezzan", ""));
                dto.setSeccion(df.getOrDefault("seccion", ""));
                dto.setUnidadInmobiliaria(df.getOrDefault("unidadInmobiliaria", ""));
                dto.setEtapa(df.getOrDefault("etapa", ""));
                dto.setSector(df.getOrDefault("sector", ""));
                dto.setPuesto(df.getOrDefault("puesto", ""));
                dto.setLocalComercial(df.getOrDefault("localComercial", ""));
                dto.setReferencia(df.getOrDefault("referencia", ""));
            }

            ContribuyenteResponse response = calificaContriService.registrarContribuyente(dto);
            if (response == null) {
                return ResponseEntity.status(500).body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "Error al registrar contribuyente",
                                null
                        )
                );
            }
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "success",
                            "Contribuyente registrado exitosamente",
                            response
                    )
            );
        }catch (Exception e) {
            log.error("Error al registrar contribuyente: {}", e.getMessage(), e);
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

    private String extractClientIp(HttpServletRequest request) {
        String[] headers = {"X-Forwarded-For","X-Real-IP","CF-Connecting-IP","True-Client-IP","Forwarded"};
        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }



}
