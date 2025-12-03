package pe.gob.mlvictoria.complejo.service.Impl;

import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.niubiz.*;
import pe.gob.mlvictoria.complejo.repository.NiubizRepository;
import pe.gob.mlvictoria.complejo.service.NiubizStotageService;

import java.util.List;

@Service
public class NiubizStotageServiceImpl implements NiubizStotageService {

    private final NiubizRepository niubizRepository;

    public NiubizStotageServiceImpl(NiubizRepository niubizRepository) {
        this.niubizRepository = niubizRepository;
    }

    @Override
    public NiubizResponse tokenStorage(NiubizRequest dto) {
        return niubizRepository.getNiubiz(dto);
    }

    @Override
    public int cajaRecibosWeb(ReciboGenerarRequest dto) {
        return niubizRepository.cajaRecibosWeb(dto);
    }

    @Override
    public List<EstadoCuentaResponse> EstadoCuenta(EstadoCuentaRequest dto) {
        return niubizRepository.EstadoCuenta(dto);
    }
}
