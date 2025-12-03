package pe.gob.mlvictoria.complejo.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaRequest;
import pe.gob.mlvictoria.complejo.dto.cancha.CanchaResponse;
import pe.gob.mlvictoria.complejo.repository.CanchaRepository;
import pe.gob.mlvictoria.complejo.service.CanchaService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CanchaServiceImpl implements CanchaService {
    private final CanchaRepository canchaRepository;

    @Override
    public List<CanchaResponse> ListarCancha(CanchaRequest dto) {
        if(dto.getIdComplejo() == null){
            throw new BusinessException("El idComplejo no puede ser nulo");
        }
        List<CanchaResponse> response = canchaRepository.ListarCancha(dto);
        if(response == null) {
            throw new BusinessException("No se encontraron canchas para el complejo proporcionado");
        }
        return response;
    }
}
