package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumCompraResponseDTO {
    private int id;
    private String purchaseNumber;
    private String nombreDeComercio;
    private String codigoDeComercio;
    private String fechaCreacion;
    private String observacion;
}
