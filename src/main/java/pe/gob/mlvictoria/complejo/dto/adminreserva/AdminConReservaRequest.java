package pe.gob.mlvictoria.complejo.dto.adminreserva;

import lombok.Data;

@Data
public class AdminConReservaRequest {
    private String fecha;
    private Integer idComplejo;
    private Integer idCancha;
}
