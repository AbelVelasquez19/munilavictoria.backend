package pe.gob.mlvictoria.complejo.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoRequest;
import pe.gob.mlvictoria.complejo.dto.complejo.ComplejoResponse;
import pe.gob.mlvictoria.complejo.repository.ComplejoRepository;
import pe.gob.mlvictoria.complejo.service.ComplejoService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComplejoServiceImpl implements ComplejoService {

    private final ComplejoRepository complejoRepository;


    @Override
    public List<ComplejoResponse> ListarComplejo(ComplejoRequest dto) {
        if(dto.getOpcion() == null){
            throw new IllegalArgumentException("La opción no puede ser nula");
        }
        List<ComplejoResponse> response = complejoRepository.listarComplejo(dto);
        if(response == null) {
            throw new IllegalArgumentException("No se encontraron complejos para la opción proporcionada");
        }
        return response;
    }
}
