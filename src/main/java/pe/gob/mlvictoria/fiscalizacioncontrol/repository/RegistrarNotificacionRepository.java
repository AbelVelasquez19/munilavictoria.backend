package pe.gob.mlvictoria.fiscalizacioncontrol.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.mapper.RegistrarNotificacionMapper;

@Repository
@AllArgsConstructor
public class RegistrarNotificacionRepository {

    private final RegistrarNotificacionMapper registrarNotificacionMapper;

    public RegistrarNotificacionResponseDTO  registrarNotificacion(RegistrarNotificacionRequestDTO dto){
        return registrarNotificacionMapper.registrarNotificacion(dto);
    }

}
