package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaRequest;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaResponse;

public interface TarifaService {
    CalcularTarifaResponse calcularTarifa(CalcularTarifaRequest dto);
}
