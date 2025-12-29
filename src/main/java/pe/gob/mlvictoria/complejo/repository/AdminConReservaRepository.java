package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.adminreserva.*;
import pe.gob.mlvictoria.complejo.mapper.AdminConReservaMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class AdminConReservaRepository {

    @Autowired
    final AdminConReservaMapper adminConReservaMapper;

    public List<AdminConReservaResponse> listarReservasDeportivo(AdminConReservaRequest dto) {
        return adminConReservaMapper.listarReservasDeportivo(dto);
    }

    public HorarioMasivaResponsive generarHorarioMasiva(HorarioMasivaRequest dto) {
        return adminConReservaMapper.generarHorarioMasiva(dto);
    }

    public CambiarEstadoHorarioResponse cambiarEstadoHorario(CambiarEstadoHorarioRequest dto) {
        return adminConReservaMapper.cambiarEstadoHorario(dto);
    }
}
