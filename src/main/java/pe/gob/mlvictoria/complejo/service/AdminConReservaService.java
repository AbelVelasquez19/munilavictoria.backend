package pe.gob.mlvictoria.complejo.service;

import jakarta.servlet.http.HttpServletRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.*;

import java.util.List;

public interface AdminConReservaService {
    List<AdminConReservaResponse> listarReservasDeportivo(AdminConReservaRequest dto);
    HorarioMasivaResponsive generarHorarioMasiva(HorarioMasivaRequest dto, HttpServletRequest req);
    CambiarEstadoHorarioResponse cambiarEstadoHorario(CambiarEstadoHorarioRequest dto,HttpServletRequest req);
}
