package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosContriRequestDTO {
    private String opcion;
    private String codigo;
    private String paramt5;
}
