package pe.gob.mlvictoria.complejo.dto.tarifa;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TarifaDetalleResponse {
    private int idHorarioBase;
    private String rango;
    private BigDecimal precio;
    private String codTasa;
}
