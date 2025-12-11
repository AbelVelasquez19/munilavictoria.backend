package pe.gob.mlvictoria.complejo.dto.pago;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDetalleResponse {
    private int idHorarioBase;
    private String rango;
    private BigDecimal precio;
}
