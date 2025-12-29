package pe.gob.mlvictoria.complejo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.dashboard.DashboardIngresosResponse;
import pe.gob.mlvictoria.complejo.repository.DasboardRepository;
import pe.gob.mlvictoria.complejo.service.DashboardService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    final DasboardRepository dasboardRepository;

    @Override
    public List<DashboardIngresosResponse> dashboardIngresosResponseLis(int opcion, String fechaInicio, String fechaFin) {
        return dasboardRepository.dashboardIngresos(opcion, fechaInicio, fechaFin);
    }
}
