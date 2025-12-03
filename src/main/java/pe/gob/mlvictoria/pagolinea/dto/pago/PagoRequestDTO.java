package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDTO {
    private String amount;
    private String purchaseNumber;
    private String transactionToken;
    private String email;
    private String dni;
    private String codigo;
}
