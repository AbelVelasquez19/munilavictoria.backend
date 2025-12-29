package pe.gob.mlvictoria.complejo.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import pe.gob.mlvictoria.complejo.dto.reserva.*;

public interface ReservaService {
    ReservaRegistrarResponse registrarReserva(@Param("dto") ReservaRegistrarRequest dto, HttpServletRequest req);
    ReservaCancelarResponse cancelarReserva(@Param("dto") ReservaCancelarRequest dto);
    ReservaDetalleResponse reservaDetalle(@Param("dto") ReservaDetalleRequest dto);
    ReservaHrsLibResponse liberarReservaExpirada();
    int pagarReserva(int idReserva,String idRecibo, String tokenId);
    int actualizarReciboNiubiz(ActuReciNiubizRequest dto);
}
