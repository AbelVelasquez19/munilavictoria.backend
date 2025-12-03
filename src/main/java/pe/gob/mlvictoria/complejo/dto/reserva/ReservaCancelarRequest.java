package pe.gob.mlvictoria.complejo.dto.reserva;

import lombok.Data;

@Data
public class ReservaCancelarRequest {
    private Integer opcion;
    private Integer idReserva;
}
