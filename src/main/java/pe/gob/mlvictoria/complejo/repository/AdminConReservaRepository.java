package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.adminreserva.AdminConReservaRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.AdminConReservaResponse;
import pe.gob.mlvictoria.complejo.dto.adminreserva.HorarioMasivaRequest;
import pe.gob.mlvictoria.complejo.dto.adminreserva.HorarioMasivaResponsive;
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
}
