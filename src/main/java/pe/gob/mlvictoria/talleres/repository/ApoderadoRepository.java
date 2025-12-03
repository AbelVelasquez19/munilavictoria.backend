package pe.gob.mlvictoria.talleres.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.talleres.dto.apoderado.ApoderadoRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.ApoderadoResponse;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteResponse;
import pe.gob.mlvictoria.talleres.mapper.ApoderadoMapper;

@Repository
@AllArgsConstructor
public class ApoderadoRepository {
    @Autowired
    final ApoderadoMapper apoderadoMapper;

    public ContribuyenteResponse registrarContribuyente(ContribuyenteRequest dto) {
        return apoderadoMapper.registrarContribuyente(dto);
    }

    public ApoderadoResponse registrarApoderado(ApoderadoRequest dto) {
        return apoderadoMapper.registrarApoderado(dto);
    }
}
