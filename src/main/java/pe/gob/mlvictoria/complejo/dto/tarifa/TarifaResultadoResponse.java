package pe.gob.mlvictoria.complejo.dto.tarifa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifaResultadoResponse{
    private Integer status;
    private String message;
    private BigDecimal totalPagar;
    private Integer horasSeleccionadas;
    private List<TarifaDetalleResponse> detalles;

}
