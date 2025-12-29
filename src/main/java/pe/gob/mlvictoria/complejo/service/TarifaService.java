package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.tarifa.*;

import java.util.List;

public interface TarifaService {
    CalcularTarifaResponse calcularTarifa(CalcularTarifaRequest dto);
    List<ListarTarrifaResponse> listarTarifa(ListarTarifaRequest dto);
    int actualizarEstadoTarifa(Integer idTarifa, Integer estado);
    int actualizarTarifa(ActualizarTarifaRequest dto);
}
