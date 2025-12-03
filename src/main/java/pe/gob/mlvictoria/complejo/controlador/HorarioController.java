package pe.gob.mlvictoria.complejo.controlador;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioRequest;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioResponse;
import pe.gob.mlvictoria.complejo.service.HorarioService;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
@Slf4j
public class HorarioController {
    private final HorarioService horarioService;

    @PostMapping("/listar-horarios-canchas")
    public ResponseEntity<ApiResponse<List<HorarioResponse>>> listarHorariosCanchas(@RequestBody HorarioRequest dto){
        List<HorarioResponse> response = horarioService.listarHorariosCancha(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        java.time.LocalDateTime.now(),
                        "success",
                        "Listado de horarios de canchas exitoso",
                        response
                )
        );
    }
}
