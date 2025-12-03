package pe.gob.mlvictoria.complejo.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.reserva.*;
import pe.gob.mlvictoria.complejo.mapper.ReservaMapper;

@Repository
@RequiredArgsConstructor
public class ReservaRepository {
    private final ReservaMapper reservaMapper;

    public ReservaRegistrarResponse registrarReserva(ReservaRegistrarRequest dto) {
        return reservaMapper.registrarReserva(dto);
    }

    public ReservaCancelarResponse cancelarReserva(@Param("dto") ReservaCancelarRequest dto) {
        return reservaMapper.cancelarReserva(dto);
    }

    public ReservaDetalleResponse reservaDetalle(@Param("dto") ReservaDetalleRequest dto) {
        return reservaMapper.detalleReserva(dto);
    }

    public ReservaHrsLibResponse liberarReservaExpirada() {
        return reservaMapper.liberarReservaExpirada();
    }

    public int pagarReserva(int idReserva,int idRecibo) {
        return reservaMapper.pagarReserva(idReserva,idRecibo);
    }

    public int actualizarReciboNiubiz(ActuReciNiubizRequest dto) {
        return reservaMapper.actualizarDespuesDeAuth(dto);
    }
}
