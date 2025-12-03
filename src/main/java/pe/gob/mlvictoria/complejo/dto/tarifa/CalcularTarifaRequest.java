package pe.gob.mlvictoria.complejo.dto.tarifa;

import lombok.Data;

@Data
public class CalcularTarifaRequest {
    private Integer idCancha;
    private String fecha;       // '2025-11-25'
    private String horarios;    // '1,2,3'
}
