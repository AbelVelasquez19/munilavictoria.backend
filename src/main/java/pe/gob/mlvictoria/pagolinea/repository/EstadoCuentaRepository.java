package pe.gob.mlvictoria.pagolinea.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaResponseDTO;
import pe.gob.mlvictoria.pagolinea.mapper.EstadoCuentaMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EstadoCuentaRepository {
    @Autowired
    private final EstadoCuentaMapper mapper;

    public List<EstadoCuentaResponseDTO> listarEstadoCuenta(EstadoCuentaRequestDTO requestDTO) {
        return mapper.listarEstadoCuenta(requestDTO);
    }

    public List<ImprimirEstadoCuentaResponseDTO> imprimirEstadoCuenta(ImprimirEstadoCuentaRequestDTO requestDTO) {
        return mapper.imprimirEstadoCuenta(requestDTO);
    }
}
