package pe.gob.mlvictoria.pagolinea.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.pagolinea.dto.historialpago.HistorialPagoResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboResponseDTO;
import pe.gob.mlvictoria.pagolinea.mapper.HistorialPagoMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistorialPagoRepository {
    @Autowired
    private final HistorialPagoMapper mapper;

    public List<HistorialPagoResponseDTO> annosRecibosPagados (DatosContriRequestDTO requestDTO){
        return mapper.annosRecibosPagados(requestDTO);
    }

    public List<ImprimirReciboResponseDTO> imprimirRecibo (ImprimirReciboRequestDTO requestDTO){
        return mapper.imprimirRecibo(requestDTO);
    }
}
