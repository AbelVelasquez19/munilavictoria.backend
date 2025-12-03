package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosContiResponseDTO {
    private String codigo;
    private String nombres;
    private String direccion;
    private String documento;
    private String nuDocu;
    private String email;
    private String dias;
}
