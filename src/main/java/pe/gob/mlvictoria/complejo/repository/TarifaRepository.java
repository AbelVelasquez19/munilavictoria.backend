package pe.gob.mlvictoria.complejo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaRequest;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaResponse;
import pe.gob.mlvictoria.complejo.mapper.TarifaMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TarifaRepository {

    private final TarifaMapper tarifaMapper;

    public CalcularTarifaResponse calcularTarifa(CalcularTarifaRequest dto) {
        return tarifaMapper.calcularTarifa(dto);
    }
}
