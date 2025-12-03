package pe.gob.mlvictoria.talleres.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.talleres.dto.restablecer.RestablecerRequest;
import pe.gob.mlvictoria.talleres.dto.restablecer.RestablecerResponse;
import pe.gob.mlvictoria.talleres.mapper.RestablecerMapper;

@Repository
@AllArgsConstructor
public class RestablecerRepository {
    @Autowired
    final RestablecerMapper restablecerMapper;

    public RestablecerResponse restablecerPassword(RestablecerRequest dto) {
        return restablecerMapper.restablecerPassword(dto);
    }
}
