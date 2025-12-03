package pe.gob.mlvictoria.consultapide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.consultapide.dto.DatosPersonaLocalDTO;
import pe.gob.mlvictoria.consultapide.dto.ReniecRegistroResponseDTO;
import pe.gob.mlvictoria.consultapide.model.PideReniec;
import pe.gob.mlvictoria.consultapide.repository.ReniecRepository;

import java.util.Map;

@Service
public class ReniecServiceImpl  implements  ReniecService{

    @Autowired
    private ReniecRepository reniecRepository;

    @Override
    public ReniecRegistroResponseDTO registrarConsulta(PideReniec requestDTO) {
        return  reniecRepository.registrarConsulta(requestDTO);
    }

    @Override
    public DatosPersonaLocalDTO buscarPersonaLocal(String numDocumento) {
        return reniecRepository.BuscarPersonaLocal(numDocumento);
    }

}
