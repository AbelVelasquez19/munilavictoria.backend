package pe.gob.mlvictoria.consultapide.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.consultapide.dto.DatosPersonaLocalDTO;
import pe.gob.mlvictoria.consultapide.dto.ReniecRegistroResponseDTO;
import pe.gob.mlvictoria.consultapide.mapper.ReniecMapper;
import pe.gob.mlvictoria.consultapide.model.PideReniec;

@Repository
@RequiredArgsConstructor
public class ReniecRepository {

    @Autowired
    private final ReniecMapper mapper;

    public ReniecRegistroResponseDTO registrarConsulta(PideReniec dto){
        return  mapper.registrarConsulta(dto);
    }

    public DatosPersonaLocalDTO BuscarPersonaLocal(String numDocumento){
        return mapper.buscarPorDni(numDocumento);
    }
}
