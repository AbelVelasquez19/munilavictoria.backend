package pe.gob.mlvictoria.complejo.service;

import jakarta.servlet.http.HttpServletRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.AdminConReservaRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.AdminConReservaResponse;
import pe.gob.mlvictoria.complejo.dto.adminreserva.HorarioMasivaRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.HorarioMasivaResponsive;

import java.util.List;

public interface AdminConReservaService {
    List<AdminConReservaResponse> listarReservasDeportivo(AdminConReservaRequest dto);
    HorarioMasivaResponsive generarHorarioMasiva(HorarioMasivaRequest dto, HttpServletRequest req);
}
