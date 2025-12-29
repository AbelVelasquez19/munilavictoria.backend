package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.dashboard.DashboardIngresosResponse;

import java.util.List;

public interface DashboardService {
    List<DashboardIngresosResponse> dashboardIngresosResponseLis(int opcion, String fechaInicio, String fechaFin);
}
