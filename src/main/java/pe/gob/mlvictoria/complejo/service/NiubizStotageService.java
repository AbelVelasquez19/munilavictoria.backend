package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.niubiz.*;

import java.util.List;

public interface NiubizStotageService {
    NiubizResponse tokenStorage(NiubizRequest dto);
    int cajaRecibosWeb(ReciboGenerarRequest dto);
    List<EstadoCuentaResponse> EstadoCuenta(EstadoCuentaRequest dto);
}
