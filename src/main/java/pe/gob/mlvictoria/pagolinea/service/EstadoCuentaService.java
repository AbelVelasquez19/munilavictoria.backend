package pe.gob.mlvictoria.pagolinea.service;

import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaRequestDTO;

import java.util.List;

public interface EstadoCuentaService {
    List<EstadoCuentaResponseDTO> listarEstadoCuenta(EstadoCuentaRequestDTO requestDTO);
    byte[] imprimirEstadoCuenta(ImprimirEstadoCuentaRequestDTO dto);
}
