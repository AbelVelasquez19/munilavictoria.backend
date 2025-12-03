package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioRequest;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioResponse;
import pe.gob.mlvictoria.complejo.mapper.HorarioMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class HorarioRepository {
    @Autowired
    final HorarioMapper horarioMapper;

    public List<HorarioResponse> listarHorariosCancha(HorarioRequest dto) {
        return horarioMapper.listarHorariosCancha(dto);
    }
}
