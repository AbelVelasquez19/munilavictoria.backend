package pe.gob.mlvictoria.complejo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.dashboard.DashboardIngresosResponse;
import pe.gob.mlvictoria.complejo.mapper.DashboardMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DasboardRepository {
    private final DashboardMapper dasboardMapper;

    public List<DashboardIngresosResponse> dashboardIngresos(int opcion, String fechaInicio, String fechaFin) {
        return dasboardMapper.dashboardIngresos(opcion, fechaInicio,  fechaFin);
    }
}
