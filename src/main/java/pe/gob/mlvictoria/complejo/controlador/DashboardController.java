package pe.gob.mlvictoria.complejo.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.complejo.dto.dashboard.DashboardIngresosResponse;
import pe.gob.mlvictoria.complejo.service.DashboardService;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
   private final DashboardService dashboardService;

   @PostMapping("/ingresos-reservas-reportes")
   public ResponseEntity<ApiResponse<List<DashboardIngresosResponse>>> dasboardIngresos(
           @RequestParam("opcion") Integer opcion,
           @RequestParam(required = false) String fechaInicio,
           @RequestParam(required = false) String fechaFin) {
       List<DashboardIngresosResponse> response = dashboardService.dashboardIngresosResponseLis(opcion, fechaInicio, fechaFin);
       return ResponseEntity.ok(
               new ApiResponse<>(
                       LocalDateTime.now(),
                       "success",
                       "Operación realizada con éxito",
                       response
               )
       );
   }
}
