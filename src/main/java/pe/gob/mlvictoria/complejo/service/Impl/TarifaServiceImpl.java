package pe.gob.mlvictoria.complejo.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaRequest;
import pe.gob.mlvictoria.complejo.dto.tarifa.CalcularTarifaResponse;
import pe.gob.mlvictoria.complejo.repository.TarifaRepository;
import pe.gob.mlvictoria.complejo.service.TarifaService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TarifaServiceImpl implements TarifaService {

    private final TarifaRepository tarifaRepository;

    @Override
    public CalcularTarifaResponse calcularTarifa(CalcularTarifaRequest dto) {
        if(dto.getIdCancha() == null){
            throw new BusinessException("El id cancha no puede ser nulo");
        }
        if(dto.getFecha() == null){
            throw new BusinessException("Fecha no puede ser nulo");
        }
        log.info("Fecha: " + tarifaRepository.calcularTarifa(dto));
        return tarifaRepository.calcularTarifa(dto);
    }
}
