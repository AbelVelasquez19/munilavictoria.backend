package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumeroCompraRequestDTO {
    private String purchaseNumber;
}