package pe.gob.mlvictoria.complejo.dto.tarifa;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CalcularTarifaResponse {
    private Integer status;
    private String message;
    private BigDecimal totalPagar;
    private Integer horasSeleccionadas;
    private String detallesJson;
}