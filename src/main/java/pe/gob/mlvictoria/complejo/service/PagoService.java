package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.pago.*;

public interface PagoService {
    BuscarTokenResponse buscarTokenPago(BuscarTokenRequest dto);
    TicketAprobadoResponse ticketAprobado(TicketAprobadoRequest dto);
    boolean enviarTicketAprobado(TicketAproResulResponse response, String idToken);
    boolean enviarTicketRechazado(BuscarTokenResponse response, String correo);
}
