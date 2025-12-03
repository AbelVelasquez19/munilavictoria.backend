package pe.gob.mlvictoria.complejo.dto.reserva;

import lombok.Data;

@Data
public class ReservaRegistrarResponse {
    private Integer status;
    private String message;
    private Integer idReserva;
}
