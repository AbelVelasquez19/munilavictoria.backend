package pe.gob.mlvictoria.pagolinea.service;

import pe.gob.mlvictoria.pagolinea.dto.historialpago.HistorialPagoResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboRequestDTO;

import java.util.List;

public interface HistorialPagoService {
    List<HistorialPagoResponseDTO> annosRecibosPagados (DatosContriRequestDTO dto);
    byte[]  imprimirRecibo (ImprimirReciboRequestDTO dto);
}
