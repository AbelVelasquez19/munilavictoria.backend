package pe.gob.mlvictoria.complejo.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioRequest;
import pe.gob.mlvictoria.complejo.dto.horario.HorarioResponse;
import pe.gob.mlvictoria.complejo.repository.HorarioRepository;
import pe.gob.mlvictoria.complejo.service.HorarioService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;

    @Override
    public List<HorarioResponse> listarHorariosCancha(HorarioRequest dto) {
        if(dto.getIdCancha() == null){
            throw new BusinessException("El id cancha no puede ser nulo");
        }
        if(dto.getFecha() == null){
            throw new BusinessException("Fecha no puede ser nulo");
        }
        List<HorarioResponse> response = horarioRepository.listarHorariosCancha(dto);
        if(response == null) {
            throw new BusinessException("No se encontraron horarios para la cancha proporcionada");
        }
        log.info(response.toString());
        return response;
    }
}
