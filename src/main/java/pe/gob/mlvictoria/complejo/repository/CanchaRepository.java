package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaRequest;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaResponse;
import pe.gob.mlvictoria.complejo.mapper.CanchaMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class CanchaRepository {

    @Autowired
    final CanchaMapper canchaMapper;

    public List<CanchaResponse> ListarCancha(CanchaRequest dto) {
        return canchaMapper.listarCancha(dto);
    }
}
