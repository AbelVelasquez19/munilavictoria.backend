package pe.gob.mlvictoria.accesosigmun.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.accesosigmun.dto.RegistrarAccesoRequestDTO;
import pe.gob.mlvictoria.accesosigmun.mapper.sistemaaudi.RegistrarAccesoMapper;

@Repository
@RequiredArgsConstructor
public class RegistrarAccesoRepository {

    @Autowired
    private final RegistrarAccesoMapper mapper;

    public int RegistrarAcceso(RegistrarAccesoRequestDTO dto){
        return mapper.registrarAcceso(dto);
    }
}
