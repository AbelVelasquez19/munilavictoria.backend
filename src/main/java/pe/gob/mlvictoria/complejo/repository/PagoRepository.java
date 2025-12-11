package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.pago.BuscarTokenRequest;
import pe.gob.mlvictoria.complejo.dto.pago.BuscarTokenResponse;
import pe.gob.mlvictoria.complejo.dto.pago.TicketAprobadoRequest;
import pe.gob.mlvictoria.complejo.dto.pago.TicketAprobadoResponse;
import pe.gob.mlvictoria.complejo.mapper.PagoMapper;

@Repository
public class PagoRepository {

    private final PagoMapper pagoMapper;

    public PagoRepository(PagoMapper pagoMapper) {
        this.pagoMapper = pagoMapper;
    }

    public BuscarTokenResponse buscarTokenPago(BuscarTokenRequest dto) {
        return pagoMapper.buscarTokenPago(dto);
    }

    public TicketAprobadoResponse ticketAprobado(TicketAprobadoRequest dto) {
        return pagoMapper.ticketAprobado(dto);
    }
}
