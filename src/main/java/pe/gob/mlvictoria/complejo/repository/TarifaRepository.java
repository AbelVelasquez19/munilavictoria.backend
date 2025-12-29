package pe.gob.mlvictoria.complejo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.tarifa.*;
import pe.gob.mlvictoria.complejo.mapper.TarifaMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TarifaRepository {

    private final TarifaMapper tarifaMapper;

    public CalcularTarifaResponse calcularTarifa(CalcularTarifaRequest dto) {
        return tarifaMapper.calcularTarifa(dto);
    }

    public List<ListarTarrifaResponse> listarTarifa(ListarTarifaRequest dto) {
        return tarifaMapper.listarTarifa(dto);
    }

    public int actualizarEstadoTarifa(Integer idTarifa, Integer estado) {
        return tarifaMapper.actualizarEstadoTarifa(idTarifa, estado);
    }

    public int actualizarTarifa(ActualizarTarifaRequest dto) {
        return tarifaMapper.actualizarTarifa(dto);
    }
}
