package pe.gob.mlvictoria.consultapide.service;

import pe.gob.mlvictoria.consultapide.dto.DatosPersonaLocalDTO;
import pe.gob.mlvictoria.consultapide.dto.ReniecRegistroResponseDTO;
import pe.gob.mlvictoria.consultapide.model.PideReniec;

public interface ReniecService {
    ReniecRegistroResponseDTO registrarConsulta(PideReniec requestDTO);
    DatosPersonaLocalDTO buscarPersonaLocal(String numDocumento);
}
