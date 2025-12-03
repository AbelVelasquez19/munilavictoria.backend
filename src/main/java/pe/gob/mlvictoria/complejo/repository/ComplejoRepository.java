package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoRequest;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoResponse;
import pe.gob.mlvictoria.complejo.mapper.ComplejoMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class ComplejoRepository {

    @Autowired
    final ComplejoMapper complejoMapper;

    public List<ComplejoResponse> listarComplejo(ComplejoRequest dto) {
        return complejoMapper.listarComplejo(dto);
    }
}
