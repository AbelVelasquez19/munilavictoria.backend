package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumeroCompraResponseDTO {
    private Integer id;
    private Integer intento;
    private String purchaseNumber;
    private String status;
}
