package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminRequest;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminResponse;
import pe.gob.mlvictoria.complejo.mapper.AdministradoMapper;

@Repository
@AllArgsConstructor
public class ComAdminRepository {

    @Autowired
    final AdministradoMapper administradoMapper;

    public ComAdminResponse registrarComAdministrado(ComAdminRequest dto) {
        return administradoMapper.registrarComAdministrado(dto);
    }
}
