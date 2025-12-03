package pe.gob.mlvictoria.complejo.service;

import org.apache.ibatis.annotations.Mapper;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioRequest;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioResponse;

import java.util.List;

public interface HorarioService {
    List<HorarioResponse> listarHorariosCancha(HorarioRequest dto);
}
