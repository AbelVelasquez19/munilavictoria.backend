package pe.gob.mlvictoria.complejo.repository;

import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.niubiz.*;
import pe.gob.mlvictoria.complejo.mapper.NiubizMapper;

import java.util.List;

@Repository
public class NiubizRepository {
    private final NiubizMapper niubizMapper;

    public NiubizRepository(NiubizMapper niubizMapper) {
        this.niubizMapper = niubizMapper;
    }

    public NiubizResponse getNiubiz(NiubizRequest dto) {
        return niubizMapper.tokenStorage(dto);
    }

    public int cajaRecibosWeb(ReciboGenerarRequest dto) {
        return niubizMapper.cajaRecibosWeb(dto);
    }

    public List<EstadoCuentaResponse> EstadoCuenta(EstadoCuentaRequest dto) {
        return niubizMapper.estadoCuenta(dto);
    }

    public List<DetalleReservaResponse> buscarDetalleReserva(int idReserva) {
        return niubizMapper.buscarDetalleReserva(idReserva);
    }
}
