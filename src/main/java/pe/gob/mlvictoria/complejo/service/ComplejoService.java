package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoRequest;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoResponse;

import java.util.List;

public interface ComplejoService {
    List<ComplejoResponse> ListarComplejo(ComplejoRequest dto);
}
