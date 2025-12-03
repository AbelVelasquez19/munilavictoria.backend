package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.cancha.CanchaRequest;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaResponse;

import java.util.List;

public interface CanchaService {
    List<CanchaResponse> ListarCancha(CanchaRequest dto);
}
