package pe.gob.mlvictoria.fiscalizacioncontrol.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.ApiResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros.GirosRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros.GirosResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion.InfraccionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion.InfraccionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores.InspectoresRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores.InspectoresResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ListasRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ListasRespondeDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ReporteNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas.CalificaMultaRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas.CalificaMultaResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.numeronotificacion.NumeroNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.numeronotificacion.NumeroNotificacionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.service.CalificaMultaService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sigmun")
@Slf4j
public class CalificaMultaController {

    @Autowired
    private CalificaMultaService calificaMultaService;

    @Value("${app.upload-dir}")
    private String baseDir;

    @PostMapping("/listar-califica-multa")
    public ResponseEntity<ApiResponse<List<CalificaMultaResponseDTO>>> ListarCalificaMulta(@RequestBody CalificaMultaRequestDTO dto){
        List<CalificaMultaResponseDTO> resultado = calificaMultaService.listarCalificaMulta(dto);
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
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Consulta  exitosa",
                        resultado
                )
        );

    }

    @PostMapping("/listar-vias")
    public ResponseEntity<ApiResponse<List<ViasResponseDTO>>> listarVias(@RequestBody ViasRequestDTO dto){
        List<ViasResponseDTO> resultado = calificaMultaService.listarVias(dto);
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
                r.getFACODVIA()==null && r.getFANOMVIA()==null
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

    @PostMapping("/total-vias")
    public ResponseEntity<ApiResponse<List<ViasResponseDTO>>> totalVias(@RequestBody ViasRequestDTO dto){
        List<ViasResponseDTO> resultado = calificaMultaService.listarVias(dto);
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

    @PostMapping("/listar-giros")
    public ResponseEntity<ApiResponse<List<GirosResponseDTO>>> listarGiros(@RequestBody GirosRequestDTO dto){
        List<GirosResponseDTO> resultado = calificaMultaService.listarGiros(dto);
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
                r.getFACODGIRO()==null && r.getFADESGIRO()==null
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

    @PostMapping("/total-giros")
    public ResponseEntity<ApiResponse<List<GirosResponseDTO>>> totalGiros(@RequestBody GirosRequestDTO dto){
        List<GirosResponseDTO> resultado = calificaMultaService.listarGiros(dto);
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

    @PostMapping("/listar-inspectores")
    public ResponseEntity<ApiResponse<List<InspectoresResponseDTO>>> listarInspector(@RequestBody InspectoresRequestDTO dto){
        List<InspectoresResponseDTO> resultado = calificaMultaService.listarInspector(dto);
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
                r.getFACODINSPE()==null && r.getFANOMINSPE()==null
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

    @PostMapping("/total-inspectores")
    public ResponseEntity<ApiResponse<List<InspectoresResponseDTO>>> totalInspector(@RequestBody InspectoresRequestDTO dto){
        List<InspectoresResponseDTO> resultado = calificaMultaService.listarInspector(dto);
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

    @PostMapping("/listar-distrito")
    public ResponseEntity<ApiResponse<List<ListasRespondeDTO>>> listarDistrito(@RequestBody ListasRequestDTO dto){
        List<ListasRespondeDTO> resultado = calificaMultaService.listas(dto);
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
                r.getFACODDISTR()==null
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

    @PostMapping("/listar-infraccion")
    public ResponseEntity<ApiResponse<List<InfraccionResponseDTO>>> listarInfraccion(@RequestBody InfraccionRequestDTO dto){
        List<InfraccionResponseDTO> resultado = calificaMultaService.listasInfraccion(dto);
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
                r.getFACODMULTA()==null
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

    @PostMapping("/total-infraccion")
    public ResponseEntity<ApiResponse<List<InfraccionResponseDTO>>> totalInfraccion(@RequestBody InfraccionRequestDTO dto){
        List<InfraccionResponseDTO> resultado = calificaMultaService.listasInfraccion(dto);
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

    private String extractClientIp(HttpServletRequest request) {
        String[] headers = {"X-Forwarded-For","X-Real-IP","CF-Connecting-IP","True-Client-IP","Forwarded"};
        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    @PostMapping(value = "/registrar-notificacion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<RegistrarNotificacionResponseDTO>> registrarNotificacion(
            @RequestPart("payload") RegistrarNotificacionRequestDTO dto,
            @RequestPart(value = "fotoFrontal", required = false) MultipartFile fotoFrontal,
            @RequestPart(value = "fotoPlaca", required = false) MultipartFile fotoPlaca,
            @RequestPart(value = "video", required = false) MultipartFile video,
            HttpServletRequest req
    ) {
        try {
            // 1. Crear carpetas
            Path dirFrontal = Paths.get(baseDir, "fotos", "frontal");
            Path dirPlaca = Paths.get(baseDir, "fotos", "placa");
            Path dirVideos = Paths.get(baseDir, "videos");

            Files.createDirectories(dirFrontal);
            Files.createDirectories(dirPlaca);
            Files.createDirectories(dirVideos);

            //Foto frontal
            if (fotoFrontal != null && !fotoFrontal.isEmpty()) {
                String nombre = fotoFrontal.getOriginalFilename();
                Path destino = dirFrontal.resolve(nombre);

                if (Files.exists(destino)) {
                    Files.delete(destino);
                }

                fotoFrontal.transferTo(destino);
                dto.setFotoFrontal("fotos/frontal/" + nombre);
            }

            //Foto placa
            if (fotoPlaca != null && !fotoPlaca.isEmpty()) {
                String nombre = fotoPlaca.getOriginalFilename();
                Path destino = dirPlaca.resolve(nombre);

                if (Files.exists(destino)) {
                    Files.delete(destino);
                }

                fotoPlaca.transferTo(destino);
                dto.setFotoPlaca("fotos/placa/" + nombre);
            }

            //Video
            if (video != null && !video.isEmpty()) {
                String nombre = video.getOriginalFilename();
                Path destino = dirVideos.resolve(nombre);

                if (Files.exists(destino)) {
                    Files.delete(destino);
                }

                video.transferTo(destino);
                dto.setVideo("videos/" + nombre);
            }

            dto.setFAESTACION(extractClientIp(req));

            // 3. Lógica normal de registro
            RegistrarNotificacionResponseDTO resultado = calificaMultaService.registrarNotificacion(dto);
            if (resultado == null) {
                return ResponseEntity.status(500)
                        .body(new ApiResponse<>(LocalDateTime.now(), "fail", "Error al registrar notificación", null));
            }

            return ResponseEntity.ok(
                    new ApiResponse<>(LocalDateTime.now(), "success", "Notificación registrada correctamente", resultado)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(LocalDateTime.now(), "fail", "Error guardando archivos: " + e.getMessage(), null)
            );
        }
    }

    @PostMapping("/numero-notificacion")
    public ResponseEntity<ApiResponse<NumeroNotificacionResponseDTO>> numeroNotificacion(@RequestBody NumeroNotificacionRequestDTO dto) {

        NumeroNotificacionResponseDTO resultado = calificaMultaService.numeroNotificacion(dto);

        if (resultado == null) {
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "Ocurrió un error al registrar la notificación",
                            null
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Proceso ejecutado correctamente",
                        resultado
                )
        );
    }

    @PostMapping("/notificaciones/imprimir")
    public ResponseEntity<?> imprimirReporte(@RequestBody ReporteNotificacionRequestDTO dto) {
       try {
           byte[] pdf = calificaMultaService.imprimirNotificacionCargo(dto);
           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_PDF);
           headers.setContentDisposition(ContentDisposition
                   .inline()
                   .filename("notificaciones.pdf")
                   .build());

           return ResponseEntity.ok().headers(headers).body(pdf);
       } catch (IllegalArgumentException e) {
           return ResponseEntity
                   .badRequest()
                   .body(Map.of("status", "warning", "message", e.getMessage()));
       } catch (Exception e) {
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(Map.of("status", "error", "message", "Ocurrió un error inesperado: " + e.getMessage()));
       }
    }

}
